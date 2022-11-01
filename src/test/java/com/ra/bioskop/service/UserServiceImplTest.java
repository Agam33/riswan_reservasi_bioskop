package com.ra.bioskop.service;

import com.ra.bioskop.repository.RolesRepository;
import com.ra.bioskop.repository.UserRepository;
import com.ra.bioskop.util.DataDummyUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private RolesRepository userRoles;

    private DataDummyUser dataDummyUser;

    @BeforeEach
    public void init() {
        dataDummyUser = new DataDummyUser();
    }

    @Test
    @DisplayName("Find User by Email, Positive")
    public void positiveTestFindUserByEmail() {

    }

    @Test
    @DisplayName("Find User by Email, Negative")
    public void negativeTestFindUserByEmail() {

    }

    @Test
    @DisplayName("Register, Positive")
    public void positiveTestRegister() {

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