package com.tms.springapp.service.impl;

import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.repository.comment.CommentRepository;
import com.tms.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("commentService")
@Transactional
public class CommentService implements IService<Comment> {

    private final CommentRepository commentRepository;
    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        logger.info("Comment {} was successfully found", comment.getId());
        return comment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        List<Comment> comments = commentRepository.findAll();
        logger.info("All comments in db was successfully found");
        return comments;
    }

    @Transactional(readOnly = true)
    public List<Comment> viewComentsByFilm(Film film, Comment comment) {
        List<Comment> commentaries = commentRepository.findAll();
        List<Comment> commentsIdByFilm = new ArrayList<>();
        for (Comment comments : commentaries) {
            if (comments.getFilm().equals(film)) {
                commentsIdByFilm.add(comments);
            }
        }
        return commentsIdByFilm;
    }

    @Transactional(readOnly = true)
    public List<Comment> deleteComentByFilm(Film film, Comment comment) {
        List<Comment> commentaries = commentRepository.findAll();
        List<Comment> commentsIdByFilm = new ArrayList<>();
        for (Comment comments : commentaries) {
            if (comments.getFilm().equals(film)) {
                commentsIdByFilm.remove(comments);
            }
        }
        return commentsIdByFilm;
    }

    @Override
    public Comment save(Comment comment) {
        Comment commentToBd = commentRepository.save(comment);
        logger.info("Comment {} was successfully added to db", commentToBd.getId());

        return commentToBd;
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
        logger.info("Comment with id {} was successfully deleted form db", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Comment> findAllWithPagination(Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);
        logger.info("All comments in db was successfully found");
        return comments;
    }


}
