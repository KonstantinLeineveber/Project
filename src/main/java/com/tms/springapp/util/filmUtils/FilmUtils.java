package com.tms.springapp.util.filmUtils;

import com.tms.springapp.model.order.Order;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "filmUtils")
public class FilmUtils {

    private final IService<Order> orderIService;
    private final IService<User> userIService;

    public FilmUtils(IService<Order> orderIService, IService<User> userIService) {
        this.orderIService = orderIService;
        this.userIService = userIService;
    }

    public void addImage(Film film, String imgLink) {
        film.getImages().add(imgLink);
    }

    public void findUsersAndOrders(long id) {
        List<Order> orders = orderIService.findAll();
        for (Order order : orders) {
            order.getFilms().removeIf(film -> film.getId() == id);
            orderIService.save(order);
        }
        List<User> users = userIService.findAll();
        for (User user : users) {
            user.getBookmarks().removeIf(film -> film.getId() == id);
            userIService.save(user);
        }
    }
}
