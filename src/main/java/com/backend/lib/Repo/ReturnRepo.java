package com.backend.lib.Repo;

import com.backend.lib.Entity.ReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnRepo extends JpaRepository<ReturnEntity, Integer> {
    List<ReturnEntity> findByEmail(String email);

}
