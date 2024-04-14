package com.campbackend.input;

import com.campbackend.modal.CampMentor;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

public class CampMentorInput extends CampMentor {
    @Getter
    @Setter
    private UUID campId;
    @Getter
    @Setter
    private String accountHolderEmail;
}
