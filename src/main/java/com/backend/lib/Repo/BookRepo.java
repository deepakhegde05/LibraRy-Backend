package com.backend.lib.Repo;

import com.backend.lib.Entity.BookEntity;
import com.backend.lib.Entity.BookGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepo extends JpaRepository<BookEntity, Integer> {
    BookEntity findById(int id);

    BookEntity findByBname(String bname);

    Optional<BookEntity> deleteByBname(String bname);

//    @Query("select bname from books where bname LIKE '%or%'")
    List<BookEntity> findByBnameContainingIgnoreCase(String bname);


    List<BookEntity> findByBookGenreEntity(BookGenreEntity bookGenre);


}
