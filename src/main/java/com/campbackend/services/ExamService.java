package com.campbackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.input.ExamInput;
import com.campbackend.modal.AyGrade;
import com.campbackend.modal.Exam;
import com.campbackend.repository.ExamRepository;

@Service
public class ExamService {
    @Autowired ExamRepository examRepository;
    @Autowired AyGradeServices ayGradeServices;

    public ResponseEntity<String> save(ExamInput examInput) {
        AyGrade ayGrade=ayGradeServices.findAllById(examInput.getAyGradeId());
       Exam exam= examRepository.save(new Exam(examInput.getId(), examInput.getName(), examInput.getDescription(), examInput.getInstruction(), ayGrade));
       return new ResponseEntity<>(exam.getName()+" saved successful",HttpStatus.CREATED);
    }

    public ResponseEntity<String> delete(UUID id) {
        examRepository.deleteById(id);
         return new ResponseEntity<>("Exam removed successful",HttpStatus.OK);
    }

    public List<Exam> getAllExams() {
     return examRepository.findAll();
    }

}
