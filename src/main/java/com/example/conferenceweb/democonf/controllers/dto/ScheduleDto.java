package com.example.conferenceweb.democonf.controllers.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class ScheduleDto {

    private long id;

    private LocalTime time;

    private long roomId;

    private long talkId;
}