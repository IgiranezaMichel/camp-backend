package com.campbackend.modal;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AyGrade {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @Column(unique = true)
    private String gradeName;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Levels.class,fetch = FetchType.LAZY,optional = false)
    private Levels levels;
    public AyGrade(UUID id, String gradeName, Levels levels) {
        this.id = id;
        this.gradeName = gradeName;
        this.levels = levels;
    }
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Exam.class,mappedBy ="ayGrade")
    private List<Exam>examList;
}
