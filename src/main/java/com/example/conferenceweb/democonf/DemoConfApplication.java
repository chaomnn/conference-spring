package com.example.conferenceweb.democonf;

import com.example.conferenceweb.democonf.model.Room;
import com.example.conferenceweb.democonf.model.Schedule;
import com.example.conferenceweb.democonf.model.Talk;
import com.example.conferenceweb.democonf.repositories.RoleRepository;
import com.example.conferenceweb.democonf.services.ScheduleService;
import com.example.conferenceweb.democonf.services.exception.ScheduleConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalTime;

@SpringBootApplication
public class DemoConfApplication {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(DemoConfApplication.class, args);
	}

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ScheduleService service;

	//@PostConstruct
	public void init() {
		Room room = new Room();
		Room room1 = new Room();

		transactionTemplate.executeWithoutResult($ -> {
			Talk talk = new Talk();
			talk.setName("talk-1");

			room.setName(100);

			entityManager.persist(talk);
			entityManager.persist(room);

			Schedule schedule = new Schedule();
			schedule.setTalk(talk);
			schedule.setRoom(room);
			schedule.setTime(LocalTime.now());

			try {
				service.schedule(schedule);
			} catch (ScheduleConflictException e) {
				e.printStackTrace();
			}
		});


		transactionTemplate.executeWithoutResult($ -> {
			Talk talk = new Talk();
			talk.setName("talk-2");

			entityManager.persist(talk);

			room1.setName(2);

			entityManager.persist(room1);

			Schedule schedule = new Schedule();
			schedule.setTalk(talk);
			schedule.setRoom(room1);
			schedule.setTime(LocalTime.now().plusMinutes(60));

			try {
				service.schedule(schedule);
			} catch (ScheduleConflictException e) {
				e.printStackTrace();
			}
		});

	}

/*
	@Bean
	CommandLineRunner initRoles(RoleRepository repository) {
		for (String role: roles) {
			if (!repository.findByName(ROLE_ADMIN)) {
				repository.save(role);
			}
		}
	}
*/
}
