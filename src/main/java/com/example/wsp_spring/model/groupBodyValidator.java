package com.example.wsp_spring.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class groupBodyValidator implements Validator<String>{
    private final Pattern pattern=Pattern.compile("(\\d|\\D){1,100}");
    @Override
    public boolean isSatisfiedBy(String newGroup){
        if(!pattern.matcher(newGroup).matches()){
            return false;
        }
        return true;
    }
}
