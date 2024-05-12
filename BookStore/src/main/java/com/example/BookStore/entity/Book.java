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
    private String publisher;
    @Column
    private int publisherYear;
    @Column
    private  EGenre genre;
    @Column
    private String description;

    public enum EGenre{
        NOVELS("Tiểu thuyết"),
        ECONOMICS_AND_FINANCE("Kinh tế và Tài chính"),
        PSYCHOLOGY("Tâm lý học"),
        SELF_HELP("Phát triển bản thân"),
        SCIENCE("Khoa học"),
        INNOVATION("Đổi mới"),
        HISTORY("Lịch sử"),
        POLITICS("Chính trị"),
        CHILDREN_BOOK("Sách thiếu nhi"),
        COMICS("Truyện tranh"),
        SCIENCE_FICTION("Khoa học viễn tưởng"),
        FANSTATY("Tiên hiệp, Huyền bí"),
        POETRY("Thơ"),
        PROSE("Văn xuôi"),
        LANGUAGE_LEARNINGS("Học ngôn ngữ"),
        TEXT_BOOKS("Sách giáo khoa"),
        TRAVEL("Du lịch"),
        MEMOIRS("Hồi ký"),
        ANOTHER("Khác");

        private final String description;

        EGenre(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
