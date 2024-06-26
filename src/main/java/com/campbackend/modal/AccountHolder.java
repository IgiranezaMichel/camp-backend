package com.campbackend.modal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import java.time.*;
import com.campbackend.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

@NoArgsConstructor
@Data
@Entity
public class AccountHolder {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String name;
    private String gender;
    private LocalDate dob;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Lob
    private byte[] profilePicture;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime timeStamp;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "accountHolder",targetEntity = CampMentor.class)
    @JsonIgnore()
    private List<CampMentor>campMentorList;
    public String getTimeStamp(){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MMM-yyyy MM:ss a");
        return dateTimeFormatter.format(timeStamp);
    }
    public String getProfilePicture(){
        return "data:image/png;base64,"+Base64.getEncoder().encodeToString(profilePicture);
    }
    public AccountHolder(UUID id, String name, String gender, String phoneNumber, String email, String profilePicture,
            String password, Role role,LocalDate dob) {
        this.timeStamp=LocalDateTime.now();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profilePicture = Base64.getDecoder().decode(profilePicture.split("base64,")[1]);
        this.password = password;
        this.role = role;
        this.dob=dob;
    }
}
