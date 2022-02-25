package com.example.conferenceweb.democonf;

import com.example.conferenceweb.democonf.model.Room;
import com.example.conferenceweb.democonf.model.Schedule;
import com.example.conferenceweb.democonf.model.Talk;
import com.example.conferenceweb.democonf.repositories.ScheduleRepository;
import com.example.conferenceweb.democonf.services.ScheduleService;
import com.example.conferenceweb.democonf.services.TalkService;
import com.example.conferenceweb.democonf.services.exception.ScheduleConflictException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.time.LocalTime;

@DataJpaTest
@Import({ScheduleService.class, TalkService.class})
class ScheduleServiceTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ScheduleService service;

    @Autowired
    private TalkService talkService;

    @Autowired
    private ScheduleRepository repository;

    @Test
    void scheduleTalk() throws Exception {
        Talk talk = new Talk();
        talk.setName("talk");

        Room room = new Room();
        room.setName(201);

        entityManager.persist(talk);
        entityManager.persist(room);

        Schedule schedule = new Schedule();
        schedule.setTalk(talk);
        schedule.setRoom(room);
        schedule.setTime(LocalTime.now());

        service.schedule(schedule);

        Assertions.assertTrue(repository.existsByRoomAndTime(schedule.getRoom(), schedule.getTime()));
    }

    @Test
    void allowOnlyOneTalkAtSameTime() throws Exception {
        Talk talk = new Talk();
        talk.setName("talk");

        Room room = new Room();
        room.setName(201);

        entityManager.persist(talk);
        entityManager.persist(room);

        Schedule schedule = new Schedule();
        schedule.setTalk(talk);
        schedule.setRoom(room);
        schedule.setTime(LocalTime.now());

        Schedule scheduleAtSameTime = schedule.toBuilder().build();

        service.schedule(schedule);

        Assertions.assertThrows(ScheduleConflictException.class, () -> service.schedule(scheduleAtSameTime));
    }
}