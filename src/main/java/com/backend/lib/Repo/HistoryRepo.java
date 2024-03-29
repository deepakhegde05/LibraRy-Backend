package com.backend.lib.Repo;

import com.backend.lib.Entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<HistoryEntity,Integer> {
    HistoryEntity findByBid(int bid);

    List<HistoryEntity> findByUser_Uid(int uid);

    HistoryEntity findByBidAndUser_email(int bid,String email);
}
