package com.ra.bioskop.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.dto.request.user.LoginRequest;
import com.ra.bioskop.dto.request.user.RegisRequest;
import com.ra.bioskop.model.user.ERoles;
import com.ra.bioskop.service.UserService;
import com.ra.bioskop.util.Constants;
import com.ra.bioskop.util.DataDummyUser;
import com.ra.bioskop.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    private ObjectMapper objMapper;

    private DaoAuthenticationProvider authentication;

    private DataDummyUser dataDummyUser;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .build();
        objMapper = new ObjectMapper();
        dataDummyUser = new DataDummyUser();
    }

    @Test
    @DisplayName("/register, Register, Positive")
    void testPositiveRegister() throws Exception {
        RegisRequest regisRequest = new RegisRequest();
        regisRequest.setEmail("sophie@gmail.com");
        regisRequest.setPassword("sophie");
        regisRequest.setRoleName("admin");
        regisRequest.setUsername("sophie33");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(regisRequest.getUsername());
        userDTO.setPassword(regisRequest.getPassword());
        userDTO.setEmail(regisRequest.getEmail());
        userDTO.setCreatedAt(LocalDateTime.now());

        ERoles userRole = ERoles.getRole(regisRequest.getRoleName());
        String userId = userRole.name().split("_")[1] + "-" + Constants.randomIdentifier(regisRequest.getEmail())[4];
        userDTO.setId(userId);
        userDTO.setRole(userRole);

        Mockito.when(userService.register(userDTO))
                .thenReturn(userDTO);

        String requestAsString = objMapper.writeValueAsString(regisRequest);

        MockHttpServletResponse response =
                mockMvc.perform(
                        post(Constants.AUTH_ENDPOINT + "/signup")
                                .content(requestAsString)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        JsonNode jsonNode = objMapper.readTree(response.getContentAsString());

        Assertions.assertNotNull(response.getContentAsString());
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("created", jsonNode.get("message").asText());
    }

    @Test
    @DisplayName("/signin, Signin, Positive")
    void testPositiveSignIn() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("sophie@gmail.com");
        loginRequest.setPassword("sophie123");

        String requestAsString = objMapper.writeValueAsString(loginRequest);

        var principal = mock(Principal.class);
        when(principal.getName()).thenReturn(loginRequest.getEmail());
//
//        var userAuth = mock(Authentication.class);
//        when(userAuth.getAuthorities())
//                .thenReturn(Set.of());
//        when(userAuth.getPrincipal())
//                .thenReturn(principal);
//
//
//        Mockito.when(authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()))
//        ).thenReturn(userAuth);
//
//        SecurityContextHolder.getContext().setAuthentication(userAuth);
//
//        MockHttpServletResponse response =
//                mockMvc.perform(
//                        post(Constants.AUTH_ENDPOINT + "/signin")
//                                .content(requestAsString)
//                                .principal(principal)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                ).andReturn().getResponse();
//
//        JsonNode jsonNode = objMapper.readTree(response.getContentAsString());
//        System.out.println(jsonNode.toPrettyString());
//        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
//        Assertions.assertNotNull(response.getContentAsString());

    }

}