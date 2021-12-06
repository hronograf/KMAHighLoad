package com.dbworker.book;

import com.common.RabbitMqSettings;
import com.common.book.dto.CreateBookRequestDto;
import com.common.book.dto.DeleteBookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequiredArgsConstructor

@RestController
@RequestMapping("/books")
public class BookRestController {

    private final BookService bookService;

    @RabbitListener(queues = RabbitMqSettings.createQueue)
    public void createBookPost(@Valid @RequestBody CreateBookRequestDto requestDto) {
        bookService.saveBook(requestDto);
    }

    @RabbitListener(queues = RabbitMqSettings.deleteQueue)
    public void deleteBookPost(@Valid @RequestBody DeleteBookRequestDto requestDto) {
        try {
            bookService.deleteBook(requestDto);
        } catch (UnexpectedRollbackException ignore) {
        }
    }

}
