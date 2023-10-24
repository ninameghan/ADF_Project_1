package org.example.service;

import org.example.entities.Salon;

import java.util.List;

public interface ISalonService {
    //Count
    int count();
    //Get all salons.
    List<Salon> findAll();
    //Create a new salon providing all data and return this new salon (from service layer).
    Salon add(Salon salon);
    //Get all salons by name e.g. all salons called "Hair Today".
    List<Salon> findAllByName(String name);
    // Get a salon by its primary key.
    Salon findById(int id);
    //Update salon by modifying the days on which it is open
    boolean editDaysOpen(String newDaysOpen, int id);
    //Delete a salon and all that salon's stylists.
    void deleteById(int id);
    //List all those salons open 7 days a week.
    List<Salon> findAllOpenAllDays();
}
