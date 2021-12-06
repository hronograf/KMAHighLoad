package com.bookapi;

import com.bookapi.book.BookRestController;
import com.common.book.dto.CreateBookRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Slf4j

@Component
@RequiredArgsConstructor
public class DbInitializer {
    private final BookRestController bookRestController;

    private final int INIT_BOOK_AMOUNT = 5;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for(int i = 0; i < INIT_BOOK_AMOUNT; i++) {
            CreateBookRequestDto requestDto = new CreateBookRequestDto();
            requestDto.setTitle("initBook_" + (char)(97 + i));
            requestDto.setAuthor("initAuthor_" + (char)(97 + i));
            bookRestController.createBookPost(requestDto);
        }
    }

}
