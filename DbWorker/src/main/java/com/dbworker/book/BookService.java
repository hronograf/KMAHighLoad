package com.dbworker.book;


import com.common.book.BookEntity;
import com.common.book.BookRepository;
import com.common.book.dto.CreateBookRequestDto;
import com.common.book.dto.DeleteBookRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @CacheEvict(cacheNames = "bookList", allEntries=true)
    public void saveBook(CreateBookRequestDto requestDto) {
        BookEntity bookEntity = new BookEntity(requestDto);
        bookRepository.saveAndFlush(bookEntity);
    }

    @Transactional(noRollbackFor = EmptyResultDataAccessException.class)
    @Caching(evict = {
            @CacheEvict(cacheNames = "book", key = "#requestDto.id"),
            @CacheEvict(cacheNames = "bookList", allEntries=true)
    })
    public void deleteBook(DeleteBookRequestDto requestDto) {
        try {
            bookRepository.deleteById(requestDto.getId());
        } catch (EmptyResultDataAccessException ignored) {
        }
    }
}
