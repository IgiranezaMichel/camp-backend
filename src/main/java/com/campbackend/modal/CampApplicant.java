package com.campbackend.modal;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.campbackend.enums.CampApplicantStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class CampApplicant {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Camp.class)
    private Camp camp;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = AccountHolder.class)
    private AccountHolder accountHolder;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Levels.class)
    private Levels levels;
    @Enumerated(EnumType.STRING)
    private CampApplicantStatus campApplicantStatus;
    private String paymentCode;
    private String telephone;
    @Column(columnDefinition = "text")
    private String comment;
    private LocalDate timeStamp;

    public CampApplicant(UUID id, Camp camp, AccountHolder accountHolder, LocalDate timeStamp,
            CampApplicantStatus campApplicantStatus, Levels levels, String telephone, String paymentCode,
            String comment) {
        this.id = id;
        this.camp = camp;
        this.accountHolder = accountHolder;
        this.timeStamp = timeStamp;
        this.campApplicantStatus = campApplicantStatus;
        this.setLevels(levels);
        this.telephone = telephone;
        this.comment = comment;
        this.paymentCode = paymentCode;
    }
}
