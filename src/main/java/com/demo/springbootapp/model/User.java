package com.demo.springbootapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class User {
    @Id
    private String id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Vorname is mandatory")
    private String vorname;
    @NotBlank(message = "Email is mandatory")
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
