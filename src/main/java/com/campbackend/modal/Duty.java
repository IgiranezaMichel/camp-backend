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
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class Duty {
@Id @UuidGenerator(style =Style.AUTO)
private UUID id;
private String name;
private String description;
@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,optional = false)
@OnDelete(action = OnDeleteAction.SET_NULL)
private AccountHolder accountHolder;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Church.class)
@OnDelete(action = OnDeleteAction.CASCADE)
private Church church;
public Duty(UUID id, String name, String description, AccountHolder accountHolder, Church church) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.accountHolder = accountHolder;
    this.church = church;
}
}
