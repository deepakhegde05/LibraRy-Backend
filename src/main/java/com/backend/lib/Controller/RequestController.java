package com.backend.lib.Controller;

import com.backend.lib.Entity.RequestEntity;
import com.backend.lib.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/request")
    public ResponseEntity<Object> requestData(@RequestBody RequestEntity request){
        boolean res= requestService.addRequest(request);
        if(res){
            System.out.println("Reached controller request");
            return ResponseEntity.ok("Successfully added");
        }
        return ResponseEntity.badRequest().body("duplicate entry");
    }

    @GetMapping("/user-request/{email}")
    //getting data to userrequest table based on the email user login
    public ResponseEntity<List<RequestEntity>> getAllRequest(@PathVariable String email){
        List<RequestEntity> list = requestService.findByEmail(email);
        return new ResponseEntity<List<RequestEntity>>(list,HttpStatus.OK);
    }

    @GetMapping("/admin-request")
    //getting data to adminrequest table based on the all email user login
    public ResponseEntity<List<RequestEntity>> getAllRequest(){
        List<RequestEntity> list = requestService.findAll();
        return new ResponseEntity<List<RequestEntity>>(list,HttpStatus.OK);
    }

    //removing from request table after approved by admin
    @DeleteMapping("/admin-request/delete/{reqid}")
    public String removeAfterApprove(@PathVariable int reqid){
        requestService.deleteById(reqid);
        return "successfully removed req";
    }

    //admin dashboard

    @GetMapping("/admin/admin-dashboard/requested")
    public Long getCount() {
        long i = requestService.getCount();
        return i;
    }

}
