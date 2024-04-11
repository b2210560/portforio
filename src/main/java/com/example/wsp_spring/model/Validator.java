package com.example.wsp_spring.model;

public interface Validator <T>{
    boolean isSatisfiedBy(T args);
}
