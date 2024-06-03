package com.campbackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.input.AyGradeInput;
import com.campbackend.modal.AyGrade;
import com.campbackend.modal.Levels;
import com.campbackend.repository.AyGradeRepository;

@Service
public class AyGradeServices {
@Autowired AyGradeRepository ayGradeRepository;
@Autowired LevelServices levelServices;

public ResponseEntity<String> save(AyGradeInput ayGradeInput) {
try {
    if(ayGradeInput.getGradeName().equals(""))throw new Exception("Ay Rank is required");
    if(ayGradeInput.getLevelId().equals(null))throw new Exception("Ay Level is required");
    Levels levels=levelServices.findById(ayGradeInput.getLevelId());
   AyGrade ayGrade= ayGradeRepository.save(new AyGrade(ayGradeInput.getId(), ayGradeInput.getGradeName(), levels));
   return new ResponseEntity<>(ayGrade.getGradeName()+" has saved successful",HttpStatus.CREATED);
} catch (Exception e) {
    if(e instanceof NullPointerException){
        return new ResponseEntity<>("Ay level is required",HttpStatus.METHOD_NOT_ALLOWED);  
    }
   return new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
}
}
public ResponseEntity<String>  deleteAyGrade(UUID id){
ayGradeRepository.deleteById(id);
return new ResponseEntity<>("Ay Grade removed successful",HttpStatus.OK);
}
public AyGrade findAllById(UUID id){
    return ayGradeRepository.findById(id).orElse(null);
}
public List<AyGrade> getAllGrades() {
return ayGradeRepository.findAll();
}

}
