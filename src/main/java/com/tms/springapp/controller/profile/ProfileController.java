package com.tms.springapp.controller.profile;

import com.tms.springapp.config.security.SecurityUser;
import com.tms.springapp.model.film.Film;
import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

//import com.tms.springapp.util.commentUtils.CommentUtils;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final IService<Film> filmService;
    private final IService<User> userService;

//    private final CommentUtils commentUtils;

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);


    public ProfileController(IService<Film> filmService,
                             IService<User> userService
    ) {
        this.filmService = filmService;
        this.userService = userService;

    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable Long id, Model model,
                              Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        model.addAttribute("user", user);
        return "profile/profile";
    }


    @PutMapping(value = "/{id}")
    public String updateAvatar(@PathVariable long id, Authentication authentication,
                               @RequestParam("file") MultipartFile file) throws IOException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        if (file.isEmpty()) {
            return "redirect:/profile/" + user.getId() + "?noFileChosen=true";
        }
        String fileType = file.getOriginalFilename().split("\\.")[1].toLowerCase(Locale.ROOT);
        if (fileType.equals("png") || fileType.equals("jpg") || fileType.equals("jpeg")) {
            user.setAvatar(file.getBytes());
            userService.save(user);
            return "redirect:/profile/" + user.getId();
        }
        return "redirect:/profile/" + user.getId() + "?fileTypeError=true";
    }

    @PutMapping(value = "/{id}/editUser")
    public String editUser(@PathVariable long id, Authentication authentication,
                           @ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/profile";
        }
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User loginUser = securityUser.getUser();
        loginUser.setAddress(user.getAddress());
        loginUser.setEmail(user.getEmail());
        loginUser.setFirstName(user.getFirstName());
        loginUser.setLastName(user.getLastName());
        loginUser.setUserName(user.getUserName());
        userService.save(loginUser);
        return "redirect:/profile/" + user.getId();
    }

}
