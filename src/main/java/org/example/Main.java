package org.example;

import org.example.entities.Salon;
import org.example.service.ISalonService;
import org.example.service.SalonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "test");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        ISalonService salonService = applicationContext.getBean(SalonService.class);

        // Salon
        System.out.println("***** Salon Functions *****");
        // Find all
        System.out.println("\nFind All:");
        salonService.findAll().forEach(System.out::println);
        // Find by Id
        System.out.println("\nFind by Id:");
        System.out.println(salonService.findById(1));
        // Add
        System.out.println("\nAdd:");
        Salon salon = new Salon(11, "Salon 1", "Address 11", "0123456789", "2357");
        salonService.add(salon);
        salonService.findAll().forEach(System.out::println);
        // Find all by name
        System.out.println("\nFind all by name:");
        salonService.findAllByName("Salon 1").forEach(System.out::println);
        // Edit days open
        System.out.println("\nEdit days open:");
        salonService.findById(1).printDays();
        salonService.editDaysOpen("12345", 1);
        salonService.findById(1).printDays();
        // Delete by Id
        System.out.println("\nDelete by Id:");
        salonService.deleteById(2);
        salonService.findAll().forEach(System.out::println);
        // Find all salons open all days
        System.out.println("\nFind all open all days:");
        salonService.findAllOpenAllDays().forEach(System.out::println);

        System.out.println("\n***** Salon Errors *****");
        // Add with blank name
        Salon salon1 = new Salon(22, "", "Address 11", "0123456789", "2357");
        salonService.add(salon1);
        // Add with no days open
        Salon salon2 = new Salon(22, "Salon 22", "Address 11", "0123456789", "");
        salonService.add(salon2);
        // Add with invalid salon id
        Salon salon3 = new Salon(0, "Salon 22", "Address 11", "0123456789", "2357");
        salonService.add(salon3);
        // Add salon with Id already exists
        Salon salon4 = new Salon(1, "Salon 22", "Address 11", "0123456789", "2357");
        salonService.add(salon4);
    }
}