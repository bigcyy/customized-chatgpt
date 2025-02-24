package com.cyy.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyy.chat.dao")
public class CustomizedChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomizedChatApplication.class, args);
    }

}
