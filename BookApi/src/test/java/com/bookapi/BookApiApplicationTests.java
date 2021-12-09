package com.bookapi;

import com.bookapi.book.BookService;
import com.bookapi.book.dto.BookInfoDto;
import com.common.book.BookEntity;
import com.common.book.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookApiApplicationTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private BookEntity createBookEntity() {
        return new BookEntity(UUID.randomUUID(), "title", "author");
    }

    @Test
    public void shouldGetBookInfo() {
        BookEntity bookEntity = createBookEntity();

        when(bookRepository.findById(bookEntity.getId())).thenReturn(Optional.of(bookEntity));

        assertThat(bookService.getBookInfo(bookEntity.getId()))
                .returns(bookEntity.getId(), BookInfoDto::getId)
                .returns(bookEntity.getTitle(), BookInfoDto::getTitle)
                .returns(bookEntity.getAuthor(), BookInfoDto::getAuthor);
        verify(bookRepository).findById(bookEntity.getId());
    }

    @Test
    public void shouldThrowException_whenNoBookWithSuchId() {
        UUID bookId = UUID.randomUUID();
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> bookService.getBookInfo(bookId))
                .isInstanceOf(RuntimeException.class);
    }

}
