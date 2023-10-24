package org.example.doa;

import org.example.entities.Stylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StylistDao implements IStylistDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Stylist> findAllBySalon(int salonId) {
        return null;
    }

    @Override
    public void save(Stylist stylist) {

    }

    @Override
    public boolean editSalon(int newSalonId, int id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
