package javadocq.indiflow.service;

import static org.junit.jupiter.api.Assertions.*;

import javadocq.indiflow.domain.User;
import javadocq.indiflow.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @Test
    public void 회원가입() throws Exception {
        User user = new User("test", "test", "test@test.com");
        userService.join(user);
        User saveUser = userRepository.findByUsername("test");
        Assertions.assertEquals("test", saveUser.getUsername());
    }

}