package com.campbackend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campbackend.modal.AccountHolder;
import com.campbackend.repository.AccountHolderRepository;
@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class IndexController {
  @Autowired AccountHolderRepository aServices;
    @RequestMapping(value="/login")
    public ResponseEntity<Object>  login()
    {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String email=authentication.getName();
      AccountHolder accountHolder=aServices.findByEmail(email);
      return authentication.isAuthenticated()
      ?
      new ResponseEntity<>(accountHolder,HttpStatus.OK)
      :
      new ResponseEntity<>("Wrong username or password",HttpStatus.UNAUTHORIZED);
     }
     @RequestMapping(value="/login-success")
     public ResponseEntity<Object>  logIn(@RequestParam Object parameter)
     {
      System.out.println(parameter);
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String email=authentication.getName();
       AccountHolder accountHolder=aServices.findByEmail(email);
       return authentication.isAuthenticated()
       ?
       new ResponseEntity<>(accountHolder,HttpStatus.OK)
       :
       new ResponseEntity<>("Wrong username or password",HttpStatus.UNAUTHORIZED);
      }
     @RequestMapping(value="/success-login")
     public ResponseEntity<Object>  logInSuccess()
     {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String email=authentication.getName();
       AccountHolder accountHolder=aServices.findByEmail(email);
       return authentication.isAuthenticated()
       ?
       new ResponseEntity<>(accountHolder,HttpStatus.OK)
       :
       new ResponseEntity<>("Wrong username or password",HttpStatus.UNAUTHORIZED);
      }
      
     
}
