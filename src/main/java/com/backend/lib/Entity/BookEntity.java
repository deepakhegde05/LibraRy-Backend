package com.backend.lib.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;
    @Column(unique = true)
    private String bname;
    private String author;
    private Long publishYear;
    private Long availableBooks;
//    private String genre;
    @ManyToOne
    private BookGenreEntity bookGenreEntity;



    public BookEntity() {
    }

    public BookEntity(String bname, String author, Long publishYear, Long availableBooks) {
        super();
        this.bname = bname;
        this.author = author;
        this.publishYear = publishYear;
        this.availableBooks = availableBooks;
//        this.genre = genre;
    }

    public BookGenreEntity getBookGenreEntity() {
        return bookGenreEntity;
    }

    public void setBookGenreEntity(BookGenreEntity bookGenreEntity) {
        this.bookGenreEntity = bookGenreEntity;
    }

    public int getId() {
        return bid;
    }

    public void setId(int uid) {
        this.bid = uid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Long publishYear) {
        this.publishYear = publishYear;
    }

    public Long getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(Long availableBooks) {
        this.availableBooks = availableBooks;
    }

}
