package com.backend.lib.Repo;

import com.backend.lib.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);

}
