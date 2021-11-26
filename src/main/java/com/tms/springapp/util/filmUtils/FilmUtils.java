package com.tms.springapp.util.filmUtils;

import com.tms.springapp.model.film.Film;
import com.tms.springapp.service.IService;
import org.springframework.stereotype.Component;

@Component(value = "filmUtils")
public class FilmUtils {

    private final IService<Film> filmIService;

    public FilmUtils(
            IService<Film> filmIService) {
        this.filmIService = filmIService;
    }
}
