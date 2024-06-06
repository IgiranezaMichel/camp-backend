package com.campbackend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.campbackend.dto.AccountHolderDTO;
import com.campbackend.modal.AccountHolder;
import com.campbackend.services.AccountHolderServices;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
@RestController
@Slf4j
public class IndexController {
  @Autowired private AccountHolderServices accountHolderServices;
@RequestMapping("/login")
public String requestMethodName(HttpServletRequest rServletRequest,HttpServletResponse response,HttpSession session) {
  Authentication auth=SecurityContextHolder.getContext().getAuthentication();
  log.info("servlet request {}",rServletRequest.getAuthType());
  log.info("authentication {}",auth.isAuthenticated());
  log.info("Email {}",auth.getName());
   log.info("user detail {}",auth.getDetails());
   log.info("session {}",rServletRequest.getSession().getId());
  if(!auth.getName().equals("anonymous")){
    Cookie cookie=new Cookie("home", "null");
    response.addCookie(cookie);
  }
 
    return new String();
}
@PostMapping(value = "/success-login")
public AccountHolderDTO getMethodName(Principal principal,HttpServletRequest session) {
  AccountHolder accountHolder=accountHolderServices.findByEmail(principal.getName());
  log.info("Session generated",session.getSession().getId());
    return new AccountHolderDTO(accountHolder.getId(), accountHolder.getName(), accountHolder.getGender(), accountHolder.getDob(), accountHolder.getPhoneNumber(), accountHolder.getEmail(), accountHolder.getProfilePicture(), accountHolder.getRole());
}

}
