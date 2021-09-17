package com.tms.springapp.util.userUtils;

import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.util.commentUtils.CommentUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "userUtils")
public class UserUtils {
    private final CommentUtils commentUtils;

    public UserUtils(CommentUtils commentUtils) {
        this.commentUtils = commentUtils;
    }


//    public void editComment(List<Comment> comments, Comment comment) {
//        comments.forEach(o -> {
//            if (o.getId() == comment.getId()) {
//                o.setState(comment.getState());
//            }
//        });
//    }


    public Comment findComment(List<Comment> comments, long id) {
        return comments.stream().filter(comment -> comment.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void addComment(List<Comment> comments, Comment comment) {
        comments.add(comment);
    }


//    public boolean containsFilmInPreparatoryComment(List<Comment> comments, long id) {
//
//        return comments.stream().filter(comment2 -> CommentState.PREPARATORY.equals(comment2.getState()))
//                .findFirst()
//                .map(comment1 -> commentUtils.containsFilm(comment1.getFilms(), id))
//                .orElse(false);
//    }


//    public List<Comment> getCommentsWithoutPreparatory(List<Comment> comments) {
//        return comments.stream().filter(comment -> comment.getState() != CommentState.PREPARATORY)
//                .filter(comment -> comment.getState() != CommentState.DELETED)
//                .collect(Collectors.toList());
//    }
//
//
//    public Comment getPreparatoryComment(List<Comment> comments) {
//        return comments.stream().filter(comment -> comment.getState().toString().equals("PREPARATORY"))
//                .findFirst()
//                .orElse(null);
//    }

    public void deleteComment(List<Comment> comments, long id) {
        comments.removeIf(comment -> comment.getId() == id);
    }

    public void addToBookMarks(List<Film> films, Film film) {
        films.add(film);
    }

    public void deleteFromBookmarks(List<Film> films, long id) {
        films.removeIf(film -> film.getId() == id);
    }

    public boolean containsFilmInBookmark(List<Film> films, long id) {
        return films.stream().anyMatch(film -> film.getId() == id);
    }
}
