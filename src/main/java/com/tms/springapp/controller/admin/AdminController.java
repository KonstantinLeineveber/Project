package com.tms.springapp.controller.admin;

import com.tms.springapp.config.security.SecurityUser;
import com.tms.springapp.model.user.Role;
import com.tms.springapp.model.user.Status;
import com.tms.springapp.model.user.User;
import com.tms.springapp.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final IService<User> userService;

    public AdminController(IService<User> userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String filmList(Model model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<User> users = userService.findAllWithPagination(pageable);
        int[] body = pagination(users);
        model.addAttribute("status", Status.values());
        model.addAttribute("roles", Role.values());
        model.addAttribute("users", users);
        model.addAttribute("body", body);
        model.addAttribute("amountOfElements", new int[]{5, 10, 20, 50});
        return "admin/users";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute User user,
                             Authentication authentication) {

        User userFromDb = userService.findById(user.getId());
        userFromDb.setStatus(user.getStatus());
        userFromDb.setRole(user.getRole());
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User userSecurity = securityUser.getUser();
        userSecurity.setRole(user.getRole());
        userSecurity.setStatus(user.getStatus());
        userService.save(userFromDb);
        return "redirect:/admin/users";
    }

    private int[] pagination(Page<?> object) {
        int[] body;
        int maxFilmPages = 7;
        int headMaxPage = 4;
        int bodyBeforeMaxPage = 4;
        int bodyAfterMaxPages = 2;
        int bodyCenterMaxPage = 3;


        if (object.getTotalPages() > maxFilmPages) {
            int totalPages = object.getTotalPages();
            int pageNumber = object.getNumber() + 1; //Отображаемый индекс страницы на единицу больше,чем тот,что мы имеем в коде.

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
            body = new int[object.getTotalPages()];
            for (int i = 0; i < object.getTotalPages(); i++) {
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
