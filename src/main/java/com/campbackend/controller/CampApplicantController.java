package com.campbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.campbackend.input.CampApplicantInput;
import com.campbackend.input.CampApplicantPage;
import com.campbackend.input.PageInput;
import com.campbackend.services.CampApplicantServices;

@Controller
public class CampApplicantController {
    @Autowired
    private CampApplicantServices campApplicantServices;

    @MutationMapping
    public ResponseEntity<String> saveOrUpdateCampApplicant(
            @Argument(name = "campApplicantInput") CampApplicantInput campApplicantInput) {
        return campApplicantServices.saveOrUpdateCampApplicant(campApplicantInput);
    }

    @QueryMapping
    public CampApplicantPage campApplicantPage(@Argument(name = "input") PageInput page,@Argument(name = "campId")UUID campId) {
        return campApplicantServices.campApplicantPage(campId,page);
    }

    @QueryMapping
    public CampApplicantPage accountHolderCampApplicationPage(@Argument(name = "input") PageInput page,
            @Argument(name = "email") String email) {
        return campApplicantServices.accountHolderCampApplicationPage(page, email);
    }
}
