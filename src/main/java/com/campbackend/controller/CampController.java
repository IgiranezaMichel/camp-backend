package com.campbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.campbackend.input.CampInput;
import com.campbackend.input.PageInput;
import com.campbackend.pagination.CampPage;
import com.campbackend.services.CampServices;
@Controller
public class CampController {
    @Autowired private CampServices campServices;
    @MutationMapping
    public ResponseEntity<String>saveOrUpdateCamp(@Argument(name="campInput")CampInput camp){
        return campServices.saveOrUpdateCamp(camp);
    }
    @MutationMapping
    public ResponseEntity<String>deleteCamp(@Argument(name="id")UUID id){
        return campServices.deleteCamp(id);
    }
    @QueryMapping
    public CampPage campPage(@Argument(name = "input")PageInput page){
        return campServices.campPage(page);
    }
    @QueryMapping
    public CampPage inactiveCamp(@Argument(name = "input")PageInput page){
        return campServices.inActiveCamp(page);
    }
    @QueryMapping
    public CampPage activeCamp(@Argument(name = "input")PageInput page){
        return campServices.activeCamp(page);
    }
}
