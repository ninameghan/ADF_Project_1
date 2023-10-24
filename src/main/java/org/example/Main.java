package org.example;

import org.example.service.ISalonService;
import org.example.service.IStylistService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "test");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        ISalonService salonService = applicationContext.getBean(ISalonService.class);
        IStylistService stylistService = applicationContext.getBean(IStylistService.class);

    }
}