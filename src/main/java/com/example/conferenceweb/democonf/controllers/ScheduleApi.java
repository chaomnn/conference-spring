package com.example.conferenceweb.democonf.controllers;

import com.example.conferenceweb.democonf.controllers.dto.ScheduleDto;
import com.example.conferenceweb.democonf.controllers.dto.ScheduleListingResponse;
import com.example.conferenceweb.democonf.model.Schedule;
import com.example.conferenceweb.democonf.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/schedules")
public class ScheduleApi {

    private final ScheduleService service;

    @GetMapping
    public ScheduleListingResponse findAll() {
        return ScheduleListingResponse.builder()
                .data(service.findAll().stream()
                        .map(ScheduleApi::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private static ScheduleDto toDto(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .roomId(schedule.getRoom().getId())
                .talkId(schedule.getTalk().getId())
                .time(schedule.getTime())
                .build();
    }
}