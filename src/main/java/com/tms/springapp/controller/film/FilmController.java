package com.tms.springapp.controller.film;

import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.model.film.Genre;
import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import com.tms.springapp.service.userService.IUserService;
import com.tms.springapp.util.filmUtils.FilmUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/films")
public class FilmController {

    private final IService<Film> filmService;
    private final IService<Comment> commentService;
    private final IUserService<User> userService;
    private final FilmUtils filmUtils;


    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    public FilmController(IService<Film> filmService, IService<Comment> commentService, IUserService<User> userService, FilmUtils filmUtils) {
        this.filmService = filmService;
        this.commentService = commentService;
        this.userService = userService;
        this.filmUtils = filmUtils;

    }

    @GetMapping()
    public String filmList(Model model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Film> films = filmService.findAllWithPagination(pageable);
        int[] body = pagination(films);
        model.addAttribute("films", films);
        model.addAttribute("body", body);
        model.addAttribute("amountOfElements", new int[]{5, 10, 20, 50});
        return "films/filmCatalog";
    }

    @GetMapping("/{id}")
    public String filmPage(@PathVariable("id") int id, Model model, Comment comment) {
        model.addAttribute("film", filmService.findById(id));
        model.addAttribute("comment", comment);
        model.addAttribute("viewComentsByFilm", commentService.viewComentsByFilm(filmService.findById(id), comment));
        return "films/showFilm";
    }

    @GetMapping("/new")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String addNewFilm(Model model) {
        model.addAttribute("newFilm", new Film());
        model.addAttribute("genre", Genre.values());
        return "films/newFilm";
    }

    @PostMapping
    public String create(@ModelAttribute("newFilm") @Valid Film film,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", Genre.values());
            return "films/newFilm";
        }
        filmService.save(film);
        return "redirect:/films";
    }

    @GetMapping("/{id}/updateFilm")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("film", filmService.findById(id));
        model.addAttribute("genre", Genre.values());
        return "films/updateFilm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id,
                         @ModelAttribute("film") @Valid Film film,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", Genre.values());
            return "films/updateFilm";
        }
        filmService.save(film);
        return "redirect:/films";
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Film> delete(@PathVariable long id) {
        filmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private int[] pagination(Page<Film> films) {
        int[] body;
        int maxFilmPages = 7;
        int headMaxPage = 4;
        int bodyBeforeMaxPage = 4;
        int bodyAfterMaxPages = 2;
        int bodyCenterMaxPage = 3;


        if (films.getTotalPages() > maxFilmPages) {
            int totalPages = films.getTotalPages();
            int pageNumber = films.getNumber() + 1; //Отображаемый индекс страницы на единицу больше,чем тот,что мы имеем в коде.

            /*If current page greater than headMaxPage ,than we display page one and minus one,else we display pages one,two three.*/
            int[] head = (pageNumber > headMaxPage) ? new int[]{1, -1} : new int[]{1, 2, 3};
            /*If current page greater than bodyBeforeMaxPage and pageNumber less than totalPAges minus one,than we display pageNumber minus two,
             and pageNumber minus one,else we display nothing.*/
            int[] bodyBefore = (pageNumber > bodyBeforeMaxPage && pageNumber < totalPages - 1) ? new int[]{pageNumber - 2, pageNumber - 1} : new int[]{};
             /*If current page greater than bodyAfterMaxPages and pageNumber less than totalPAges minus three,than we display pageNumber plus one,
             and pageNumber plus two,else we display nothing.*/
            int[] bodyAfter = (pageNumber > bodyAfterMaxPages && pageNumber < totalPages - 3) ? new int[]{pageNumber + 1, pageNumber + 2} : new int[]{};
            /*If current page greater than bodyCenterMaxPage and pageNumber less than totalPAges minus two,than we display pageNumber,else we display nothing.*/
            int[] bodyCenter = (pageNumber > bodyCenterMaxPage && pageNumber < totalPages - 2) ? new int[]{pageNumber} : new int[]{};
            /*If current page less than totalPages minus three,than we display minus one and totalPages,else we display totalPages-2,totalPages-1 and totalPages.*/
            int[] tail = (pageNumber < totalPages - 3) ? new int[]{-1, totalPages} : new int[]{totalPages - 2, totalPages - 1, totalPages};

            body = merge(head, bodyBefore, bodyCenter, bodyAfter, tail);
        } else {
            body = new int[films.getTotalPages()];
            for (int i = 0; i < films.getTotalPages(); i++) {
                body[i] = i + 1;
            }
        }
        return body;
    }

    private int[] merge(int[]... intArrays) {
        return Arrays.stream(intArrays).flatMapToInt(Arrays::stream)
                .toArray();
    }
}