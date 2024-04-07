package com.campbackend.modal;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.*;
import java.util.*;
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
    private byte [] file;
    private String fileEncoding;
    @ManyToOne(targetEntity = Levels.class,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Levels levels;
    @Lob
    private byte [] cover;
    public Book(UUID id,String name,String file,Levels levels,String cover){
        this.id=id;
        this.name=name;
        this.file=Base64.decodeBase64(file.split("base64,")[1]);
        this.fileEncoding=file.split("base64,")[0]+"base64,";
        this.levels=levels;
        this.cover=Base64.decodeBase64(cover.split("base64,")[1]);
    }
}
