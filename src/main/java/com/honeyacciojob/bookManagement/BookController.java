package com.honeyacciojob.bookManagement;

import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.xmlunit.util.Linqy.asList;

@RestController
public class BookController {//class which will have our API -> APIs are nothing but methods only
    Map<Integer, Book> data = new HashMap<>();

    @PostMapping("/add-book") //use this annotation when creating Post API with end point url
    public String addBook(@RequestBody Book book){// json
        //we have to put in checks also -> like if book already exists in data
        if(Objects.isNull(book.getBookId()))return "Please enter Book Id";
        if(Objects.isNull(book.getTitle()))return "Please enter Book title";
        if(Objects.isNull(book.getAuthor()))return "Please enter Book Author";
        if(Objects.isNull(book.getPages()))return "Please enter Book Pages";
        if(data.containsKey(book.getBookId()))return "Book already Exist with following Id";
        data.put(book.getBookId(), book);
        //return "Book with ID: " + book.getBookId() + " added successfully";
        return "book added successfully";
    }
    @GetMapping("/book")//?id = 1
    public Book findBook(@RequestParam int id){
        return data.get(id);
    }
    @GetMapping("/books/{id}")//books/1
    public Book findBookByParam(@PathVariable int id){
        return data.get(id);
    }

    @GetMapping("/all-books")
    public List<Book> allBook(){

        return data.values().stream().toList();

        //return asList(data.values());


//        ArrayList<Book> list = new ArrayList<>();
//        for(int id : data.keySet()){
//            list.add(data.get(id));
//        }
//        return list;
    }
    @PutMapping("/update-book/{id}")
    public String updateBook(@PathVariable int id, @RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) int pages){
        Book book = data.get(id);
        if(Objects.nonNull(title)){
            book.setTitle(title);
        }
        if(Objects.nonNull(author)){
            book.setAuthor(author);
        }
        if(Objects.nonNull(pages)){
            book.setPages(pages);
        }
        data.put(id,book);
        return "book Updated succefully";
    }
    @DeleteMapping("/remove-book/{id}")
    public String removeBook(@PathVariable int id){
        data.remove(id);
        return "book removed successfully";
    }
}
