package com.campbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.campbackend.input.DutyInput;
import com.campbackend.input.PageInput;
import com.campbackend.pagination.DutyPage;
import com.campbackend.services.DutyServices;


@Controller
public class DutyController {
    @Autowired private DutyServices dutyServices;
    @MutationMapping
    public ResponseEntity<String>saveOrUpdateDuty(@Argument(name="dutyInput")DutyInput dutyInput){
        return dutyServices.saveOrUpdateDuty(dutyInput);
    }
    @MutationMapping
    public ResponseEntity<String>deleteDuty(@Argument(name="id")UUID level){
        return dutyServices.deleteDuty(level);
    }
    @QueryMapping
    public DutyPage dutyPage(@Argument(name = "input")PageInput page){
        return dutyServices.dutyPage(page);
    }
}
