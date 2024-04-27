package com.tai.admin.user;

import com.tai.common.entity.Role;
import com.tai.common.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/users")
    public ModelAndView listAllUsers(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.getAllUsers();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/users/new")
    public ModelAndView createNewUser(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = userService.getAllRoles();
        User user = new User();
        user.setEnabled(true);
        modelAndView.setViewName("user_form");
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @PostMapping("/users/save")
    public ModelAndView saveUser(User user, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        redirectAttributes.addAttribute("message", "The user has been saved successfully.");
        userService.saveUser(user);
        return modelAndView;
    }
}
