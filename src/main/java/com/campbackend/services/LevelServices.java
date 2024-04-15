package com.campbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

import com.campbackend.input.LevelInput;
import com.campbackend.modal.Levels;
import com.campbackend.repository.LevelRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class LevelServices {
@Autowired private LevelRepository levelRepository;
public ResponseEntity<String>saveOrUpdateLevel(LevelInput level){
    try {
        byte [] image=Base64.getDecoder().decode(level.getBase64Photo().split("base64,")[1]);
        Levels data=levelRepository.save(new Levels(level.getId(), level.getName(), level.getFromAge(), level.getToAge(), image));
        return new ResponseEntity<>(data.getName()+" saved successful",HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(" Level already exist",HttpStatus.METHOD_NOT_ALLOWED);
    }
}
public Levels findById(UUID level){
  return levelRepository.findById(level).orElseThrow();
}
public ResponseEntity<String>delete(UUID id){
    try {
        Levels data=this.findById(id);
        levelRepository.delete(data);
        return new ResponseEntity<>(data.getName()+" removed successful",HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(" Level doesn't exist",HttpStatus.METHOD_NOT_ALLOWED);
    }
}
public List<Levels> getAll(){
    return levelRepository.findAll();
}
@Autowired private EntityManager entityManager;
public UUID levelBetweenUserDob(int age){
try {
TypedQuery<Levels>query=entityManager.createQuery("select u from Levels u WHERE u.fromAge<=:age AND u.toAge>=:age",Levels.class);
query.setParameter("age", age);
Levels levelList=query.getSingleResult();
if(levelList==null) throw new Exception("Level not found exception");
return levelList.getId();
} catch (Exception e) {
   return null;
}
}
}
