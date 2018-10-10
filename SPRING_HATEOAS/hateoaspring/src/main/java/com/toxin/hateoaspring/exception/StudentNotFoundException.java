package com.toxin.hateoaspring.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
        super("NOT FOUND by id " + id);
    }

}
