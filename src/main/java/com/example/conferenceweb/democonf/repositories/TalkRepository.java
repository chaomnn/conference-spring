package com.example.conferenceweb.democonf.repositories;

import com.example.conferenceweb.democonf.model.Room;
import com.example.conferenceweb.democonf.model.Talk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface TalkRepository extends JpaRepository<Talk, Long> {
//    Talk findByTime(LocalTime time);
//    Talk findByRoom(Room room);
}
