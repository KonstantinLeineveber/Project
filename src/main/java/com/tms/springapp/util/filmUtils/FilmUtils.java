package com.tms.springapp.util.filmUtils;

import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import org.springframework.stereotype.Component;

@Component(value = "filmUtils")
public class FilmUtils {

    private final IService<Comment> commentIService;
    private final IService<User> userIService;

    public FilmUtils(IService<Comment> commentIService, IService<User> userIService) {
        this.commentIService = commentIService;
        this.userIService = userIService;
    }

    public void addImage(Film film, String imgLink) {
        film.getImages().add(imgLink);
    }

//    public void findUsersAndComments(long id) {
//        List<Comment> comments = commentIService.findAll();
//        for (Comment comment : comments) {
//            comment.getFilms().removeIf(film -> film.getId() == id);
//            commentIService.save(comment);
//        }
//
//    }
}
