package com.backend.lib.Repo;

import com.backend.lib.Entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<RequestEntity,Integer> {

    List<RequestEntity> findByEmail(String email);

    RequestEntity findByBid(int bid);

    RequestEntity findByBidAndEmail(int bid,String email);

    void deleteByReqid(int reqid);
}
