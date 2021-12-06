package com.common;

public class RabbitMqSettings {
    public static final String createQueue = "createBookQueue";
    public static final String deleteQueue = "deleteBookQueue";

    public static final String exchangeName = "booksExchange";

    public static final String createBookRoutingKey = "book.create";

    public static final String deleteBookRoutingKey = "book.delete";
}
