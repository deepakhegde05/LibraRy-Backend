package com.backend.lib.Service;

import com.backend.lib.Entity.BookEntity;
import com.backend.lib.Entity.BookGenreEntity;
import com.backend.lib.Repo.BookGenreRepo;
import com.backend.lib.Repo.BookRepo;
import com.backend.lib.Dto.BookDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookGenreRepo bookGenreRepo;

    public void addBook(BookDto book){
        Optional<BookGenreEntity> newGenreName = bookGenreRepo.findByName(book.getBookGenreEntity());
        BookGenreEntity bookGenreEntity;
        if(newGenreName.isPresent()){
            bookGenreEntity = newGenreName.get();
        }
        else {
            BookGenreEntity bge = new BookGenreEntity();
            bge.setName(book.getBookGenreEntity());
            bookGenreEntity = bookGenreRepo.save(bge);
        }
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBname(book.getBname());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setAvailableBooks(book.getAvailableBooks());
        bookEntity.setPublishYear(book.getPublishYear());
        bookEntity.setBookGenreEntity(bookGenreEntity);
        bookRepo.save(bookEntity);
    }

    public BookEntity findThisBook(int id){

        return bookRepo.findById(id);
    }

    public List<BookEntity> getAllBooks() {
        return bookRepo.findAll();
    }


    public BookEntity findByName(String name){
        return bookRepo.findByBname(name);
    }
    @Transactional
    public Optional<BookEntity> deleteByName(String name){
        return bookRepo.deleteByBname(name);
    }

    public BookEntity findById(int id){
        return bookRepo.findById(id);
    }

    public void updateBook(BookDto book, int id){
        BookEntity oldBook = bookRepo.findById(id);
        oldBook.setBname(book.getBname());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setAvailableBooks(book.getAvailableBooks());
        oldBook.setPublishYear(book.getPublishYear());
        Optional<BookGenreEntity> newGenreName = bookGenreRepo.findByName(book.getBookGenreEntity());
        BookGenreEntity bookGenreEntity;
        if(newGenreName.isPresent()){
            bookGenreEntity = newGenreName.get();
        }
        else{
            BookGenreEntity bge = new BookGenreEntity();
            bge.setName(book.getBookGenreEntity());
            bookGenreEntity = bookGenreRepo.save(bge);
        }
        oldBook.setBookGenreEntity(bookGenreEntity);
        bookRepo.save(oldBook);
    }


}
