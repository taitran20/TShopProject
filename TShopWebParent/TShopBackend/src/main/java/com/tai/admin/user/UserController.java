package com.tai.admin.user;

import com.tai.admin.exception.UserNotFoundException;
import com.tai.common.entity.Role;
import com.tai.common.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @GetMapping("")
    public ModelAndView listAllUsers(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.getAllUsers();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView createNewUser(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = userService.getAllRoles();
        User user = new User();
        user.setEnabled(true);
        modelAndView.setViewName("user_form");
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("pageTitle", "Create New User");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveUser(User user, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        redirectAttributes.addAttribute("message", "The user has been saved successfully.");
        userService.saveUser(user);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView updateUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Edit User");
        try {
            User user = userService.getUserById(id);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("user_form");
            modelAndView.addObject("roles", userService.getAllRoles());
        }catch (Exception exception){
            redirectAttributes.addAttribute("message", exception.getMessage());
            modelAndView.setViewName("redirect:/users");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        redirectAttributes.addAttribute("message", "The user has been deleted successfully.");
        userService.deleteUser(id);
        return modelAndView;
    }

    @GetMapping("/{id}/enabled/{status}")
    public ModelAndView changeUserStatus(@PathVariable("id") Long id, @PathVariable("status") boolean status, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        userService.changeUserStatus(id, status);
        modelAndView.setViewName("redirect:/users");
        redirectAttributes.addAttribute("message", "The user has been change successfully.");
        return modelAndView;
    }
}
