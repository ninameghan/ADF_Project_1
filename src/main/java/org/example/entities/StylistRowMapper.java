package org.example.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StylistRowMapper implements RowMapper<Stylist> {
    @Override
    public Stylist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stylist stylist = new Stylist();
        stylist.setId(rs.getInt(1));
        stylist.setName(rs.getString(2));
        stylist.setPhoneNumber(rs.getString(3));
        stylist.setAnnualSalary(rs.getDouble(4));
        stylist.setSalonId(rs.getInt(5));
        return stylist;
    }
}
