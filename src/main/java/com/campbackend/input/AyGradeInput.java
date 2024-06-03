package com.campbackend.input;

import java.util.UUID;

import com.campbackend.modal.AyGrade;

import lombok.Getter;
import lombok.Setter;

public class AyGradeInput extends AyGrade{
@Getter @Setter
private UUID levelId;
}
