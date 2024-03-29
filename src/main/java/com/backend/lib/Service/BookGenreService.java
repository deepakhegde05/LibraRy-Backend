package com.backend.lib.Service;

import com.backend.lib.Entity.BookEntity;
import com.backend.lib.Entity.BookGenreEntity;
import com.backend.lib.Repo.BookGenreRepo;
import com.backend.lib.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookGenreService {

    @Autowired
    private BookGenreRepo bookGenreRepo;

    @Autowired
    private BookRepo bookRepo;
     public List<BookGenreEntity> getAllGenreLists(){
        return bookGenreRepo.findAll();
    }

    public List<BookEntity> getAllBooksById(int id){
         BookGenreEntity bookGenre = bookGenreRepo.findById(id).get();
         return bookRepo.findByBookGenreEntity(bookGenre);
    }
}
