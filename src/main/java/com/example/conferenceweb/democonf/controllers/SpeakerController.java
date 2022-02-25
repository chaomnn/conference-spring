package com.example.conferenceweb.democonf.controllers;

import com.example.conferenceweb.democonf.controllers.dto.ScheduleRequest;
import com.example.conferenceweb.democonf.model.Room;
import com.example.conferenceweb.democonf.model.Schedule;
import com.example.conferenceweb.democonf.model.Talk;
import com.example.conferenceweb.democonf.model.User;
import com.example.conferenceweb.democonf.services.RoomService;
import com.example.conferenceweb.democonf.services.ScheduleService;
import com.example.conferenceweb.democonf.services.TalkService;
import com.example.conferenceweb.democonf.services.exception.ScheduleConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
public class SpeakerController {
    @Autowired
    private TalkService talkService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/speaker")
    public String talkList(Model model) {
        model.addAttribute("allTalks", talkService.allTalks());
        model.addAttribute("rooms", roomService.findAll());
        return "speaker";
    }

    @RequestMapping("/speaker/delete/{id}")
    public String deleteTalk(@PathVariable Long id) {
        scheduleService.delete(id);
        //talkService.delete(id);
        return "redirect:/speaker";
    }

    @RequestMapping("/speaker/add")
    public String addTalk(@ModelAttribute ScheduleRequest scheduleRequest, Model model) {
        Schedule schedule = new Schedule();
        schedule.setTalk(new Talk());
        schedule.getTalk().setName(scheduleRequest.getTalkName());
        schedule.setRoom(roomService.findById(scheduleRequest.getRoomId()));
        schedule.setTime(LocalTime.parse(scheduleRequest.getTime()));

        try {
            scheduleService.schedule(schedule);
        } catch (ScheduleConflictException e) {
            model.addAttribute("talkError", "Это время в этой аудитории уже занято.");
        }

        return "redirect:/speaker";
    }

}
