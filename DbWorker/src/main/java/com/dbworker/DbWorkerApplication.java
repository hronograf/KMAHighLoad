package com.dbworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DbWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbWorkerApplication.class, args);
    }

}
