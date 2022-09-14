package com.ra.nontonfilm.service;

import com.ra.nontonfilm.dto.model.user.UserDTO;
import com.ra.nontonfilm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDTO> findByEmail(String email) {

        return Optional.empty();
    }

    @Override
    public UserDTO add(UserDTO userDTO) {

        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {

        return userDTO;
    }

    @Override
    public UserDTO delete(UserDTO userDTO) {

        return userDTO;
    }
}
