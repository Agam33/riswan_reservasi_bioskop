package com.ra.bioskop.dto.mapper;

import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.model.user.Users;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public static UserDTO toDto(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
