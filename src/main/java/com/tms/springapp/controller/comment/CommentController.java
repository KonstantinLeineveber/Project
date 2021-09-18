package com.tms.springapp.controller.comment;

import com.tms.springapp.config.security.SecurityUser;
import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import com.tms.springapp.service.userService.IUserService;
import com.tms.springapp.util.commentUtils.CommentUtils;
import com.tms.springapp.util.filmUtils.FilmUtils;
import com.tms.springapp.util.userUtils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/films")
public class CommentController {

    private final IService<Film> filmService;
    private final IService<Comment> commentService;
    private final IUserService<User> userService;
    private final UserUtils userUtils;
    private final FilmUtils filmUtils;
    private final CommentUtils commentUtils;

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    public CommentController(IService<Film> filmService, IUserService<User> userService, IService<Comment> commentService, UserUtils userUtils, FilmUtils filmUtils, CommentUtils commentUtils) {
        this.filmService = filmService;
        this.commentService = commentService;
        this.userService = userService;
        this.userUtils = userUtils;
        this.filmUtils = filmUtils;
        this.commentUtils = commentUtils;
    }

    @PostMapping(value = "/films/{id}")
    public String commentCreate(Model model,
                                @PathVariable long id, Authentication authentication,
                                @ModelAttribute(value = "comment") String comment) {
        model.addAttribute("comment", new Comment());
        Film film = filmService.findById(id);
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Comment comment1 = new Comment();
        comment1.setUserName(user.getUserName());
        comment1.setFilm_id(film.getId());
        comment1.setComment(comment);
        commentUtils.addComents(comment1, comment);
        commentService.save(comment1);
        return "redirect:/films/" + film.getId();
    }

    @GetMapping(value = "/films/{id}")
    public String commentList(Model model, @PathVariable long id,
                              @ModelAttribute @Valid Comment comment) {
        Film film = filmService.findById(id);
        List<Comment> comments = commentService.findAll();
        model.addAttribute("comment", comment);
        return "redirect:/films/" + film.getId();
    }

}
