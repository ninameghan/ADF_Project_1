package org.example.service;

import lombok.SneakyThrows;
import org.example.doa.ISalonDao;
import org.example.doa.IStylistDao;
import org.example.doa.dto.SalonOverview;
import org.example.entities.Stylist;
import org.example.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StylistService implements  IStylistService{
    
    @Autowired
    private IStylistDao stylistDao;

    @Autowired
    private ISalonDao salonDao;

    @Override
    public int count() {
        return stylistDao.count();
    }

    @Override
    @SneakyThrows
    public Stylist findById(int id) {
        return stylistDao.findById(id).orElseThrow(() -> new StylistNotFoundException("Stylist with id " + id + " was not found!"));
    }

    @Override
    public List<Stylist> findAllBySalon(int salonId) {
        return stylistDao.findAllBySalon(salonId);
    }

    @Override
    @SneakyThrows
    public Stylist add(Stylist stylist) {
        if (stylist.getName().isBlank()){
            throw new StylistMalformedException("Stylist name cannot be blank!");
        }
        if (stylist.getAnnualSalary() < 0){
            throw new StylistMalformedException("Stylist annual salary must be a positive amount!");
        }
        if (stylist.getSalonId() <= 0){
            throw new StylistMalformedException("Stylist salon ID is invalid!");
        }
        if (stylist.getId() <= 0){
            throw new StylistMalformedException("Stylist ID is invalid!");
        }
        if (salonDao.findById(stylist.getSalonId()).isEmpty()){
            throw new SalonNotFoundException("Salon with id " + stylist.getSalonId() + " was not found!");
        }
        if (stylistDao.findById(stylist.getId()).isPresent()){
            throw new StylistIdAlreadyExistsException("Stylist with ID " + stylist.getId() + " already exists!");
        }
        stylistDao.save(stylist);
        return stylistDao.findById(stylist.getId()).get();
    }

    @Override
    @SneakyThrows
    public boolean changeSalon(int newSalonId, int id) {
        if (newSalonId <= 0){
            throw new StylistMalformedException("Stylist salon ID is invalid!");
        }
        if (stylistDao.findById(id).isEmpty()){
            throw new StylistNotFoundException("Stylist with id " + id + " was not found!");
        }
        return stylistDao.editSalon(newSalonId, id);
    }

    @Override
    @SneakyThrows
    public void deleteById(int id) {
        if (!stylistDao.deleteById(id)) {
            throw new StylistNotFoundException("Stylist with id " + id + " was not found!");
        }
    }

    @Override
    @SneakyThrows
    public double findAverageSalaryForSalon(int salonId) {
        if (salonDao.findById(salonId).isEmpty()){
            throw new SalonNotFoundException("Salon with id " + salonId + " was not found!");
        }
        return stylistDao.findAverageSalaryForSalon(salonId);
    }

    @Override
    public List<SalonOverview> getSalonOverview() {
        return stylistDao.getSalonOverview();
    }
}
