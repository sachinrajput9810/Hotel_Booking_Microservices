package com.hm.user.service.Exception;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found at Server !!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
