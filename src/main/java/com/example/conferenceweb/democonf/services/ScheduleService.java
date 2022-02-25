package com.example.conferenceweb.democonf.services;

import com.example.conferenceweb.democonf.model.Schedule;
import com.example.conferenceweb.democonf.repositories.ScheduleRepository;
import com.example.conferenceweb.democonf.services.exception.ScheduleConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    private final TalkService talkService;

    @Transactional
    public void schedule(Schedule schedule) throws ScheduleConflictException {

        if (schedule.getTalk().getId() == null) {
            talkService.save(schedule.getTalk());
        }

        if (repository.existsByRoomAndTime(schedule.getRoom(), schedule.getTime())) {
            throw new ScheduleConflictException();
        }

        repository.save(schedule);
    }

    public void delete(Long id) {
        talkService.delete(id);
    }

    public List<Schedule> findAll() {
        return repository.findAll();
    }
}