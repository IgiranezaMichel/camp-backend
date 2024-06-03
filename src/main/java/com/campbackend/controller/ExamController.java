package com.campbackend.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.campbackend.input.ExamInput;
import com.campbackend.modal.Exam;
import com.campbackend.services.ExamService;

@Controller
public class ExamController {
@Autowired ExamService examService;
@MutationMapping
public ResponseEntity<String> saveExam(@Argument(name = "examInput")ExamInput examInput){
    return examService.save(examInput);
}
@MutationMapping
public ResponseEntity<String> deleteExam(@Argument(name = "id")UUID id){
    return examService.delete(id);
}
@QueryMapping
public List<Exam>getAllExams(){
    return examService.getAllExams();
}
}
