package com.example.conferenceweb.democonf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


    @ManyToOne(cascade= CascadeType.REMOVE)
    @JoinColumn(name = "talk_id")
    private Talk talk;

}
