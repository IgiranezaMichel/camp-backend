package com.campbackend.configuration;
import org.springframework.http.HttpStatus;
public class AppException extends RuntimeException{
private final HttpStatus status;
public AppException(String message,HttpStatus statusCode){
    super(message);
    this.status=statusCode;
}
public HttpStatus getStatus() {
    return status;
}
}
