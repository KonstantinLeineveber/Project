package com.tms.springapp.util.orderUtils;

import com.tms.springapp.model.film.Film;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component(value = "orderUtils")
public class OrderUtils {

//    public double orderSum(List<Film> films) {
//        return films.stream().mapToDouble(Film::getPrice).sum();
//    }


    public void addFilm(List<Film> films, Film film) {
        films.add(film);
    }


    public boolean containsFilm(List<Film> films, long id) {
        Film film2 = films.stream().filter(film -> film.getId() == id)
                .findFirst()
                .orElse(null);

        return film2 != null;
    }


    public void deleteFilm(List<Film> films, long id) {
        for (Film film : films) {
            if (film.getId() == id) {
                films.remove(film);
                return;
            }
        }
    }


    public void deleteAllFilms(List<Film> films, long id) {
        films.removeIf(film -> film.getId() == id);

    }


    public Map<String, Integer> orderInformation(List<Film> films) {
        Map<String, Integer> order = new HashMap<>();

        for (Film film : films) {
            Integer value = order.get(film.getName());
            if (value == null) {
                order.put(film.getName(), 1);
            } else {
                order.put(film.getName(), ++value);
            }
        }
        return order;
    }


    public Map<String, Map<Film, Integer>> convertListOfFilmsIntoMap(List<Film> userFilms) {
        Map<String, Map<Film, Integer>> films = new HashMap<>();
        for (Film film : userFilms) {
            Map<Film, Integer> s = new HashMap<>();
            if (films.containsKey(film.getName())) {
                s = films.get(film.getName());
                Set<Film> set = s.keySet();
                Film p = (Film) set.toArray()[0];
                int i = s.get(p) + 1;
                s.put(p, i);
            } else {
                s.put(film, 1);
            }
            films.put(film.getName(), s);
        }
        return films;
    }
}
