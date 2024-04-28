package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Book {
    @Column
    private String title;
    @Column
    private String authors;
    @Column
    private int publisherYear;
    @Column
    private  EGenre genre;
    @Column
    private String description;

    public enum EGenre{
        NOVELS, ECONOMICS_AND_FINANCE, PSYCHOLOGY, SELF_HELP,
        SCIENCE, INNOVATION, HISTORY, POLITICS, CHILDREN_BOOK,
        COMICS, SCIENCE_FICTION, FANSTATY, POETRY, PROSE,
        LANGUAGE_LEARNINGS, TEXT_BOOKS, TRAVEL, MEMOIRS, ANOTHER
    }
}
