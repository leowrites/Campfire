package service;
import entity.Internship;

import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class InternshipRowMapper implements RowMapper<Internship> {

    @Override
    public Internship mapRow(ResultSet rs, int rowNum) throws SQLException {
        Internship internship = new Internship();
        internship.setId(rs.getInt("internship_id"));
        internship.setCompany_id(rs.getInt("internship_company"));
        internship.setJobTitle(rs.getString("internship_job_title"));
        internship.setCreator_username(rs.getString("creator_username"));
        int[] reviews_array = (int[]) rs.getArray("reviews").getArray();
        internship.setReviews(Arrays.stream(reviews_array).boxed().collect(Collectors.toList()));

        return internship;
    }
}
