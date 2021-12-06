package com.bookapi.book;


import com.bookapi.book.dto.BookInfoDto;
import com.bookapi.utils.Response;
import com.common.RabbitMqSettings;
import com.common.book.dto.CreateBookRequestDto;
import com.common.book.dto.DeleteBookRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookService bookService;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/create")
    public Response<Void> createBookPost(@Valid @RequestBody CreateBookRequestDto requestDto) {
        rabbitTemplate.convertAndSend(RabbitMqSettings.exchangeName, RabbitMqSettings.createBookRoutingKey, requestDto);
        return Response.of("Successfully added", null);
    }

    @PostMapping("/delete")
    public Response<Void> deleteBookPost(@Valid @RequestBody DeleteBookRequestDto requestDto) {
        rabbitTemplate.convertAndSend(RabbitMqSettings.exchangeName, RabbitMqSettings.deleteBookRoutingKey, requestDto);
        return Response.of("Successfully deleted", null);
    }

    @GetMapping("/{id}")
    public Response<BookInfoDto> getBookInfo(@PathVariable("id") UUID id) {
        log.debug("getBookInfo id: {}", id);
        return Response.success(bookService.getBookInfo(id));
    }

    @GetMapping("/all")
    public Response<List<BookInfoDto>> getAllBooks() {
        return Response.success(bookService.getAllBooks());
    }

}
