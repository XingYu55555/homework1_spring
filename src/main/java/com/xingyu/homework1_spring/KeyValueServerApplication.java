package com.xingyu.homework1_spring;

import com.xingyu.homework1_spring.controllers.KeyValueController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeyValueServerApplication implements CommandLineRunner {
    private static final int PORT = 12345;
    private final KeyValueController keyValueController;

    @Autowired
    public KeyValueServerApplication(KeyValueController keyValueController) {
        this.keyValueController = keyValueController;
    }

    public static void main(String[] args) {
        SpringApplication.run(KeyValueServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        keyValueController.startServer(PORT);
    }
}
