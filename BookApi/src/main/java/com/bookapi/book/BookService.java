package com.bookapi.book;

import com.common.book.BookRepository;
import com.bookapi.book.dto.BookInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Cacheable(cacheNames = "book", key = "#id", unless="#result == null")
    public BookInfoDto getBookInfo(UUID id) {
        return new BookInfoDto(bookRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Cacheable(cacheNames = "bookList")
    public List<BookInfoDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookInfoDto::new)
                .collect(Collectors.toList());
    }
}
