package com.campbackend.modal;

import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class CampMentor {
@Id @UuidGenerator(style = Style.AUTO)
private UUID id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Camp.class)
@OnDelete(action = OnDeleteAction.CASCADE)
private Camp camp;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = AccountHolder.class)
@OnDelete(action = OnDeleteAction.CASCADE)
private AccountHolder accountHolder;
private String role;
private String description;
public CampMentor(UUID id, Camp camp, AccountHolder accountHolder, String role, String description) {
    this.id = id;
    this.camp = camp;
    this.accountHolder = accountHolder;
    this.role = role;
    this.description = description;
}

}
