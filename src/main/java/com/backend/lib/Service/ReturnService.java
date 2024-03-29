package com.backend.lib.Service;

import com.backend.lib.Entity.BookEntity;
import com.backend.lib.Entity.ReturnEntity;
import com.backend.lib.Repo.BookRepo;
import com.backend.lib.Repo.ReturnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReturnService {
    @Autowired
    private ReturnRepo returnRepo;

    @Autowired
    private BookRepo bookRepo;

    public void addToRequest(ReturnEntity returnEntity){
        BookEntity book = bookRepo.findById(returnEntity.getBid());
        if (book != null) {
            // If the book exists and is available, decrement availability and save
            book.setAvailableBooks(book.getAvailableBooks() + 1);
            bookRepo.save(book);
        }else {
            throw new ArrayIndexOutOfBoundsException();

        }
        returnRepo.save(returnEntity);
    }

    public List<ReturnEntity> getByEmail(String email){
        return returnRepo.findByEmail(email);
    }

    public List<ReturnEntity> getAllRequest(){
        return returnRepo.findAll();
    }

    //admin dash

    public Long getCount(){
        return returnRepo.count();
    }

}
