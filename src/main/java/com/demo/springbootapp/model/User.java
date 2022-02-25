package com.demo.springbootapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class User {
    @Id
    private String id;
    private String name;
    private String vorname;
    private String mail;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String name, String vorname, String mail) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.vorname = vorname;
        this.mail = mail;
    }
}
