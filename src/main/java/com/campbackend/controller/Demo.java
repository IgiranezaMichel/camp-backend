package com.campbackend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.*;

@RestController
public class Demo {
@GetMapping("/listHeaders")
public ResponseEntity<String> listAllHeaders(
  @RequestHeader Map<String, String> headers) {
    headers.forEach((key, value) -> {
       System.out.println(String.format("Header '%s' = %s", key, value));
    });

    return new ResponseEntity<String>(
      String.format("Listed %d headers", headers.size()), HttpStatus.OK);
}
}
