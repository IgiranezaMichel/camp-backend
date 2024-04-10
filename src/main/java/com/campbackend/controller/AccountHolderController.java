package com.campbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;

import com.campbackend.input.AccountHolderInput;
import com.campbackend.input.PageInput;
import com.campbackend.pagination.AccountHolderPage;
import com.campbackend.services.AccountHolderServices;

public class AccountHolderController {
    @Autowired private AccountHolderServices accountHolderServices;
    @MutationMapping
    public ResponseEntity<String>saveOrUpdateBook(@Argument(name="accountHolderInput")AccountHolderInput accountHolderInput){
        return accountHolderServices.saveOrUpdateAccountHolder(accountHolderInput);
    }
    @MutationMapping
    public ResponseEntity<String>deleteAccountHolder(@Argument(name="id")UUID id){
        return accountHolderServices.deleteAccountHolder(id);
    }
    @QueryMapping
    public AccountHolderPage accountHolderPage(@Argument(name = "input")PageInput page){
        return accountHolderServices.accountPage(page);
    }
}