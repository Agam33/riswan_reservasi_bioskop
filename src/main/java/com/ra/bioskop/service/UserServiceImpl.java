package com.ra.bioskop.service;

import com.ra.bioskop.dto.mapper.UserMapper;
import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.dto.request.user.LoginRequest;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.model.user.Roles;
import com.ra.bioskop.model.user.Users;
import com.ra.bioskop.repository.RolesRepository;
import com.ra.bioskop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public UserDTO findByEmail(String email) {
        Optional<Users> user = userRepository.findUserByEmail(email);
        if(user.isPresent()) {
            return UserMapper.toDto(user.get());
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                "User dengan email " + email + " tidak ditemukan");
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<Users> user = userRepository.findUserByEmail(userDTO.getEmail());
        if(user.isEmpty()) {
            Optional<Roles> role = rolesRepository.findByName(userDTO.getRole());
            Users userModel = new Users();
            userModel.setId(userDTO.getId());
            userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userModel.setUsername(userDTO.getUsername());
            userModel.setEmail(userDTO.getEmail());
            userModel.setCreatedAt(LocalDateTime.now());
            userModel.setUpdatedAt(LocalDateTime.now());
            userModel.getRoles().add(role.get());
            userRepository.save(userModel);
            return userDTO;
        }
        throw BioskopException.throwException(ExceptionType.DUPLICATE_ENTITY, HttpStatus.CONFLICT,
                "User dengan email "+userDTO.getEmail() + " sudah ada");
    }

    @Override
    public UserDTO updateProfile(UserDTO userDTO) {
        Optional<Users> user = userRepository.findUserByEmail(userDTO.getEmail());
        if(user.isPresent()) {
            Users userModel = user.get();
            userModel.setUsername(userDTO.getUsername());
            userModel.setUpdatedAt(LocalDateTime.now());
            userRepository.save(userModel);
            return userDTO;
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                "User dengan email " + userDTO.getEmail() + " tidak ditemukan");
    }

    @Override
    public UserDTO deleteByEmail(String email) {
        Optional<Users> user = userRepository.findUserByEmail(email);
        if(user.isPresent()) {
            Users userModel = user.get();
            userRepository.delete(userModel);
            return UserMapper.toDto(userModel);
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                "User dengan email " + email + " tidak ditemukan");
    }

    @Override
    public UserDTO login(LoginRequest loginRequest) {
        Optional<Users> user = userRepository.findUserByEmail(loginRequest.getEmail());
        if(user.isPresent() &&
                user.get().getPassword().equals(passwordEncoder.encode(loginRequest.getPassword()))) {

        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                "User dengan email " + loginRequest.getEmail() + " tidak ditemukan");
    }
}
