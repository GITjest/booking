package com.example.booking.repository;

import com.example.booking.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById_returnUser() {
        Boolean result = userRepository.findById(1L).isPresent();

        assertThat(result).isTrue();
    }

    @Test
    public void save_returnUser() {
        User user = userRepository.save(new User(1L, "Adam", new Date(), "adam@mail.com", "male"));

        assertThat(user).isNotNull();
    }


}