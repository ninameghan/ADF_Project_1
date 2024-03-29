package org.example.doa;

import org.example.doa.dto.SalonOverview;
import org.example.doa.dto.SalonOverviewRowMapper;
import org.example.entities.Stylist;
import org.example.entities.StylistRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StylistDao implements IStylistDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from stylists", Integer.class);
    }

    @Override
    public Optional<Stylist> findById(int id) {
        try {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("id", id);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    "select * from stylists where stylist_id=:id",
                    mapSqlParameterSource,
                    new StylistRowMapper()));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Stylist> findAllBySalon(int salonId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("salon_id", salonId);
        return namedParameterJdbcTemplate.query(
                "select * from stylists where salon_id=:salon_id",
                mapSqlParameterSource,
                new StylistRowMapper());
    }

    @Override
    public void save(Stylist stylist) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", stylist.getId());
        mapSqlParameterSource.addValue("name", stylist.getName());
        mapSqlParameterSource.addValue("phone_number", stylist.getPhoneNumber());
        mapSqlParameterSource.addValue("annual_salary", stylist.getAnnualSalary());
        mapSqlParameterSource.addValue("salon_id", stylist.getSalonId());
        String SQL = "insert into stylists (stylist_id, stylist_name, stylist_phone_number, stylist_annual_salary, salon_id) " +
                "values (:id, :name, :phone_number, :annual_salary, :salon_id)";
        namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource);
    }

    @Override
    public boolean editSalon(int newSalonId, int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_salon_id", newSalonId);
        mapSqlParameterSource.addValue("id", id);
        String SQL = "update stylists set salon_id = :new_salon_id where stylist_id = :id";
        return namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource) == 1;
    }

    @Override
    public boolean deleteById(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update(
                "delete from stylists where stylist_id=:id",
                mapSqlParameterSource) == 1;
    }

    @Override
    public double findAverageSalaryForSalon(int salonId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("salon_id", salonId);
        String SQL = "select avg(stylist_annual_salary) from stylists where salon_id=:salon_id";
        return namedParameterJdbcTemplate.queryForObject(SQL, mapSqlParameterSource, Double.class);
    }

    @Override
    public List<SalonOverview> getSalonOverview() {
        String SQL = "select st.*, sa.salon_name from stylists st join salons sa where st.salon_id = sa.salon_id";
        return namedParameterJdbcTemplate.query(SQL, new SalonOverviewRowMapper());
    }
}
