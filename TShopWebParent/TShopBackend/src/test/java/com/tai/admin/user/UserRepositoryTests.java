package com.tai.admin.user;

import static org.assertj.core.api.Assertions.assertThat;
import com.tai.common.entity.Role;
import com.tai.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    public void testCreateNewUserWithOneRole(){
        Role roleAdmin = testEntityManager.find(Role.class, 1);
        User user = new User("tai@gmail.com","123456","Tai","Tran");
        user.addRole(roleAdmin);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRole(){
        User user = new User("tuan@gmail.com", "123456", "Tuan", "Tran");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(2);
        user.addRole(roleAssistant);
        user.addRole(roleEditor);

        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        List<User> userList = userRepository.findAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void testGetUserById(){
        User user = userRepository.findById(1L).get();
        System.out.println(user.getFirstName());
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails(){
        User user = userRepository.findById(1L).get();
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Test
    public void testUpdateUserRoles(){
        User user = userRepository.findById(2L).orElse(null);
        assert user != null;
        System.out.println("Before: " + user.getRoles().toString());
        Role roleEditor = roleRepository.findById(3).orElse(null);
        //Role roleSalePerson = new Role(4);
        //Role roleEditor = new Role(3);
        user.getRoles().remove(roleEditor);
        //user.addRole(roleSalePerson);
        userRepository.save(user);
        System.out.println("After: " + user.getRoles().toString());
    }

    @Test
    public void testDeleteUser(){
        User user = userRepository.findById(2L).orElse(null);
        assert user != null;
        userRepository.delete(user);
    }
}
