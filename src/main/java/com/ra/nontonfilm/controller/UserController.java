package com.ra.nontonfilm.controller;

import com.ra.nontonfilm.dto.model.user.UserDTO;
import com.ra.nontonfilm.dto.request.RegisRequest;
import com.ra.nontonfilm.dto.response.Response;
import com.ra.nontonfilm.dto.response.ResponseError;
import com.ra.nontonfilm.exception.NontonFilmException;
import com.ra.nontonfilm.service.UserService;
import com.ra.nontonfilm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody RegisRequest regisRequest) {
        try {
            if(!Constants.validateEmail(regisRequest.getEmail()))
                throw new RuntimeException("Periksa kembali email");

            userService.add(registerUser(regisRequest));

            return ResponseEntity.ok(new Response(false,
                    "created", null));
        } catch (NontonFilmException.EntityNotFoundException | NontonFilmException.DuplicateEntityException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        try {
            if(!Constants.validateEmail(userDTO.getEmail()))
                throw new RuntimeException("Periksa kembali email");

            userService.updateProfile(userDTO);
            return ResponseEntity.ok(new Response(false,
                    "updated", null));
        } catch (NontonFilmException.EntityNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody UserDTO userDTO) {
        try {
            if(!Constants.validateEmail(userDTO.getEmail()))
                throw new RuntimeException("Periksa kembali email");

            userService.delete(userDTO);
            return ResponseEntity.ok(new Response(false,
                    "deleted",
                    null));
        } catch (NontonFilmException.EntityNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    private UserDTO registerUser(RegisRequest regisRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(regisRequest.getUsername());
        userDTO.setPassword(regisRequest.getPassword());
        userDTO.setEmail(regisRequest.getEmail());
        userDTO.setCreatedAt(new Date());
        return userDTO;
    }
}
