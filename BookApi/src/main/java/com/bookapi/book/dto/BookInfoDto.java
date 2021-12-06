package com.bookapi.book.dto;

import com.common.book.BookEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Data
public class BookInfoDto implements Serializable {
    private UUID id;
    private String title;
    private String author;

    public BookInfoDto(BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
    }
}
