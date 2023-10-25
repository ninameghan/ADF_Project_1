package org.example.service;

import org.example.doa.dto.SalonOverview;
import org.example.entities.Stylist;

import java.util.List;

public interface IStylistService {
    //Count
    int count();
    //Find by id
    Stylist findById(int id);
    //Get all stylists in a particular salon.
    List<Stylist> findAllBySalon(int salonId);
    //Add a stylist, ensuring you add them to a salon.
    Stylist add(Stylist stylist);
    //Move a stylist from one salon to another.
    boolean changeSalon(int newSalonId, int id);
    //Delete a stylist.
    void deleteById(int id);
    //Determine the average salary of stylists in a particular salon.
    double findAverageSalaryForSalon(int salonId);
    //Get all stylists along with the name of the salon for which they work - use a record.
    List<SalonOverview> getSalonOverview();

}
