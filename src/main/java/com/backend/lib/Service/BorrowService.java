package com.backend.lib.Service;


import com.backend.lib.Entity.BookEntity;
import com.backend.lib.Entity.BorrowEntity;
import com.backend.lib.Repo.BookRepo;
import com.backend.lib.Repo.BorrowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepo borrowRepo;

    @Autowired
    BookRepo bookRepo;

    public void addToBorrow(BorrowEntity borrow){
        BookEntity book = bookRepo.findById(borrow.getBid());
        if (book != null && book.getAvailableBooks() > 0) {
            // If the book exists and is available, decrement availability and save
            book.setAvailableBooks(book.getAvailableBooks() - 1);
            bookRepo.save(book);
        } else {
            throw new ArrayIndexOutOfBoundsException();

        }
        borrowRepo.save(borrow);
    }

    public List<BorrowEntity> findAll(){
        return borrowRepo.findAll();
    }

    public List<BorrowEntity>  findByEmail(String email){
        return borrowRepo.findByEmail(email);
    }

    public void deleteById(int id){
        borrowRepo.deleteById(id);
    }

    //admin dash

    public Long getCount(){
        return borrowRepo.count();
    }

}
