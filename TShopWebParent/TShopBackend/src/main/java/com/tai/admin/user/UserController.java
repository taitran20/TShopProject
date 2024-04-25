package com.tai.admin.user;

import com.tai.common.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.setViewName("user_form");
        return modelAndView;
    }
}
