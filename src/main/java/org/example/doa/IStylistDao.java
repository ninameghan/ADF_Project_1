package org.example.doa;

import org.example.entities.Stylist;

import java.util.List;
import java.util.Optional;

public interface IStylistDao {
    //Count
    int count();
    //Find by id
    Optional<Stylist> findById(int id);
    //Get all stylists in a particular salon.
    List<Stylist> findAllBySalon(int salonId);
    //Add a stylist, ensuring you add them to a salon.
    void save(Stylist stylist);
   //Move a stylist from one salon to another.
    boolean editSalon(int newSalonId, int id);
    //Delete a stylist.
    boolean deleteById(int id);

}
