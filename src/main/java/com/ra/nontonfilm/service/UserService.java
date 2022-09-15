package com.ra.nontonfilm.service;

import com.ra.nontonfilm.dto.model.user.UserDTO;

public interface UserService {
    UserDTO findByEmail(String email);

    UserDTO add(UserDTO userDTO);

    UserDTO updateProfile(UserDTO userDTO);

    UserDTO delete(UserDTO userDTO);

}
