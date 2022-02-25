package com.demo.springbootapp.services;

import com.demo.springbootapp.model.User;
import com.demo.springbootapp.support.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Validator validator;

    public User createNewUser(User user) throws Exception {
        checkIfEmailIsValid(user);
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setVorname(user.getVorname());
        newUser.setMail(user.getMail());
        return newUser;
    }

    public User updateUserInstance(User updateUser, User user) throws Exception {
        checkIfEmailIsValid(user);
        user.setName(updateUser.getName());
        user.setVorname(updateUser.getVorname());
        user.setMail(updateUser.getMail());
        return user;
    }

    private void checkIfEmailIsValid(User user) throws Exception {
        if (!validator.validateEmail(user.getMail())) {
            throw new Exception("Not a valid E-Mail");
        }
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
