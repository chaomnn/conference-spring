package com.example.conferenceweb.democonf.controllers.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleRequest {

    private String talkName;

    private long roomId;

    private String time;
}
