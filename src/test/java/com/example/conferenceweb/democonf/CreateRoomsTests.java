package com.example.conferenceweb.democonf;

import com.example.conferenceweb.democonf.model.Room;
import com.example.conferenceweb.democonf.repositories.RoomRepository;
import com.example.conferenceweb.democonf.services.RoomService;
import com.example.conferenceweb.democonf.services.ScheduleService;
import com.example.conferenceweb.democonf.services.TalkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;


@DataJpaTest
@Import({RoomService.class, ScheduleService.class, TalkService.class})
public class CreateRoomsTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RoomService service;

    @Autowired
    private RoomRepository repository;

    @Test
    void createRooms() throws Exception {
        Room room1 = new Room(1);

        Room room2 = new Room();
        room1.setName(2);

        Room room3 = new Room();
        room1.setName(3);

        Room room100 = new Room();
        room1.setName(100);

        entityManager.persist(room1);
        entityManager.persist(room2);
        entityManager.persist(room3);
        entityManager.persist(room100);

    }
}
