package com.dbworker;

import com.common.book.BookEntity;
import com.common.book.BookRepository;
import com.common.book.dto.CreateBookRequestDto;
import com.common.book.dto.DeleteBookRequestDto;
import com.dbworker.book.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DbWorkerApplicationTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldSaveBookEntity() {
        bookService.saveBook(new CreateBookRequestDto("title", "author"));
        verify(bookRepository).saveAndFlush(ArgumentMatchers.any(BookEntity.class));
    }

    @Test
    void shouldDeleteBookEntity() {
        UUID bookId = UUID.randomUUID();
        bookService.deleteBook(new DeleteBookRequestDto(bookId));
        verify(bookRepository).deleteById(bookId);
    }

}
