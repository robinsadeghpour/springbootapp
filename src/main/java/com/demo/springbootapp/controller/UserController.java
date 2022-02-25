package com.demo.springbootapp.controller;


import com.demo.springbootapp.model.User;
import com.demo.springbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(@RequestParam(required = false) String name) {
        List<User> users = new ArrayList<>();
        if (name == null) {
            users.addAll(userRepository.findAll());
        } else {
            users.addAll(userRepository.findByName(name));
        }
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        try {
            Optional<User> userData = userRepository.findById(id);
            if (userData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = new User(user.getName(), user.getVorname(), user.getMail());
            User _user = userRepository.save(newUser);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        try {
            Optional<User> userData = userRepository.findById(id);
            if (userData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            User updatedUser = updateUserInstance(user, userData.get());
            return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private User updateUserInstance(User updateUser, User user) {
        user.setName(updateUser.getName());
        user.setVorname(updateUser.getVorname());
        user.setMail(updateUser.getMail());
        return user;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}