package com.example.pokedex.services;

import com.example.pokedex.entities.User;
import com.example.pokedex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/20/2020
 */


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(String username) {
        if(username != null) {
            var user = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by username %s.", username)));
            return List.of(user);
        }
        return userRepository.findAll();
    }

    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    public User save(User user) {
        if(StringUtils.isEmpty(user.getPassword())) { // user.getPassword() == null || user.getPassword().isEmpty()
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "I need a password!!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void update(String id, User user) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by id %s.", id));
        }else if(!getCurrentUser().equals(user.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't change other's user information");
        }
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void delete(String id) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by id %s.", id));
        }
        userRepository.deleteById(id);
    }
}
