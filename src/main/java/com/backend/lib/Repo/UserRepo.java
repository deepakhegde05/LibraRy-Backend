package com.backend.lib.Repo;

import com.backend.lib.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = " select * from users where email = :email",nativeQuery = true)
    Optional<User> findByEmail(String email);

    Optional<User> deleteByUname(String uname);
    // i changed to email

    Optional<User> deleteByEmail(String email);
}
