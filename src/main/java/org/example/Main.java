package org.example;

import org.example.service.ISalonService;
import org.example.service.IStylistService;
import org.example.service.SalonService;
import org.example.service.StylistService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "test");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        ISalonService salonService = applicationContext.getBean(SalonService.class);
        IStylistService stylistService = applicationContext.getBean(StylistService.class);

        // Salon
        System.out.println("***** Salon Functions *****");

    }
}