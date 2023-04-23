package com.honeyacciojob.bookManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class BookRepository {

    Map<Integer,Book> data = new HashMap<>();
    public Boolean addBook(Book book) {
        data.put(book.getBookId(),book);
        return true;

    }

    public Optional<Book> getById(Integer bookId) {
        if(data.containsKey(bookId)){
            return Optional.of(data.get(bookId));
        }
        else return Optional.empty();
    }

    public void removeById(int id) {
        data.remove(id);
    }

//    public Optional<Book> updateBook(int id, String title, String author, int pages) {
//        Book book = data.get(id);
//        if(Objects.nonNull(title)){
//            book.setTitle(title);
//        }
//        if(Objects.nonNull(author)){
//            book.setAuthor(author);
//        }
//        if(Objects.nonNull(pages)){
//            book.setPages(pages);
//        }
//        data.put(id,book);
//        return Optional.of(data.get(id));
//    }
}
