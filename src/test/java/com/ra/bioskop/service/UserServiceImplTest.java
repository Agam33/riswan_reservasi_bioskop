package com.ra.bioskop.service;


import com.ra.bioskop.dto.mapper.UserMapper;
import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.model.user.ERoles;
import com.ra.bioskop.model.user.Roles;
import com.ra.bioskop.model.user.Users;
import com.ra.bioskop.repository.RolesRepository;
import com.ra.bioskop.repository.UserRepository;
import com.ra.bioskop.util.DataDummyUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepo;

    @Mock
    private RolesRepository mockRoleRepo;

    @Mock
    private BCryptPasswordEncoder encoder;

    private DataDummyUser dataDummyUser;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        dataDummyUser = new DataDummyUser();
    }

    @Test
    @DisplayName("Find User by Email, Positive")
    void positiveTestFindUserByEmail() {
        String email = "sheila@gmail.com";
        Optional<Users> user = dataDummyUser.findByEmail(email);

        Mockito.when(mockUserRepo.findByEmail(email))
                .thenReturn(user);

        var actualValue = userService.findByEmail(email);
        var expectedValue = user.orElse(null);

        Assertions.assertNotNull(expectedValue);
        Assertions.assertNotNull(actualValue);
        Assertions.assertEquals(expectedValue.getEmail(), actualValue.getEmail());
        Assertions.assertEquals(expectedValue.getUsername(), actualValue.getUsername());
    }

    @Test
    @DisplayName("Find User by Email, " +
            "Should throw exception User Not Found with status 404, Negative")
    void negativeTestFindUserByEmail() {
        BioskopException
                .EntityNotFoundException e = Assertions.assertThrows(BioskopException.EntityNotFoundException.class, () -> {
                    String email = "sheilaaAaAaAaAaAaAaAa@gmail.com";
                    Optional<Users> user = dataDummyUser.findByEmail(email);
                            Mockito.when(mockUserRepo.findByEmail(email))
                    .thenReturn(user);
                            userService.findByEmail(email);
        });
        Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Register, Positive")
    void positiveTestRegister() {
        Users users = dataDummyUser.getAllUser().get(1);

        UserDTO userDTO = UserMapper.toDto(users);

        Roles role = new Roles();
        role.setId(1);
        role.setName(ERoles.ROLE_ADMIN);
        role.setUsers(new ArrayList<>());

        Mockito.when(mockRoleRepo.findByName(userDTO.getRole()))
                .thenReturn(Optional.of(role));
        Mockito.when(mockUserRepo.save(users))
                .thenReturn(users);

        var actualValue = userService.register(userDTO);
        var expectedValue = dataDummyUser.getAllUser().get(1);

        Assertions.assertNotNull(actualValue);
        Assertions.assertNotNull(expectedValue);
        Assertions.assertEquals(expectedValue.getEmail(), actualValue.getEmail());
        Assertions.assertEquals(expectedValue.getUsername(), actualValue.getUsername());
    }

    @Test
    @DisplayName("Register, " +
            "should throw exception Duplicate Entity with status 409, Negative")
    void negativeTestRegister() {
        BioskopException.DuplicateEntityException e =
                Assertions.assertThrows(BioskopException.DuplicateEntityException.class, () -> {
                    UserDTO userDTO = UserMapper.toDto(dataDummyUser
                            .getAllUser()
                            .get(0));
                    Optional<Users> user = dataDummyUser.findByEmail(userDTO.getEmail());
                    Mockito.when(mockUserRepo.findByEmail(userDTO.getEmail()))
                                    .thenReturn(user);
                    userService.register(userDTO);
                });
        Assertions.assertEquals(HttpStatus.CONFLICT ,e.getStatusCode());
    }

    @Test
    @DisplayName("Update User, Positive")
    void positiveTestUpdate() {
        Users users = dataDummyUser.getAllUser().get(0);
        Optional<Users> userByEmail = dataDummyUser.findByEmail(users.getEmail());

        String newUsername = "Sheila Amundsen";
        UserDTO userDTO = UserMapper.toDto(users);
        userDTO.setUsername(newUsername);

        Users userModel = userByEmail.get();
        userModel.setUsername(newUsername);
        userModel.setUpdatedAt(LocalDateTime.now());

        Mockito.when(mockUserRepo.findByEmail(userDTO.getEmail()))
                .thenReturn(userByEmail);
        Mockito.when(mockUserRepo.save(users))
                .thenReturn(userModel);

        var actualValue = userService.updateProfile(userDTO);
        var expectedValue = userByEmail.get();

        Assertions.assertEquals(expectedValue.getEmail(), actualValue.getEmail());
        Assertions.assertEquals(newUsername, actualValue.getUsername());
    }

    @Test
    @DisplayName("Update User, " +
            "Should throw exception User Not Found with status 404, Negative")
    void negativeTestUpdate() {
        BioskopException.EntityNotFoundException e =
                Assertions.assertThrows(BioskopException.EntityNotFoundException.class, () -> {
                    String wrongEmail = "example@gmail.com";
                    UserDTO userDTO = UserMapper.toDto(dataDummyUser
                            .getAllUser()
                            .get(0));
                    userDTO.setEmail(wrongEmail);
                    userService.updateProfile(userDTO);
                });
        Assertions.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
    }

    @Test
    @DisplayName("Delete user by email, Positive")
    void positiveTestDeleteByEmail() {
        String email = "sheila@gmail.com";
        Optional<Users> user = dataDummyUser.findByEmail(email);

        Mockito.when(mockUserRepo.findByEmail(email))
                        .thenReturn(user);
        Mockito.doNothing()
                .when(mockUserRepo)
                .delete(user.get());

        mockUserRepo.delete(user.get());

        Mockito.verify(mockUserRepo).delete(user.get());

        var actualValue = userService.deleteByEmail(email);
        var expectedValue = user.orElse(null);

        Assertions.assertEquals(expectedValue.getEmail(), actualValue.getEmail());
        Assertions.assertEquals(expectedValue.getUsername(), actualValue.getUsername());
    }

    @Test
    @DisplayName("Delete user by email," +
            "Should throw exception User Not Found with status 404, Negative")
    void negativeTestDeleteByEmail() {
        BioskopException.EntityNotFoundException e =
                Assertions
                        .assertThrows(BioskopException.EntityNotFoundException.class, () -> {
            String email = "sophia@gmail.com";
            userService.deleteByEmail(email);
        });
        Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}