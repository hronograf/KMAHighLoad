package com.common.book;



import com.common.book.dto.IBookDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "book")
public class BookEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;


    public BookEntity(IBookDto bookDto) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
    }

    public BookEntity(UUID id) {
        this.id = id;
    }

}
