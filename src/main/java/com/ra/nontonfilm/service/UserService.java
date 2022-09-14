package com.ra.nontonfilm.service;

import com.ra.nontonfilm.dto.model.user.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findByEmail(String email);

    UserDTO add(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO delete(UserDTO userDTO);
}
