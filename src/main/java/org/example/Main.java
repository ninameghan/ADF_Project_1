package org.example;

import org.example.entities.Salon;
import org.example.service.ISalonService;
import org.example.service.IStylistService;
import org.example.service.SalonService;
import org.example.service.StylistService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "test");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        ISalonService salonService = applicationContext.getBean(SalonService.class);
        IStylistService stylistService = applicationContext.getBean(StylistService.class);

        // Salon
        System.out.println(applicationContext.getMessage("functions", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("functions", null, Locale.GERMAN));
        // Find all
        System.out.println(applicationContext.getMessage("findAll", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("findAll", null, Locale.GERMAN));
        salonService.findAll().forEach(System.out::println);
        System.out.println("\n");
        // Find by Id
        System.out.println(applicationContext.getMessage("findById", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("findById", null, Locale.GERMAN));
        System.out.println(salonService.findById(1));
        System.out.println("\n");
        // Add
        System.out.println(applicationContext.getMessage("add", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("add", null, Locale.GERMAN));
        Salon salon = new Salon(11, "Salon 1", "Address 11", "0123456789", "2357");
        salonService.add(salon);
        salonService.findAll().forEach(System.out::println);
        System.out.println("\n");
        // Find all by name
        System.out.println(applicationContext.getMessage("findAllByName", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("findAllByName", null, Locale.GERMAN));
        salonService.findAllByName("Salon 1").forEach(System.out::println);
        System.out.println("\n");
        // Edit days open
        System.out.println(applicationContext.getMessage("editDaysOpen", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("editDaysOpen", null, Locale.GERMAN));
        salonService.findById(1).printDays();
        salonService.editDaysOpen("12345", 1);
        salonService.findById(1).printDays();
        System.out.println("\n");
        // Delete by Id
        System.out.println(applicationContext.getMessage("deleteById", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("deleteById", null, Locale.GERMAN));
        salonService.deleteById(2);
        salonService.findAll().forEach(System.out::println);
        System.out.println("\n");
        // Find all salons open all days
        System.out.println(applicationContext.getMessage("findOpenAllDays", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("findOpenAllDays", null, Locale.GERMAN));
        salonService.findAllOpenAllDays().forEach(System.out::println);
        System.out.println("\n");

        System.out.println(applicationContext.getMessage("errors", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("errors", null, Locale.GERMAN));
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
        System.out.println("\n");

        System.out.println(applicationContext.getMessage("stylist", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("stylist", null, Locale.GERMAN));
        stylistService.getSalonOverview().forEach(System.out::println);
    }
}