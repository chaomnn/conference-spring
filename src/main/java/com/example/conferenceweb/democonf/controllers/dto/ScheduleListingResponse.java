package com.example.conferenceweb.democonf.controllers.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScheduleListingResponse {

    private List<ScheduleDto> data;
}