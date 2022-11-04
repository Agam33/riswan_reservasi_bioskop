package com.ra.bioskop.service;

import com.ra.bioskop.dto.mapper.UserMapper;
import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.model.user.Roles;
import com.ra.bioskop.model.user.Users;
import com.ra.bioskop.repository.RolesRepository;
import com.ra.bioskop.repository.UserRepository;
import com.ra.bioskop.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, RolesRepository rolesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return UserMapper.toDto(user.get());
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                Constants.NOT_FOUND_MSG);
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<Users> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isEmpty()) {
            Optional<Roles> role = rolesRepository.findByName(userDTO.getRole());
            if(role.isPresent()) {
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
        }
        throw BioskopException.throwException(ExceptionType.DUPLICATE_ENTITY, HttpStatus.CONFLICT,
               Constants.ALREADY_EXIST_MSG);
    }

    @Override
    public UserDTO updateProfile(UserDTO userDTO) {
        Optional<Users> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent()) {
            Users userModel = user.get();
            userModel.setUsername(userDTO.getUsername());
            userModel.setUpdatedAt(LocalDateTime.now());
            return UserMapper.toDto(userRepository.save(userModel));
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                Constants.NOT_FOUND_MSG);
    }

    @Override
    public UserDTO deleteByEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Users userModel = user.get();
            userRepository.delete(userModel);
            return UserMapper.toDto(userModel);
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                Constants.NOT_FOUND_MSG);
    }
}
