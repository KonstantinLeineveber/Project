package com.tms.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
@EnableCaching
public class SpringAppApplication {


    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext run = SpringApplication.run(SpringAppApplication.class, args);
    }

}
