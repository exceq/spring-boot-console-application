package com.example.springbootconsoleapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.FileNotFoundException;

// https://www.baeldung.com/spring-boot-console-app

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    FilesService filesService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length != 1) {
            System.out.println("Incorrect number of arguments.");
            System.out.println("Please enter filename.");
            return;
        }
        try {
            filesService.executeFunction(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
