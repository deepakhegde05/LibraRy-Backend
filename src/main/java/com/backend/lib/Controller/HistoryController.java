package com.backend.lib.Controller;

import com.backend.lib.Entity.HistoryEntity;
import com.backend.lib.Repo.HistoryRepo;
import com.backend.lib.Service.HistoryService;
import com.backend.lib.Dto.HistoryDto;
import com.backend.lib.Dto.HistoryDto2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HistoryRepo historyRepo;

    @PostMapping("/ad-ap-history")
    public String addBorrowedHistory(@RequestBody HistoryDto historyDto){
        historyService.addBorrowedHistory(historyDto);
        return "borrowedHistroy added";
    }

    @PostMapping("/us-bor-history/{bid}/{email}")
    public String addReturnedHistory(@RequestBody HistoryDto2 historyDto2,@PathVariable("bid") int bid, @PathVariable("email") String email){
        historyService.addReturnedHistory(historyDto2,bid,email);
        return "returnedHistroy added";
    }

    @GetMapping("/getBorrowedHistory")
    public List<HistoryEntity> getAll(){
        return historyRepo.findAll();
    }

    @GetMapping("/getReturnedHistory/{id}")
    public ResponseEntity<List<HistoryEntity>> getAll(@PathVariable int id){
        List<HistoryEntity> history = historyService.getById(id);
        return new ResponseEntity<List<HistoryEntity>>(history,HttpStatus.OK);
    }


}
