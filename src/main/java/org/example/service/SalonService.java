package org.example.service;

import org.example.entities.Salon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonService implements ISalonService{
    @Override
    public List<Salon> findAll() {
        return null;
    }

    @Override
    public Salon save(Salon salon) {
        return null;
    }

    @Override
    public List<Salon> findAllByName(String name) {
        return null;
    }

    @Override
    public Salon findById(int id) {
        return null;
    }

    @Override
    public boolean editDaysOpen(String newDaysOpen, int id) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Salon> findAllOpenAllDays() {
        return null;
    }
}
