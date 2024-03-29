package com.backend.lib.Controller;

import com.backend.lib.Entity.ReturnEntity;
import com.backend.lib.Service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    @PostMapping("/user-borrow-return")
    public String approvedToBorrow(@RequestBody ReturnEntity returnEntity){
        returnService.addToRequest(returnEntity);
        return "successfully approved to borrow";
    }

    @GetMapping("/user-return/{email}")
    public ResponseEntity<List<ReturnEntity>> getAllRequest(@PathVariable String email){
        List<ReturnEntity> returnEntity = returnService.getByEmail(email);
        return new ResponseEntity<List<ReturnEntity>>(returnEntity, HttpStatus.OK);
    }

    @GetMapping("/admin-return")
    public ResponseEntity<List<ReturnEntity>> getAllRequest(){
        List<ReturnEntity> returnList = returnService.getAllRequest();
        return new ResponseEntity<>(returnList,HttpStatus.OK);
    }
//admin dash
    @GetMapping("/admin/admin-dashboard/returned")
    public Long getCount() {
        long i = returnService.getCount();
        return i;
    }

}
