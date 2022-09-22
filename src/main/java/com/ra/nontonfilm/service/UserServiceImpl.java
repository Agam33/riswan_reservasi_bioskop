package com.ra.nontonfilm.service;

import com.ra.nontonfilm.dto.mapper.UserMapper;
import com.ra.nontonfilm.dto.model.user.UserDTO;
import com.ra.nontonfilm.exception.ExceptionType;
import com.ra.nontonfilm.exception.NontonFilmException;
import com.ra.nontonfilm.model.user.Users;
import com.ra.nontonfilm.repository.UserRepository;
import com.ra.nontonfilm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findByEmail(String email) {
        Optional<Users> user = userRepository.findUserByEmail(email);
        if(user.isPresent()) {
            return UserMapper.toDto(user.get());
        }
        throw NontonFilmException.throwException(ExceptionType.USER_NOT_FOUND, HttpStatus.NOT_FOUND,
                "User dengan email " + email + " tidak ditemukan");
    }

    @Override
    public UserDTO add(UserDTO userDTO) {
        Optional<Users> user = userRepository.findUserByEmail(userDTO.getEmail());
        if(user.isEmpty()) {
            Users userModel = new Users();
            userModel.setId(userDTO.getId());
            userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userModel.setUsername(userDTO.getUsername());
            userModel.setEmail(userDTO.getEmail());
            userModel.setCreatedAt(new Date());
            userModel.setUpdateAt(new Date());
            userRepository.save(userModel);
            return userDTO;
        }
        throw NontonFilmException.throwException(ExceptionType.DUPLICATE_ENTITY, HttpStatus.CONFLICT,
                "User dengan email "+userDTO.getEmail() + " sudah ada");
    }

    @Override
    public UserDTO updateProfile(UserDTO userDTO) {
        Optional<Users> user = userRepository.findUserByEmail(userDTO.getEmail());
        if(user.isPresent()) {
            Users userModel = user.get();
            userModel.setUsername(userDTO.getUsername());
            userModel.setUpdateAt(new Date());
            userRepository.save(userModel);
            return userDTO;
        }
        throw NontonFilmException.throwException(ExceptionType.USER_NOT_FOUND, HttpStatus.NOT_FOUND,
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
        throw NontonFilmException.throwException(ExceptionType.USER_NOT_FOUND, HttpStatus.NOT_FOUND,
                "User dengan email " + email + " tidak ditemukan");
    }
}
