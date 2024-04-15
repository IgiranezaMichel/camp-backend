package com.campbackend.modal;

import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Levels {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @Column(unique=true)
    private String name;
    private int fromAge;
    private int toAge;
    private byte[] photo;
    @ManyToMany(mappedBy = "levels")
    private List<Camp> campList;
    public String getPhoto(){
        return "data:image/png;base64,"+Base64.encodeBase64String(photo);
    }
    public Levels(UUID id,String name,int fromAge,int toAge,byte[] photo){
        this.setId(id);
        this.setName(name);
        this.setFromAge(fromAge);
        this.setToAge(toAge);
        this.setPhoto(photo);
    }
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Book.class,mappedBy = "levels")
    public List<Book> bookList;
}
