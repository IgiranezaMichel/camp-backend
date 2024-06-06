package com.campbackend.modal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.*;
import java.util.*;
import java.time.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String name;
    @Lob
    private byte[] file;
    private String fileEncoding;
    @ManyToOne(targetEntity = Levels.class, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Levels levels;
    @Lob
    private byte[] cover;
    private String author;
    private LocalDate publicationDate;
    private String publisher;
    @Column(unique = true)
    private String serialNumber;

    public String getCover() {
        return "data:image/png;base64,"+Base64.getEncoder().encode(cover);
    }

    public String getFile() {
        return Base64.getEncoder().encodeToString(file);
    }

    public Book(UUID id, String name, String file, Levels levels, String cover, String author,
            LocalDate publicationDate, String publisher, String serialNumber) {
        this.id = id;
        this.name = name;
        this.file = Base64.getDecoder().decode(file.split("base64,")[1]);
        this.fileEncoding = file.split("base64,")[0] + "base64,";
        this.levels = levels;
        this.cover = Base64.getDecoder().decode(cover.split("base64,")[1]);
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.serialNumber = serialNumber;
    }
}
