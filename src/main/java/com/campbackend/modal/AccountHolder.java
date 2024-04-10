package com.campbackend.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;
import java.util.*;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.campbackend.enums.Role;

@NoArgsConstructor
@Data
@Entity
public class AccountHolder {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    @Lob
    private byte[] profilePicture;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    public String getProfilePicture(){
        return Base64.getEncoder().encodeToString(profilePicture);
    }
    public AccountHolder(UUID id, String name, String gender, String phoneNumber, String email, String profilePicture,
            String password, Role role) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profilePicture = Base64.getDecoder().decode(profilePicture.split("base64,")[1]);
        this.password = password;
        this.role = role;
    }
}