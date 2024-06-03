package com.campbackend.controller;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.campbackend.input.AyGradeInput;
import com.campbackend.modal.AyGrade;
import com.campbackend.services.AyGradeServices;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;

@Controller
public class AyGradeController {
    @Autowired
    AyGradeServices ayGradeServices;
    @MutationMapping
    public ResponseEntity<String> saveAyGrade(@Argument(name = "ayGradeInput") AyGradeInput ayGradeInput) {
        return ayGradeServices.save(ayGradeInput);
    }
    @MutationMapping
    public ResponseEntity<String> deleteAyGrade(@Argument(name = "ayGradeId") UUID id) {
        return ayGradeServices.deleteAyGrade(id);
    }
    @QueryMapping
    public List<AyGrade>getAllAyGrade(){
        return ayGradeServices.getAllGrades();
    }
}
