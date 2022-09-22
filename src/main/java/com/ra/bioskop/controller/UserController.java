package com.ra.bioskop.controller;

import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.dto.request.user.RegisRequest;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.service.UserService;
import com.ra.bioskop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.ra.bioskop.exception.BioskopException.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody RegisRequest regisRequest) {
        try {
            if(!Constants.validateEmail(regisRequest.getEmail()))
                throw throwException(ExceptionType.INVALID_EMAIL, HttpStatus.NOT_ACCEPTABLE, "Periksa kembali email");

            userService.add(addUser(regisRequest));

            return ResponseEntity.ok(new Response<>(HttpStatus.CREATED.value(), new Date(),
                    "created", null));
        } catch (EntityNotFoundException
                 | EmailValidateException
                 | DuplicateEntityException e) {
            return ResponseEntity.ok(new ResponseError(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        try {
            if(!Constants.validateEmail(userDTO.getEmail()))
                throw throwException(ExceptionType.INVALID_EMAIL, HttpStatus.NOT_ACCEPTABLE, "Periksa kembali email");

            userService.updateProfile(userDTO);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "updated", null));
        } catch (EntityNotFoundException
                 | EmailValidateException e) {
            return ResponseEntity.ok(new ResponseError(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteByEmail(@RequestParam("email") String email) {
        try {
            if(!Constants.validateEmail(email))
                throw throwException(ExceptionType.INVALID_EMAIL, HttpStatus.NOT_ACCEPTABLE, "Periksa kembali email");

            userService.deleteByEmail(email);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "deleted",
                    null));
        } catch (EntityNotFoundException
                | EmailValidateException e) {
            return ResponseEntity.ok(new ResponseError(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()));
        }
    }

    private UserDTO addUser(RegisRequest regisRequest) {
        UserDTO userDTO = new UserDTO();
        String userId = "user-"+Constants.randomIdentifier(regisRequest.getUsername())[4];
        userDTO.setId(userId);
        userDTO.setUsername(regisRequest.getUsername());
        userDTO.setPassword(regisRequest.getPassword());
        userDTO.setEmail(regisRequest.getEmail());
        userDTO.setCreatedAt(new Date());
        return userDTO;
    }
}
