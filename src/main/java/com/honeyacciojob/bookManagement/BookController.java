package com.honeyacciojob.bookManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.xmlunit.util.Linqy.asList;

@RestController
@RequestMapping("/book")
public class BookController {//class which will have our API -> APIs are nothing but methods only

    public BookController(){
        System.out.println("BookController is Instantiated");
    }

    @Autowired
    BookService bookService;

    @PostMapping("/add") //use this annotation when creating Post API with end point url
    public ResponseEntity addBook(@RequestBody Book book){// json

        try{
            bookService.addBook(book);
            return new ResponseEntity("Added Successfully", HttpStatus.CREATED);
        } catch(BookAlreadyExistException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.valueOf(400));
        } catch (Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.valueOf(500));
        }


        //we have to put in checks also -> like if book already exists in data
        //if(Objects.isNull(book.getBookId()))return "Please enter Book Id";
        //if(Objects.isNull(book.getTitle()))return "Please enter Book title";
        //if(Objects.isNull(book.getAuthor()))return "Please enter Book Author";
        //if(Objects.isNull(book.getPages()))return "Please enter Book Pages";
        //if(data.containsKey(book.getBookId()))return "Book already Exist with following Id";
        //data.put(book.getBookId(), book);
        //return "Book with ID: " + book.getBookId() + " added successfully";
        //return "book added successfully";
    }
    @GetMapping("/get")//?id = 1
    public ResponseEntity findBook(@RequestParam int id){
        try{
            Book book = bookService.findBook(id);
            return new ResponseEntity(book,HttpStatus.OK);
        } catch (BookNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.valueOf(500));
        }
    }
    @GetMapping("/get/{id}")//books/1
    public ResponseEntity findBookByParam(@PathVariable int id){
        try{
            Book book = bookService.findBook(id);
            return new ResponseEntity(book,HttpStatus.OK);
        } catch (BookNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.valueOf(500));
        }
    }
//
//    @GetMapping("/all-books")
//    public List<Book> allBook(){
//
//        return data.values().stream().toList();
//
//        //return asList(data.values());
//
//
////        ArrayList<Book> list = new ArrayList<>();
////        for(int id : data.keySet()){
////            list.add(data.get(id));
////        }
////        return list;
//    }
    @PutMapping("/update/{id}")
    public String updateBook(@PathVariable int id, @RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) Integer pages){
            try {
                String response = bookService.updateBook(id, title, author, pages);
                return response;
            } catch (Exception e){
                return e.toString();
            }
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){

        try{
            bookService.deleteBook(id);
            return new ResponseEntity("Book deleted successfully",HttpStatus.valueOf(200));
        } catch (BookNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
