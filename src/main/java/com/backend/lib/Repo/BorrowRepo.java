package com.backend.lib.Repo;

import com.backend.lib.Entity.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepo extends JpaRepository<BorrowEntity, Integer> {
    List<BorrowEntity> findByEmail(String email);

    void deleteById(int id);
}
