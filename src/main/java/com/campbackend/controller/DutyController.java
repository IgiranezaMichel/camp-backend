package com.campbackend.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.campbackend.enums.Role;
import com.campbackend.input.DutyInput;
import com.campbackend.input.PageInput;
import com.campbackend.modal.Duty;
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
    public List<Duty>findUserWorkingAtTheSameChurch(@Argument(name="church")UUID churchId){
        return dutyServices.findUserWorkingAtTheSameChurch(churchId);
    }
    @QueryMapping
    public DutyPage dutyPage(@Argument(name = "input")PageInput page){
        return dutyServices.dutyPage(page);
    }
    @QueryMapping
    public DutyPage findAccountHolderHavingSameRole(@Argument(name = "input")PageInput page,@Argument(name = "role")Role role){
        return dutyServices.findAccountHolderHavingSameRole(page,role);
    }
}
