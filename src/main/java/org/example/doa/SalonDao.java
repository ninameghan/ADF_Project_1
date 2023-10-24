package org.example.doa;

import org.example.entities.Salon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SalonDao implements ISalonDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Salon> findAll() {
        return null;
    }

    @Override
    public void save(Salon salon) {

    }

    @Override
    public List<Salon> findAllByName(String name) {
        return null;
    }

    @Override
    public Optional<Salon> findById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean editDaysOpen(String newDaysOpen, int id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
