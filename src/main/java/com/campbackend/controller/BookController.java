package com.campbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.campbackend.input.BookInput;
import com.campbackend.input.PageInput;
import com.campbackend.pagination.BookPage;
import com.campbackend.services.BookServices;
import java.util.*;
@Controller
public class BookController {
    @Autowired private BookServices bookServices;
    @MutationMapping
    public ResponseEntity<String>saveOrUpdateBook(@Argument(name="bookInput")BookInput bookInput){
        return bookServices.saveOrUpdateBook(bookInput);
    }
    @MutationMapping
    public ResponseEntity<String>deleteBook(@Argument(name="id")UUID level){
        return bookServices.deleteBook(level);
    }
    @QueryMapping
    public BookPage bookPage(@Argument(name = "input")PageInput page){
        return bookServices.bookPage(page);
    }
    @QueryMapping
    public BookPage accountHolderBookPage(@Argument(name = "input")PageInput page,@Argument(name = "accountHolderId")UUID accountHolderId){
        return bookServices.accountHolderBookPage(page,accountHolderId);
    }
}
