package com.ra.bioskop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.dto.request.user.LoginRequest;
import com.ra.bioskop.dto.request.user.RegisRequest;
import com.ra.bioskop.dto.response.JwtResponse;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.model.user.ERoles;
import com.ra.bioskop.security.userservice.UserDetailsImpl;
import com.ra.bioskop.service.UserService;
import com.ra.bioskop.util.Constants;
import com.ra.bioskop.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.ra.bioskop.exception.BioskopException.*;

import java.time.LocalDateTime;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3900)
@Tag(name = "Auth")
@RestController
@RequestMapping(Constants.AUTH_ENDPOINT)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Regis User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User berhasil ditambahkan.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "406", description = "Email tidak valid.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }),
            @ApiResponse(responseCode = "409", description = "User sudah ada.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }) })
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody @Valid RegisRequest regisRequest) throws EmailValidateException {
        try {
            if (!Constants.validateEmail(regisRequest.getEmail()))
                throw throwException(ExceptionType.INVALID_EMAIL, HttpStatus.NOT_ACCEPTABLE,
                       Constants.INVALID_EMAIL_MSG);

            userService.register(regisUser(regisRequest));
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.CREATED_MSG, null));
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(
                    new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        }
    }

    @Operation(summary = "Auth user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Berhasil Login", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "406", description = "Email tidak valid.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }) })
    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String accessToken = jwtUtil.generateJwtToken(authentication);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.SUCCESS_MSG, new JwtResponse(userDetails.getUsername(), accessToken)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(HttpStatus.UNAUTHORIZED.value(),
                    new Date(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    private UserDTO regisUser(RegisRequest regisRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(regisRequest.getUsername());
        userDTO.setPassword(regisRequest.getPassword());
        userDTO.setEmail(regisRequest.getEmail());
        userDTO.setCreatedAt(LocalDateTime.now());

        ERoles userRole = ERoles.getRole(regisRequest.getRoleName());
        String userId = userRole.name().split("_")[1] + "-" + Constants.randomIdentifier(regisRequest.getEmail())[4];
        userDTO.setId(userId);
        userDTO.setRole(userRole);
        return userDTO;
    }

}
