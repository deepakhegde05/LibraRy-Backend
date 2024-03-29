package com.backend.lib.Repo;

import com.backend.lib.Entity.BookGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookGenreRepo extends JpaRepository<BookGenreEntity,Integer> {
     Optional<BookGenreEntity> findByName(String name);
}
