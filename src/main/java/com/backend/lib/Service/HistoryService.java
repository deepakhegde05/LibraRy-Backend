package com.backend.lib.Service;

import com.backend.lib.Entity.HistoryEntity;
import com.backend.lib.Entity.User;
import com.backend.lib.Repo.HistoryRepo;
import com.backend.lib.Repo.UserRepo;
import com.backend.lib.Dto.HistoryDto;
import com.backend.lib.Dto.HistoryDto2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepo historyRepo;

    @Autowired
    private UserRepo userRepo;
    public void addBorrowedHistory(HistoryDto historyDto){
        User user = new User();
        user = userRepo.findByEmail(historyDto.getEmail()).get();
        HistoryEntity history = new HistoryEntity();
        history.setBid(historyDto.getBid());
        history.setBname(historyDto.getBname());
        history.setUser(user);
        history.setNoOfDays(historyDto.getNoOfDays());
        history.setBorrowedDate(historyDto.getBorrowedDate());
        history.setReturnDate("Pending");
        history.setFine(0);
        historyRepo.save(history);
    }

    public void addReturnedHistory(HistoryDto2 historyDto2,int bid, String email){
        HistoryEntity history = historyRepo.findByBidAndUser_email(bid,email);
        history.setReturnDate(historyDto2.getReturnDate());
        history.setFine(historyDto2.getFine());
        historyRepo.save(history);
    }

    public List<HistoryEntity> getById(int id){
        return historyRepo.findByUser_Uid(id);
    }


}
