package com.example.conferenceweb.democonf.services;

import com.example.conferenceweb.democonf.model.Role;
import com.example.conferenceweb.democonf.model.User;
import com.example.conferenceweb.democonf.repositories.RoleRepository;
import com.example.conferenceweb.democonf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return null;
        }

        Optional<Role> roleOptional = roleRepository.findByName("ROLE_LISTENER");
        if (!roleOptional.isPresent()) {
            Role role = new Role();
            role.setName("ROLE_LISTENER");
            user.addRole(roleRepository.save(role));
        } else {
            roleOptional.ifPresent(user::addRole);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User changeRoles(String username) {
        User user = userRepository.findByUsername(username);
        if (!isSpeaker(username)) {
            Optional<Role> roleOptional = roleRepository.findByName("ROLE_SPEAKER");
            if (!roleOptional.isPresent()) {
                Role role = new Role();
                role.setName("ROLE_SPEAKER");
                user.addRole(roleRepository.save(role));
            } else {
                roleOptional.ifPresent(user::addRole);
            }
        }
        return userRepository.save(user);
    }

    public boolean isSpeaker(String username) {
        AtomicBoolean speakerFlag = new AtomicBoolean(false);
        User user = userRepository.findByUsername(username);
        user.getRoles().forEach(role -> {if (role.getName().equals("ROLE_SPEAKER")) { speakerFlag.set(true);}});

        return speakerFlag.get();
    }

}