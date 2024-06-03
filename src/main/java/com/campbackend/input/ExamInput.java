package com.campbackend.input;

import java.util.UUID;

import com.campbackend.modal.Exam;

import lombok.Getter;
import lombok.Setter;

public class ExamInput extends Exam{
    @Getter @Setter
private UUID ayGradeId;
}
