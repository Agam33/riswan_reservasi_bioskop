package com.ra.bioskop.controller;

import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.dto.request.user.RegisRequest;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.service.UserService;
import com.ra.bioskop.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

import static com.ra.bioskop.exception.BioskopException.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Menambahkan user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user berhasil ditambahkan."),
            @ApiResponse(responseCode = "406", description = "email salah."),
            @ApiResponse(responseCode = "409", description = "user sudah ada.")})
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody RegisRequest regisRequest) {
        try {
            userService.add(addUser(regisRequest));
            return ResponseEntity.ok(new Response<>(HttpStatus.CREATED.value(), new Date(),
                    "created", null));
        } catch (DuplicateEntityException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @Operation(summary = "Mengubah profile user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Berhasil"),
            @ApiResponse(responseCode = "404", description = "User tidak ditemukan")})
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        try {
            userService.updateProfile(userDTO);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "updated", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @Operation(summary = "Menghapus user berdasarkan email")
    @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Berhasil menghapus user"),
                @ApiResponse(responseCode = "404", description = "User tidak ditemukan`")})
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteByEmail(@RequestParam("email") String email) {
        try {

            userService.deleteByEmail(email);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "deleted",
                    null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    private UserDTO addUser(RegisRequest regisRequest) {
        UserDTO userDTO = new UserDTO();
        String userId = "user-"+Constants.randomIdentifier(regisRequest.getUsername())[4];
        userDTO.setId(userId);
        userDTO.setUsername(regisRequest.getUsername());
        userDTO.setPassword(regisRequest.getPassword());
        userDTO.setEmail(regisRequest.getEmail());
        userDTO.setCreatedAt(LocalDateTime.now());
        return userDTO;
    }
}
