package com.example.servingwebcontent.initDb.initUsers;

import com.example.servingwebcontent.user.Role;
import com.example.servingwebcontent.user.Status;
import com.example.servingwebcontent.user.User;
import com.example.servingwebcontent.service.userService.IUserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitUsers {
    private final IUserService<User> serviceUser;

    public InitUsers(IUserService<User> serviceUser) {
        this.serviceUser = serviceUser;
    }

    public void initUsers(ConfigurableApplicationContext context) {
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        User user = new User();
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        user.setUserName("user");
        user.setEmail("user@mail.ru");
        user.setPassword(encoder.encode("user"));
        user.setRegistrationDate(new Date());

        User admin = new User();
        admin.setEmail("admin@mail.ru");
        admin.setUserName("admin");
        admin.setStatus(Status.ACTIVE);
        admin.setRole(Role.ADMIN);
        admin.setPassword(encoder.encode("admin"));
        admin.setRegistrationDate(new Date());

        serviceUser.save(user);
        serviceUser.save(admin);
    }

}
