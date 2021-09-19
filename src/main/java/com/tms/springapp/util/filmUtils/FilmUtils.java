package com.tms.springapp.util.filmUtils;

import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import org.springframework.stereotype.Component;

@Component(value = "filmUtils")
public class FilmUtils {

    private final IService<User> userIService;

    public FilmUtils(
            IService<User> userIService) {
        this.userIService = userIService;
    }
}
