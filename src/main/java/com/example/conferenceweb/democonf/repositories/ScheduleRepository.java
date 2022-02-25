package com.example.conferenceweb.democonf.repositories;

import com.example.conferenceweb.democonf.model.Room;
import com.example.conferenceweb.democonf.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByRoomAndTime(Room room, LocalTime time);
}