package com.campbackend.modal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Data
@NoArgsConstructor
@Entity
public class Camp {
    @Id @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String title;
    private int cost; 
    private String address; 
    private String location; 
    private LocalDate startingDate; 
    private LocalDate endingDate;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Levels.class)
    private Levels levels;
    @Column(columnDefinition = "text")
    private String content;
    private LocalDateTime timeStamp;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = CampMentor.class,mappedBy = "camp")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CampMentor>campMentorList;
    public Camp(UUID id, String title, int cost, String address, String location, LocalDate startingDate,
            LocalDate endingDate, String description, String content) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.address = address;
        this.location = location;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.description = description;
        this.content = content;
        setTimeStamp(LocalDateTime.now()); 
   }
}
