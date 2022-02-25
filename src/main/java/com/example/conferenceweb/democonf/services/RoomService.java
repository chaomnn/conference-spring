package com.example.conferenceweb.democonf.services;

import com.example.conferenceweb.democonf.model.Room;
import com.example.conferenceweb.democonf.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository repository;

    public List<Room> findAll() {
        return repository.findAll();
    }

    public Room findById(long roomId) {
        return repository.getById(roomId);
    }
}
