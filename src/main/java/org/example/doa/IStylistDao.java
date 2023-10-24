package org.example.doa;

import org.example.entities.Stylist;

import java.util.List;

public interface IStylistDao {
    //Get all stylists in a particular salon.
    List<Stylist> findAllBySalon(int salonId);
    //Add a stylist, ensuring you add them to a salon.
    void save(Stylist stylist);
   //Move a stylist from one salon to another.
    boolean editSalon(int newSalonId, int id);
    //Delete a stylist.
    boolean deleteById(int id);

}
