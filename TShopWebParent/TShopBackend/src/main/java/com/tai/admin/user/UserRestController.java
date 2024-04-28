package com.tai.admin.user;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class UserRestController {
    private UserService userService;

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@Param("id") Long id, @Param("email") String email){
        System.out.println("Here");
        return userService.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }
}
