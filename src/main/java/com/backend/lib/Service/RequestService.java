package com.backend.lib.Service;

import com.backend.lib.Entity.BookEntity;
import com.backend.lib.Entity.RequestEntity;
import com.backend.lib.Repo.BookRepo;
import com.backend.lib.Repo.RequestRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private BookRepo bookRepo;

    public boolean addRequest(RequestEntity request){
        try {
            RequestEntity newReq = requestRepo.findByBidAndEmail(request.getBid(),request.getEmail());
            if (newReq == null) {
                BookEntity book = bookRepo.findById(request.getBid());
                if (book.getAvailableBooks() > 0) {
                    System.out.println("Reached request repo");
                    requestRepo.save(request);
                    return true;
                } else {
                    System.out.println("Not done request");
                    throw new ArrayIndexOutOfBoundsException();
                }
            }else{
                System.out.println("request not sent");
                return false;
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("You are requested same book");
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public List<RequestEntity> findByEmail(String email){
        return requestRepo.findByEmail(email);
    }

    public List<RequestEntity> findAll(){
        return requestRepo.findAll();
    }

    @Transactional
    public void deleteById(int reqid){
        requestRepo.deleteByReqid(reqid);
    }

    //admin dash

    public Long getCount(){
        return requestRepo.count();
    }
}
