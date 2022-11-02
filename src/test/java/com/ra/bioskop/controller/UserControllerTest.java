package com.ra.bioskop.controller;

import com.ra.bioskop.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("/update")
    public void testPositiveUpdate() {

    }

}