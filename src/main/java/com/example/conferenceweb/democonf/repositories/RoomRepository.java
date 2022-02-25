package com.example.conferenceweb.democonf.repositories;

import com.example.conferenceweb.democonf.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
