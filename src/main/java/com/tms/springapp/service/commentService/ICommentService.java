package com.tms.springapp.service.commentService;

import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.service.IService;

import java.util.List;

public interface ICommentService<T> extends IService<T> {

    List<Comment> viewComentsByFilm(Film film);

}
