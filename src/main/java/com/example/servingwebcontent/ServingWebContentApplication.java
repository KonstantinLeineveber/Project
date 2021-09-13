package com.example.servingwebcontent;

import com.example.servingwebcontent.initDb.InitDb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ServingWebContentApplication.class, args);
        initDb(run);
    }

    private static void initDb(ConfigurableApplicationContext context) {
        InitDb db = context.getBean(InitDb.class);
        db.initDb( context);
    }

}
