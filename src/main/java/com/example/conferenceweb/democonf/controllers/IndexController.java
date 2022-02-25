package com.example.conferenceweb.democonf.controllers;

import com.example.conferenceweb.democonf.model.Room;
import com.example.conferenceweb.democonf.model.Schedule;
import com.example.conferenceweb.democonf.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ScheduleService scheduleService;

    @GetMapping(path = {"/", "/home"})
    public String home(Model model) {
        model.addAttribute("scheduleHeaders", scheduleService.findAll().stream()
                .map(Schedule::getRoom)
                .map(Room::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList()));

        Map<Integer, List<Schedule>> map = scheduleService.findAll().stream()
                .collect(Collectors.groupingBy(s -> s.getRoom().getName()));

        Map<Integer, List<Schedule>> sortedMap = new TreeMap<>();
        sortedMap.putAll(map);

        model.addAttribute("roomSchedules", sortedMap);

        return "index";
    }
}