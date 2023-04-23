package com.honeyacciojob.bookManagement;

import java.util.Objects;
import java.util.Optional;

public class BookService {
    BookRepository bookRepository = new BookRepository();
    public Boolean addBook(Book book) throws BookAlreadyExistException{
        Optional<Book> bookOpt = bookRepository.getById(book.getBookId());
        if(bookOpt.isPresent()){//bookId already exists
            throw new BookAlreadyExistException(book.getBookId());
        }
        return bookRepository.addBook(book);
    }

    public Book findBook(int id) throws BookNotFoundException {
        Optional<Book> bookOpt = bookRepository.getById(id);
        if(bookOpt.isPresent()){
            return bookOpt.get();
        }
        else {
            throw new BookNotFoundException(id);
        }
    }

    public String updateBook(int id, String title, String author, Integer pages) {
        try{
            Book book = findBook(id);
            if(Objects.nonNull(title)){
                book.setTitle(title);
            }
            if(Objects.nonNull(author)){
                book.setAuthor(author);
            }
            if(Objects.nonNull(pages)){
                book.setPages(pages);
            }
            bookRepository.addBook(book);
            return "Book Updated";
        } catch(BookNotFoundException e){
            Book book = new Book(id,title,author,pages);
            bookRepository.addBook(book);
            return "New Book Create";
        }
    }
    public Boolean deleteBook(int id) throws BookNotFoundException {
        Optional<Book> bookOpt = bookRepository.getById(id);
        if(bookOpt.isEmpty()){
            throw new BookNotFoundException(id);
        }
        bookRepository.removeById(id);
        return true;
    }
}
