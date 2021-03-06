package com.demo.springbootapp.controller;

import com.demo.springbootapp.error.InvalidMailException;
import com.demo.springbootapp.error.NoUsersException;
import com.demo.springbootapp.error.UserNotFoundException;
import com.demo.springbootapp.model.User;
import com.demo.springbootapp.repository.UserRepository;
import com.demo.springbootapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) throws NoUsersException {
        List<User> users = new ArrayList<>();
        if (name == null) {
            users.addAll(userRepository.findAll());
        } else {
            users.addAll(userRepository.findByName(name));
        }
        if (users.isEmpty()) {
            throw new NoUsersException();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isEmpty()) {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws InvalidMailException {
        User newUser = userService.createNewUser(user);
        User _user = userRepository.save(newUser);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) throws InvalidMailException {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isEmpty()) {
            throw new UserNotFoundException();
        }
        User updatedUser = userService.updateUserInstance(user, userData.get());
        return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
