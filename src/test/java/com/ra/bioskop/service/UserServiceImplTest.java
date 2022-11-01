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
import java.util.List;
import java.util.Optional;

class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepo;

    @Mock
    private RolesRepository mockUserRoles;

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
    public void positiveTestFindUserByEmail() {
        String email = "sheila@gmail.com";
        Optional<Users> user = dataDummyUser.findByEmail(email);

        Mockito.when(mockUserRepo.findByEmail(email))
                .thenReturn(user);

        var actualValue = userService.findByEmail(email);
        var expectedValue = user.orElse(null);

        Assertions.assertNotNull(expectedValue);
        Assertions.assertNotNull(actualValue);
        Assertions.assertEquals(expectedValue.getEmail(), actualValue.getEmail());
        Assertions.assertEquals(expectedValue.getId(), actualValue.getId());
        Assertions.assertEquals(expectedValue.getUsername(), actualValue.getUsername());
    }

    @Test
    @DisplayName("Find User by Email, " +
            "Should throw exception User Not Found with status 404, Negative")
    public void negativeTestFindUserByEmail() {
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
    public void positiveTestRegister() {
        List<Roles> roles = dataDummyUser.getRoles();

        Users users = new Users();
        users.setId("customer-001");
        users.getRoles().add(roles.get(1));
        users.setCreatedAt(LocalDateTime.now());
        users.setUpdatedAt(LocalDateTime.now());
        users.setUsername("James");
        users.setEmail("james@gmail.com");

        Mockito.when(mockUserRoles.saveAll(roles))
                        .thenReturn(roles);
        Mockito.when(mockUserRepo.save(users))
                .thenReturn(users);

        UserDTO userDTO = UserMapper.toDto(users);

        var actualValue = userService.register(userDTO);
        var expectedValue = dataDummyUser.getAllUser().get(1);

        Assertions.assertNotNull(actualValue);
        Assertions.assertNotNull(expectedValue);
        Assertions.assertEquals(expectedValue.getEmail(), actualValue.getEmail());
        Assertions.assertEquals(expectedValue.getId(), actualValue.getId());
        Assertions.assertEquals(expectedValue.getUsername(), actualValue.getUsername());
    }

    @Test
    @DisplayName("Register, Negative")
    public void negativeTestRegister() {

    }

    @Test
    @DisplayName("Update User, Positive")
    public void positiveTestUpdate() {

    }

    @Test
    @DisplayName("Update User, Negative")
    public void negativeTestUpdate() {

    }

    @Test
    @DisplayName("Delete user by email, Positive")
    public void positiveTestDeleteByEmail() {

    }

    @Test
    @DisplayName("Delete user by email, Negative")
    public void negativeTestDeleteByEmail() {

    }


}