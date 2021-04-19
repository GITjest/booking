package com.example.booking.controller;

import com.example.booking.exception.UserNotFoundException;
import com.example.booking.model.User;
import com.example.booking.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void getUser_returnUser() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dateOfBirth = dateFormat.parse("2000-11-11");
        User user = new User(1L, "Adam", dateOfBirth, "adam@mail.com", "male");

        when(userService.getUser(anyLong())).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userId").value("1"))
                .andExpect(jsonPath("name").value("Adam"))
                .andExpect(jsonPath("dateOfBirth").value("2000-11-11"))
                .andExpect(jsonPath("email").value("adam@mail.com"))
                .andExpect(jsonPath("sex").value("male"));
    }

    @Test
    public void getUser_notFound() throws Exception {
        when(userService.getUser(anyLong())).thenThrow(new UserNotFoundException(""));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addUser_return201AndUser() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dateOfBirth = dateFormat.parse("2000-11-11");
        User user = new User(1L, "Adam", dateOfBirth, "adam@mail.com", "male");

        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("userId").value("1"))
                .andExpect(jsonPath("name").value("Adam"))
                .andExpect(jsonPath("dateOfBirth").value("2000-11-11"))
                .andExpect(jsonPath("email").value("adam@mail.com"))
                .andExpect(jsonPath("sex").value("male"));
    }

}
