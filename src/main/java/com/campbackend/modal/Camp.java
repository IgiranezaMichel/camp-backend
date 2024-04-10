package com.campbackend.modal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    // levels
    private String content;
    private LocalDateTime timeStamp;
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
