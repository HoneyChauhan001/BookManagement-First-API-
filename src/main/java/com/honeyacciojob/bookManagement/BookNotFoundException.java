package com.honeyacciojob.bookManagement;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super("Book with id: " + id + " not present in Data");
    }
}
