package org.example.entities;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonRowMapper implements RowMapper<Salon> {

    @Override
    public Salon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Salon salon = new Salon();
        salon.setId(rs.getInt(1));
        salon.setName(rs.getString(2));
        salon.setAddress(rs.getString(3));
        salon.setPhoneNumber(rs.getString(4));
        salon.setDaysOpen(rs.getString(5));
        return salon;
    }
}
