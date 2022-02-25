package com.example.conferenceweb.democonf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_talk")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Talk {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    private String name;
    @ManyToMany
    private List<User> speakers = new ArrayList<>();

    @OneToMany(mappedBy="talk", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Schedule> schedules;
}