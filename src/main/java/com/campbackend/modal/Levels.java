package com.campbackend.modal;

import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public String getPhoto(){
        return "data:image/png;base64,"+Base64.encodeBase64String(photo);
    }
}
