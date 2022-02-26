package com.demo.springbootapp.services;

import com.demo.springbootapp.error.InvalidMailException;
import com.demo.springbootapp.model.User;
import com.demo.springbootapp.support.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Validator validator;

    public User createNewUser(User user) throws InvalidMailException {
        checkIfEmailIsValid(user.getMail());
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setVorname(user.getVorname());
        newUser.setMail(user.getMail());
        return newUser;
    }

    public User updateUserInstance(User updateUser, User user) throws InvalidMailException {
        checkIfEmailIsValid(updateUser.getMail());
        user.setName(updateUser.getName());
        user.setVorname(updateUser.getVorname());
        user.setMail(updateUser.getMail());
        return user;
    }

    private void checkIfEmailIsValid(String mail) throws InvalidMailException {
        if (!validator.validateEmail(mail)) {
            throw new InvalidMailException("Not a valid E-Mail");
        }
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
