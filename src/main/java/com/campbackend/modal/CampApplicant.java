package com.campbackend.modal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.campbackend.enums.CampApplicantStatus;

import java.time.*;
@Data
@NoArgsConstructor
@Entity
public class CampApplicant {
@Id @UuidGenerator(style =Style.AUTO)
private UUID id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Camp.class)
private Camp camp;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = AccountHolder.class)
private AccountHolder accountHolder;
@Enumerated(EnumType.STRING)
private CampApplicantStatus campApplicantStatus;
private LocalDate timeStamp;
public CampApplicant(UUID id, Camp camp, AccountHolder accountHolder, LocalDate timeStamp,CampApplicantStatus campApplicantStatus) {
    this.id = id;
    this.camp = camp;
    this.accountHolder = accountHolder;
    this.timeStamp = timeStamp;
    this.campApplicantStatus=campApplicantStatus;
}
}
