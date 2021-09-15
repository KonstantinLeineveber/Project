package com.tms.springapp.controller.home;


import com.tms.springapp.model.film.Film;
import com.tms.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final IService<Film> filmService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(IService<Film> filmService) {
        this.filmService = filmService;
    }


    @GetMapping()
    public String home(Model model) {
        List<Film> carouselItemActive = new ArrayList<>();
        carouselItemActive.add(filmService.findById(1L));
        carouselItemActive.add(filmService.findById(2L));
        carouselItemActive.add(filmService.findById(3L));
        carouselItemActive.add(filmService.findById(4L));
        List<Film> carouselItem = new ArrayList<>();
        carouselItem.add(filmService.findById(5L));
        carouselItem.add(filmService.findById(6L));
        carouselItem.add(filmService.findById(7L));
        carouselItem.add(filmService.findById(8L));

        carouselItem.removeAll(Collections.singleton(null));
        carouselItemActive.removeAll(Collections.singleton(null));

        model.addAttribute("itemActive", carouselItemActive);
        model.addAttribute("item", carouselItem);

        return "home/home";
    }

}
