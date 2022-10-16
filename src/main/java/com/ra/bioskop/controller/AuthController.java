package com.ra.bioskop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.dto.request.user.LoginRequest;
import com.ra.bioskop.dto.request.user.RegisRequest;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.model.user.ERoles;
import com.ra.bioskop.service.UserService;
import com.ra.bioskop.util.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.ra.bioskop.exception.BioskopException.*;

import java.time.LocalDateTime;
import java.util.Date;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Daftar User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User berhasil ditambahkan.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "406", description = "Email tidak valid.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }),
            @ApiResponse(responseCode = "409", description = "User sudah ada.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }) })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisRequest regisRequest) {
        try {
            if (!Constants.validateEmail(regisRequest.getEmail()))
                throw throwException(ExceptionType.INVALID_EMAIL, HttpStatus.NOT_ACCEPTABLE,
                        "Email tidak valid");

            userService.register(regisUser(regisRequest));
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "created", null));
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(
                    new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        } catch (EmailValidateException e) {
            return new ResponseEntity<>(
                    new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        }
    }

    // TODO: Login
    @Operation(summary = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Berhasil Login"),
            @ApiResponse(responseCode = "406", description = "Email tidak valid.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }) })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        return new ResponseEntity<>(
                new ResponseError(HttpStatus.NOT_FOUND.value(), new Date(), "Tidak ditemukan"),
                HttpStatus.NOT_FOUND);
    }

    private UserDTO regisUser(RegisRequest regisRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(regisRequest.getUsername());
        userDTO.setPassword(regisRequest.getPassword());
        userDTO.setEmail(regisRequest.getEmail());
        userDTO.setCreatedAt(LocalDateTime.now());

        ERoles userRole = ERoles.getRole(regisRequest.getRoleName());
        String userId = userRole.getName().split("_")[1] + "-" + Constants.randomIdentifier(regisRequest.getEmail())[4];
        userDTO.setId(userId);
        userDTO.setRole(userRole);
        return userDTO;
    }

}
