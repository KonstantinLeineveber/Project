package com.tms.springapp.controller.comment;

import com.tms.springapp.config.security.SecurityUser;
import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import com.tms.springapp.service.userService.IUserService;
import com.tms.springapp.util.commentUtils.CommentUtils;
import com.tms.springapp.util.filmUtils.FilmUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
@RequestMapping("/films")
public class CommentController {

    private final IService<Film> filmService;
    private final IService<Comment> commentService;
    private final IUserService<User> userService;
    private final FilmUtils filmUtils;
    private final CommentUtils commentUtils;

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    public CommentController(IService<Film> filmService,
                             IService<Comment> commentService,
                             IUserService<User> userService,
                             FilmUtils filmUtils,
                             CommentUtils commentUtils
    ) {
        this.filmService = filmService;
        this.commentService = commentService;
        this.userService = userService;
        this.filmUtils = filmUtils;
        this.commentUtils = commentUtils;
    }

    @PostMapping(path = "/{id}")
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public String addComment(
            @PathVariable long id,
            @ModelAttribute(value = "comment") String comment,
            Authentication authentication
    ) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Film film = filmService.findById(id);
        Comment commentary = new Comment();
        commentary.setAuthor(user);
        commentary.setFilm(film);
        commentary.setText(comment);
        commentary.setReleaseDate(new Date().toString());
        commentService.save(commentary);
        return "redirect:/films/" + film.getId();
    }


    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/deleteComment")
    public String deleteComment(@PathVariable int id,
                                @RequestParam(value = "commentId") Long commentId) {
        Film film = filmService.findById(id);
        commentService.deleteById(commentId);
        return "redirect:/films" + film.getId();
    }

}