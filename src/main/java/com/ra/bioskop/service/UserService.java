package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.user.UserDTO;

public interface UserService {
    UserDTO findByEmail(String email);

    UserDTO signup(UserDTO userDTO);

    UserDTO updateProfile(UserDTO userDTO);

    UserDTO deleteByEmail(String email);


}
