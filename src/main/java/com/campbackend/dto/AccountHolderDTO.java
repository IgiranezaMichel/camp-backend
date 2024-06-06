package com.campbackend.dto;

import java.time.LocalDate;
import java.util.UUID;
import com.campbackend.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountHolderDTO {
 private UUID id;
    private String name;
    private String gender;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String profilePicture;
    private Role role;
}
