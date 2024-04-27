package com.tai.admin.user;

import com.tai.common.entity.Role;
import com.tai.common.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public void saveUser(User user){
        String encodePass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePass);
        userRepository.save(user);
    }

    public boolean isEmailUnique(String email){
        User user = userRepository.getUserByEmail(email);
        return user == null;
    }
}
