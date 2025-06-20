package com.hm.user.service.Exception;

import com.hm.user.service.Payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        String message = ex.getMessage() ;
//        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build() ;
        ApiResponse response = new ApiResponse() ;
        response.setMessage(message);
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
