package com.backend.lib.Controller;

import com.backend.lib.Entity.BookEntity;
import com.backend.lib.Entity.BookGenreEntity;
import com.backend.lib.Service.BookGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class BookGenreController {
    @Autowired
    private BookGenreService bookGenreService;

    @GetMapping("/genre-lists")
    public ResponseEntity<List<BookGenreEntity>> getAllGenre(){
        List<BookGenreEntity> list = bookGenreService.getAllGenreLists();
        return new ResponseEntity<List<BookGenreEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/genre-lists/{id}")
    public ResponseEntity<List<BookEntity>> getBookById(@PathVariable int id) {
        List<BookEntity> book = bookGenreService.getAllBooksById(id);
        return new ResponseEntity<List<BookEntity>>(book, HttpStatus.OK);
    }
}
