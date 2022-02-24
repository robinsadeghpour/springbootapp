package com.demo.springbootapp.model;

import lombok.Data;

@Data
public class User {
    private String name;
    private String vorname;
    private String mail;

    public User(String name, String vorname, String mail) {
        this.name = name;
        this.vorname = vorname;
        this.mail = mail;
    }
}
