package com.example.servingwebcontent.initDb;

//import by.yurachel.springapp.initDb.initPhones.InitPhones;
import com.example.servingwebcontent.initDb.initUsers.InitUsers;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
@Getter
public class InitDb {
//    private final InitPhones initPhones;
    private final InitUsers initUsers;

    @Value("${project.initDb}")
    private String initDb;

    public InitDb(
//            InitPhones initPhones,
            InitUsers initUsers) {
//        this.initPhones = initPhones;
        this.initUsers = initUsers;

    }

    public void initDb(ConfigurableApplicationContext context) {
        if (initDb.equals("true")) {
//            initPhones.initPhones();
            initUsers.initUsers(context);
        }
    }



}
