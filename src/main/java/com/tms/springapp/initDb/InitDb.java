package com.tms.springapp.initDb;

import com.tms.springapp.initDb.initUsers.InitUsers;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
@Getter
public class InitDb {
    private final InitUsers initUsers;

    @Value("${project.initDb}")
    private String initDb;

    public InitDb(InitUsers initUsers) {
        this.initUsers = initUsers;

    }

    public void initDb(ConfigurableApplicationContext context) {
        if (initDb.equals("true")) {
            initUsers.initUsers(context);
        }
    }


}
