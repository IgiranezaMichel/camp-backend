package com.campbackend.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.campbackend.input.ChurchInput;
import com.campbackend.input.PageInput;
import com.campbackend.pagination.ChurchPage;
import com.campbackend.services.ChurchServices;

@Controller
public class ChurchController {
    @Autowired private ChurchServices churchServices;
    @MutationMapping
    public ResponseEntity<String>saveOrUpdateChurch(@Argument(name="churchInput")ChurchInput churchInput){
        return churchServices.saveOrUpdateChurch(churchInput);
    }
    @MutationMapping
    public ResponseEntity<String>deleteChurch(@Argument(name="id")UUID church){
        return churchServices.deleteChurch(church);
    }
    @QueryMapping
    public ChurchPage churchPage(@Argument(name = "input")PageInput page){
        return churchServices.churchPage(page);
    }
}
