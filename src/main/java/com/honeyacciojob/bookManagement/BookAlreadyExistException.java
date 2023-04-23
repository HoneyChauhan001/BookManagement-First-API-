package com.honeyacciojob.bookManagement;

public class BookAlreadyExistException extends RuntimeException {
    public BookAlreadyExistException(int id){
        super("Book for id: " + id + " already exist in database");
    }
}
