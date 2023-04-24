package com.honeyacciojob.bookManagement;

import lombok.Getter;
import lombok.Setter;

@Getter //lombok dependency
@Setter
public class Book {
    private Integer bookId;
    private String title;
    private String author;
    private int pages;

    public Book(Integer bookId, String title, String author, Integer pages) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
}
