package com.campbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.campbackend.input.AccountHolderInput;
import com.campbackend.input.PageInput;
import com.campbackend.modal.AccountHolder;
import com.campbackend.pagination.AccountHolderPage;
import com.campbackend.services.AccountHolderServices;

@Controller
public class AccountHolderController {
    @Autowired
    private AccountHolderServices accountHolderServices;

    @MutationMapping
    public ResponseEntity<String> saveOrUpdateAccountHolder(
            @Argument(name = "accountHolderInput") AccountHolderInput accountHolderInput,@Argument(name = "churchId")UUID churchId) {
        return accountHolderServices.saveOrUpdateAccountHolder(accountHolderInput,churchId);
    }

    @MutationMapping
    public ResponseEntity<String> deleteAccountHolder(@Argument(name = "id") UUID id) {
        return accountHolderServices.deleteAccountHolder(id);
    }

    @QueryMapping
    public AccountHolderPage accountHolderPage(@Argument(name = "input") PageInput page) {
        return accountHolderServices.accountPage(page);
    }

    @MutationMapping
    public AccountHolder findByEmail(@Argument(name = "email") String email) {
        return accountHolderServices.findByEmail(email);
    }
    @MutationMapping
    public ResponseEntity<String> updateAccountHolderPassword(@Argument(name = "accountHolderEmail")String accountHolderEmail,@Argument(name = "oldPassword")String oldPassword,@Argument(name = "newPassword")String newPassword){
        return accountHolderServices.updateAccountHolderPassword(accountHolderEmail, oldPassword, newPassword);
    }

}
