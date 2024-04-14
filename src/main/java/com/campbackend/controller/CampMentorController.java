package com.campbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.*;
import com.campbackend.input.CampMentorInput;
import com.campbackend.modal.CampMentor;
import com.campbackend.services.CampMentorServices;

@Controller
public class CampMentorController {
@Autowired private CampMentorServices campMentorServices;
@MutationMapping
public ResponseEntity<String>saveOrUpdateCampMentor(@Argument(name = "input")CampMentorInput campMentorInput){
    return campMentorServices.saveOrUpdateCampMentor(campMentorInput);
}
@MutationMapping
public ResponseEntity<String>deleteCampMentor(@Argument(name = "id")UUID camMentorId){
    return campMentorServices.deleteCampMentor(camMentorId);
}
@QueryMapping
public List<CampMentor>getCampMentorList(@Argument(name = "campId")UUID campId){
    return campMentorServices.getCampMentorList(campId);
}

}
