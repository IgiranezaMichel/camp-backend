package com.campbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.*;
import java.security.Principal;
import com.campbackend.input.LevelInput;
import com.campbackend.modal.Levels;
import com.campbackend.services.LevelServices;

@Controller
public class LevelController {
@Autowired private LevelServices levelServices;
@MutationMapping
public ResponseEntity<String>saveOrUpdateLevel(@Argument(name="levelInput")LevelInput levelInput){
    return levelServices.saveOrUpdateLevel(levelInput);
}
@MutationMapping
public ResponseEntity<String>deleteLevel(@Argument(name="id")UUID level){
    return levelServices.delete(level);
}
@QueryMapping
public List<Levels>getAllLevels(Principal principle){
    System.out.println(principle.getName());
    return levelServices.getAll();
}
}
