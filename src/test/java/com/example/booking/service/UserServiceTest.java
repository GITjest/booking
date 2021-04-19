package com.example.booking.service;

import com.example.booking.exception.UserNotFoundException;
import com.example.booking.model.User;
import com.example.booking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void getUser_returnUser() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dateOfBirth = dateFormat.parse("2000-11-11");

        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "Adam", dateOfBirth, "adam@mail.com", "male")));

        User user = userService.getUser(1L);

        assertThat(user.getUserId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("Adam");
        assertThat(user.getDateOfBirth()).isEqualTo(dateOfBirth);
        assertThat(user.getEmail()).isEqualTo("adam@mail.com");
        assertThat(user.getSex()).isEqualTo("male");
    }

    @Test
    public void getUser_whenUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.getUser(1L));
    }

}