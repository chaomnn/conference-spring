package com.example.conferenceweb.democonf.services;

import com.example.conferenceweb.democonf.model.Talk;
import com.example.conferenceweb.democonf.model.User;
import com.example.conferenceweb.democonf.repositories.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TalkService {
    @Autowired
    private TalkRepository talkRepository;

    public List<Talk> allTalks() {
        return talkRepository.findAll();
    }

    public Talk save(Talk talk) {
        return talkRepository.save(talk);
    }

    public void delete(Long id) {
        talkRepository.deleteById(id);
    }

}
