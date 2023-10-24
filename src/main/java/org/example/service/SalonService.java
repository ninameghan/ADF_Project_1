package org.example.service;

import lombok.SneakyThrows;
import org.example.doa.ISalonDao;
import org.example.entities.Salon;
import org.example.service.exceptions.SalonIdAlreadyExistsException;
import org.example.service.exceptions.SalonMalformedException;
import org.example.service.exceptions.SalonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonService implements ISalonService{

    @Autowired
    private ISalonDao salonDao;
    @Override
    public int count() {
        return salonDao.count();
    }

    @Override
    public List<Salon> findAll() {
        return salonDao.findAll();
    }

    @Override
    @SneakyThrows
    public Salon add(Salon salon) {
        if (salon.getName().isBlank()){
            throw new SalonMalformedException("Salon name cannot be blank!");
        }
        if (salon.getAddress().isBlank()){
            throw new SalonMalformedException("Salon address cannot be blank!");
        }
        if (salon.getPhoneNumber().isBlank()){
            throw new SalonMalformedException("Salon phone number cannot be blank!");
        }
        if (salon.getDaysOpen().isBlank()){
            throw new SalonMalformedException("Salon days open cannot be blank!");
        }
        if (!salon.getDaysOpen().contains("1")){
            throw new SalonMalformedException("Salon must be open at least 1 day a week!");
        }
        if (salon.getId() < 0){
            throw new SalonMalformedException("Salon ID is invalid!");
        }
        if (salonDao.findById(salon.getId()).isPresent()){
            throw new SalonIdAlreadyExistsException("Salon with ID " + salon.getId() + " already exists!");
        }
        salonDao.save(salon);
        return salonDao.findById(salon.getId()).get();
    }

    @Override
    public List<Salon> findAllByName(String name) {
        return salonDao.findAllByName(name);
    }

    @Override
    @SneakyThrows
    public Salon findById(int id) {
        return salonDao.findById(id).orElseThrow(() -> new SalonNotFoundException("Salon with id " + id + " was not found!"));

    }

    @Override
    @SneakyThrows
    public boolean editDaysOpen(String newDaysOpen, int id) {
        if (newDaysOpen.isBlank()){
            throw new SalonMalformedException("Salon days open cannot be blank!");
        }
        if (!newDaysOpen.contains("1")){
            throw new SalonMalformedException("Salon must be open at least 1 day a week!");
        }
        if (salonDao.findById(id).isEmpty()){
            throw new SalonNotFoundException("Salon with id " + id + " was not found!");
        }
        return salonDao.editDaysOpen(newDaysOpen, id);
    }

    @Override
    @SneakyThrows
    public void deleteById(int id) {
        if (!salonDao.deleteById(id)) {
            throw new SalonNotFoundException("Salon with id " + id + " was not found!");
        }
    }

    @Override
    public List<Salon> findAllOpenAllDays() {
        List<Salon> allSalons = salonDao.findAll();
        return allSalons.stream().filter(s -> s.getDaysOpen().equals("1111111")).toList();
    }
}
