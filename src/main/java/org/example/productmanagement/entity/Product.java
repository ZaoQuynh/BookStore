package org.example.productmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String authors;
    @Column
    private String publisher;
    @Column
    private int publisherYear;
    @Column
    private  String genre;
    @Column
    private String description;

    public enum EGenre{
        NOVELS, ECONOMICS_AND_FINANCE, PSYCHOLOGY, SELF_HELP,
        SCIENCE, INNOVATION, HISTORY, POLITICS, CHILDREN_BOOK,
        COMICS, SCIENCE_FICTION, FANSTATY, POETRY, PROSE,
        LANGUAGE_LEARNINGS, TEXT_BOOKS, TRAVEL, MEMOIRS, ANOTHER
    }

    @Column
    private int price;
    @Column
    private int stockQty;
    @Column
    private boolean isBlocked;
    @Column
    private boolean isDeleted;
    @Column
    private int idUser;
}
