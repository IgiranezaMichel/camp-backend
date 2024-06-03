package com.campbackend.modal;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exam {
@Id @UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
private String description;
@Column(columnDefinition = "text")
private String instruction;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = AyGrade.class)
private AyGrade ayGrade;
}
