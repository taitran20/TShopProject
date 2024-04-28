package com.tai.admin.user;

import com.tai.common.entity.Role;
import com.tai.common.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if(userRepository.getUserByEmail(user.getEmail()) != null){
            user.setPassword(userRepository.getUserByEmail(user.getEmail()).getPassword());
            userRepository.save(user);
            System.out.println(">>>");
        }
        else if (userRepository.getUserByEmail(user.getEmail()) == null){
            String encodePass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePass);
            userRepository.save(user);
        }

    }

    public boolean isEmailUnique(Long id,String email){
        User user = userRepository.getUserByEmail(email);
        if(user == null) return true;
        boolean isCreateNew = (id == null);
        if(isCreateNew){
            return false;
        }
        else {
            return Objects.equals(user.getId(), id);
        }
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Not found"));
    }

    public void deleteUser(Long id) throws UsernameNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        userRepository.delete(user);
    }
    public void changeUserStatus(Long id, boolean status){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        user.setEnabled(!status);
        userRepository.save(user);
    }
}
