package com.ra.nontonfilm.dto.mapper;

import com.ra.nontonfilm.dto.model.user.UserDTO;
import com.ra.nontonfilm.model.user.Users;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public static UserDTO entityToDto(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
