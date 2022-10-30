package com.ra.bioskop.controller;

import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.dto.request.user.UpdateUserRequest;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.service.UserService;
import com.ra.bioskop.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.ra.bioskop.exception.BioskopException.*;

@CrossOrigin(origins = "*", maxAge = 3900)
@Tag(name = "User")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Mengubah profile user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Berhasil", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "404", description = "User tidak ditemukan", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }),
            @ApiResponse(responseCode = "406", description = "Email tidak valid.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }) })
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest updateUserRequest) {
        try {
            if (!Constants.validateEmail(updateUserRequest.getEmail()))
                throw throwException(ExceptionType.INVALID_EMAIL, HttpStatus.NOT_ACCEPTABLE,
                        "Email tidak valid");

            userService.updateProfile(updateUser(updateUserRequest));
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "updated", null));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        } catch (EmailValidateException e) {
            return new ResponseEntity<>(
                    new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        }
    }

    @Operation(summary = "Menghapus user berdasarkan email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Berhasil menghapus user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "404", description = "User tidak ditemukan`", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }),
            @ApiResponse(responseCode = "406", description = "Email tidak valid.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) }) })
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteByEmail(@RequestParam("email") String email) {
        try {
            if (!Constants.validateEmail(email))
                throw throwException(ExceptionType.INVALID_EMAIL, HttpStatus.NOT_ACCEPTABLE,
                        "Email tidak valid");

            userService.deleteByEmail(email);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "deleted",
                    null));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        } catch (EmailValidateException e) {
            return new ResponseEntity<>(
                    new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        }
    }

    private UserDTO updateUser(UpdateUserRequest updateUserRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(updateUserRequest.getEmail());
        userDTO.setUsername(updateUserRequest.getNewUsername());
        return userDTO;
    }
}
