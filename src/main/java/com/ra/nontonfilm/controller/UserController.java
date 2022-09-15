package com.ra.nontonfilm.controller;

import com.ra.nontonfilm.dto.model.user.UserDTO;
import com.ra.nontonfilm.dto.request.user.RegisRequest;
import com.ra.nontonfilm.dto.response.Response;
import com.ra.nontonfilm.dto.response.ResponseError;
import com.ra.nontonfilm.exception.ExceptionType;
import com.ra.nontonfilm.service.UserService;
import com.ra.nontonfilm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.ra.nontonfilm.exception.NontonFilmException.*;

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
                throw throwException(ExceptionType.INVALID_EMAIL, "Periksa kembali email");

            userService.add(addUser(regisRequest));

            return ResponseEntity.ok(new Response(false,
                    "added", null));
        } catch (EntityNotFoundException
                 | EmailValidateException
                 | DuplicateEntityException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        try {
            if(!Constants.validateEmail(userDTO.getEmail()))
                throw throwException(ExceptionType.INVALID_EMAIL, "Periksa kembali email");

            userService.updateProfile(userDTO);
            return ResponseEntity.ok(new Response(false,
                    "updated", null));
        } catch (EntityNotFoundException
                 | EmailValidateException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody UserDTO userDTO) {
        try {
            if(!Constants.validateEmail(userDTO.getEmail()))
                throw throwException(ExceptionType.INVALID_EMAIL, "Periksa kembali email");

            userService.delete(userDTO);
            return ResponseEntity.ok(new Response(false,
                    "deleted",
                    null));
        } catch (EntityNotFoundException
                | EmailValidateException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
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
