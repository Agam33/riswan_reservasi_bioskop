package com.ra.bioskop.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ra.bioskop.dto.mapper.UserMapper;
import com.ra.bioskop.dto.model.user.UserDTO;
import com.ra.bioskop.dto.request.user.UpdateUserRequest;
import com.ra.bioskop.service.UserService;
import com.ra.bioskop.util.Constants;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objMapper;

    private DataDummyUser dataDummyUser;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
        objMapper = new ObjectMapper();
        dataDummyUser = new DataDummyUser();
    }

    @Test
    @DisplayName("/update, Positive")
     void testPositiveUpdate() throws Exception {
        UserDTO userDTO = UserMapper.toDto(dataDummyUser.getAllUser().get(0));
        String newName = "sheila99";
        UpdateUserRequest updateUserRequest =
                new UpdateUserRequest(userDTO.getEmail(), newName);

        UserDTO requestToDto = new UserDTO();
        requestToDto.setEmail(userDTO.getEmail());
        requestToDto.setUsername(newName);

        userDTO.setUsername(newName);

        Mockito.when(userService.updateProfile(requestToDto))
                .thenReturn(userDTO);

        String userData = objMapper.writeValueAsString(updateUserRequest);
        MockHttpServletResponse response = mockMvc.perform(post(
                                        Constants.USER_V1_ENDPOINT + "/update")
                                .content(userData)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        JsonNode jsonNode = objMapper.readTree(response.getContentAsString());

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("updated", jsonNode.get("message").asText());
    }

    @Test
    @DisplayName("/delete, Positive")
    void testPositiveDelete() throws Exception {
        String email = "sheila@gmail.com";

        UserDTO userDTO = UserMapper.toDto(dataDummyUser.getAllUser().get(0));

        Mockito.when(userService.deleteByEmail(email))
                .thenReturn(userDTO);

        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("email", email);

        MockHttpServletResponse response =
                mockMvc.perform(
                        delete(
                                Constants.USER_V1_ENDPOINT + "/delete")
                                .params(paramsMap))
                        .andReturn().getResponse();

        JsonNode jsonNode = objMapper.readTree(response.getContentAsString());

        System.out.println(response.getContentAsString());

        Assertions.assertEquals("deleted", jsonNode.get("message").asText());
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}