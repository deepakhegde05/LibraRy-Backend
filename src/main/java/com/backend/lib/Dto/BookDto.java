package com.backend.lib.Dto;

public class BookDto {
    private String bname;
    private String author;

    @Override
    public String toString() {
        return "Dto{" +
                "bid=" +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                ", availableBooks=" + availableBooks +
                ", BookGenreEntity='" + bookGenreEntity + '\'' +
                '}';
    }

    private Long publishYear;
    private Long availableBooks;
    private String bookGenreEntity;


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

    public String getBookGenreEntity() {
        return bookGenreEntity;
    }

    public void setBookGenreEntity(String bookGenreEntity) {
        this.bookGenreEntity = bookGenreEntity;
    }
}
