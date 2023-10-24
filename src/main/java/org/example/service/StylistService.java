package org.example.service;

import org.example.entities.Stylist;

import java.util.List;

public class StylistService implements  IStylistService{
    @Override
    public List<Stylist> findAllBySalon(int salonId) {
        return null;
    }

    @Override
    public Stylist add(Stylist stylist) {
        return null;
    }

    @Override
    public boolean changeSalon(int newSalonId, int id) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public double findAverageSalaryForSalon(int salonId) {
        return 0;
    }
}
