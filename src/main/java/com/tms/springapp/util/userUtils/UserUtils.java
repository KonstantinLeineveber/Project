package com.tms.springapp.util.userUtils;

import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import org.springframework.stereotype.Component;


@Component(value = "userUtils")
public class UserUtils {
    private final IService<User> userIService;

    public UserUtils(
            IService<User> userIService) {
        this.userIService = userIService;
    }


}
