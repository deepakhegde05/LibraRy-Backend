package com.backend.lib.Controller;

import com.backend.lib.Entity.BorrowEntity;
import com.backend.lib.Service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/admin-request-approve")
    public String approvedtoBorrow(@RequestBody BorrowEntity borrow){
        borrowService.addToBorrow(borrow);
        return "successfully approved to borrow";
    }

    @GetMapping("/user-borrow/{email}")
    public ResponseEntity<List<BorrowEntity>> getAllBorrowed(@PathVariable String email){
        System.out.println("before serivice");
        List<BorrowEntity> list = borrowService.findByEmail(email);
        System.out.println("after borrowserivice");
        return new ResponseEntity<List<BorrowEntity>>(list,HttpStatus.OK);
    }

    @GetMapping("/admin-borrow")
    public ResponseEntity<List<BorrowEntity>> getAllBorrowed(){
        List<BorrowEntity> list = borrowService.findAll();
        return new ResponseEntity<List<BorrowEntity>>(list, HttpStatus.OK);
    }

    @DeleteMapping("/user-borrow-return/delete/{id}")
    public String removeAfterApprove(@PathVariable int id){
        borrowService.deleteById(id);
        return "successfully removed req";
    }

    //admin dash
    @GetMapping("/admin/admin-dashboard/borrowed")
    public Long getCount() {
        long i = borrowService.getCount();
        return i;
    }

}
