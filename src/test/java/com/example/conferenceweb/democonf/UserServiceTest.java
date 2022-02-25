package com.example.conferenceweb.democonf;

import com.example.conferenceweb.democonf.model.User;
import com.example.conferenceweb.democonf.repositories.UserRepository;
import com.example.conferenceweb.democonf.services.ScheduleService;
import com.example.conferenceweb.democonf.services.TalkService;
import com.example.conferenceweb.democonf.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import javax.persistence.EntityManager;

@DataJpaTest
@Import({UserService.class, ScheduleService.class, TalkService.class})
public class UserServiceTest {

    @MockBean
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void AddUserToRepository() throws Exception {

        User user = new User();
        user.setUsername("a");
        user.setPassword("b");

        entityManager.persist(user);

        userService.saveUser(user);

        Assertions.assertTrue(userRepository.existsByUsername(user.getUsername()));

    }

}
