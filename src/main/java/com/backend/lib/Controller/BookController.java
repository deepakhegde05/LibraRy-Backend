package com.backend.lib.Controller;

import com.backend.lib.Entity.BookEntity;
import com.backend.lib.Entity.User;
import com.backend.lib.Repo.BookRepo;
import com.backend.lib.Service.BookService;
import com.backend.lib.Service.JWTService;
import com.backend.lib.Service.UserService;
import com.backend.lib.Dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/admin-home")
    public String addBook(@RequestBody BookDto book){
        System.out.println(book.toString());
        bookService.addBook(book);
        return "Successfully book added";
    }
    @GetMapping("/admin-home/allBooks")//fetch all books
    public ResponseEntity<List<BookEntity>> allBooks() {
        List<BookEntity> list = bookService.getAllBooks();
        return new ResponseEntity<List<BookEntity>>(list,HttpStatus.OK);
    }
    @GetMapping("/admin-home/books/{name}")//fetch all books
    public ResponseEntity<List<BookEntity>> perBooks(@PathVariable String name) {
//        jwtService.isTokenValid()
        List<BookEntity> list = bookRepo.findByBnameContainingIgnoreCase(name);
        return new ResponseEntity<List<BookEntity>>(list,HttpStatus.OK);
    }
    @GetMapping("/admin-home/users")//fetch all users
    public ResponseEntity<List<User>> allUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<List<User>>(list,HttpStatus.OK);
    }

//    @GetMapping("admin-home/book/{name}")  //fetch a specific book
//    public ResponseEntity<BookEntity> checkEmail(@PathVariable("name") String name){
//        System.out.println("name: "+name);
//        Optional<BookEntity> bookOptional = Optional.ofNullable(BookService.findByName(name));
//        if(bookOptional.isPresent()){
//            BookEntity book = bookOptional.get();
//            System.out.println("book data : "+book);
//            return new ResponseEntity<>(book,HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("admin-home/delete/{name}")
    public String deleteBook(@PathVariable String name){
        System.out.println("bokService up to call");
        bookService.deleteByName(name);
        return "Successfully dletetd";

    }
    @PatchMapping("/admin-home/patch/{id}")
    public String updateBook(@PathVariable int id,@RequestBody BookDto book){
        bookService.updateBook(book,id);
        return "Updated Book";
    }






}

