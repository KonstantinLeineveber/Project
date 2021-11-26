package com.tms.springapp.repository.comment;

import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.model.film.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByFilm(Film film);
}
