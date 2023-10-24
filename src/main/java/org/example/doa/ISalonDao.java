package org.example.doa;

import org.example.entities.Salon;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ISalonDao {
    //Get salon count
    int count();
    //Get all salons.
    List<Salon> findAll();
    //Create a new salon providing all data and return this new salon (from service layer).
    void save(Salon salon);
    //Get all salons by name e.g. all salons called "Hair Today".
    List<Salon> findAllByName(String name);
   // Get a salon by its primary key.
    Optional<Salon> findById(int id);
    //Update salon by modifying the days on which it is open
    boolean editDaysOpen(String newDaysOpen, int id);
    //Delete a salon and all that salon's stylists.
    boolean deleteById(int id);
}
