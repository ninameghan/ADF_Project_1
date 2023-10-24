package org.example.doa;

import org.example.entities.Salon;
import org.example.entities.SalonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SalonDao implements ISalonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from salons", Integer.class);
    }

    @Override
    public List<Salon> findAll() {
        return jdbcTemplate.query("select * from salons", new SalonRowMapper());
    }

    @Override
    public void save(Salon salon) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", salon.getId());
        mapSqlParameterSource.addValue("name", salon.getName());
        mapSqlParameterSource.addValue("address", salon.getAddress());
        mapSqlParameterSource.addValue("phone_number", salon.getPhoneNumber());
        mapSqlParameterSource.addValue("days_open", salon.getDaysOpen());
        String SQL = "insert into salons(salon_id, salon_name, salon_address, salon_phone_number, salon_days_open) " +
                "values (:id, :name, :address, :phone_number, :days_open)";
        namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource);
    }

    @Override
    public List<Salon> findAllByName(String name) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", name);
        return namedParameterJdbcTemplate.query(
                "select * from salons where salon_name=:name",
                mapSqlParameterSource,
                new SalonRowMapper());
    }

    @Override
    public Optional<Salon> findById(int id) {
        try {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("id", id);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    "select * from salons where salon_id=:id",
                    mapSqlParameterSource,
                    new SalonRowMapper()));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public boolean editDaysOpen(String newDaysOpen, int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_days_open", newDaysOpen);
        mapSqlParameterSource.addValue("id", id);
        String SQL = "update salons set salon_days_open = :new_days_open where salon_id = :id";
        return namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource) == 1;
    }

    @Override
    public boolean deleteById(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update(
                "delete from salons where salon_id=:id",
                mapSqlParameterSource) == 1;
    }
}
