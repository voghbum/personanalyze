package com.voghbum.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.voghbum",})
public class PersonanalyzeWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonanalyzeWebServiceApplication.class, args);
    }
}
