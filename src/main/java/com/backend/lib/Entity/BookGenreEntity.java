package com.backend.lib.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="book_genre")
public class BookGenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public BookGenreEntity() {
    }

    public BookGenreEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
