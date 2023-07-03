package com.campbackend.modal;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.campbackend.enums.ChurchType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class Church {
    @Id @UuidGenerator(style =Style.AUTO)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ChurchType type;
    @ManyToOne(targetEntity = Church.class)
    private Church church;
    private String location;
    @OneToMany(mappedBy ="church",targetEntity = Church.class)
    private List<Church> churchList;
    @OneToMany(mappedBy="church",targetEntity = Duty.class)
    private List<Duty>listOfDuty;
    public Church(UUID id, String name, ChurchType type, Church church,String location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.church = church;
        this.location=location;
    }
}
