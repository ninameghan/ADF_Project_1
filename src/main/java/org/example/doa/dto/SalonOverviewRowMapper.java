package org.example.doa.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonOverviewRowMapper implements RowMapper<SalonOverview> {
    @Override
    public SalonOverview mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SalonOverview(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getInt(5),
                rs.getString(6));
    }
}
