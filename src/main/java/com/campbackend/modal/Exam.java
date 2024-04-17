package com.campbackend.modal;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Exam {
@Id @UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
@Column(columnDefinition = "text")
private String instruction;
}
