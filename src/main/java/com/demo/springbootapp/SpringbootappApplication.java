package com.demo.springbootapp;

import com.demo.springbootapp.model.User;
import com.demo.springbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringbootappApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootappApplication.class, args);
	}

    @Override
    public void run(String... args) {
        userRepository.insert(new User("name", "robin", "sadeghpour"));
        System.out.println("Data inserted");

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }
}
