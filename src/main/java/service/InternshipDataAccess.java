package service;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Internship;

import exceptions.InternshipNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;

@Repository
public class InternshipDataAccess implements InternshipDBGateway{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO internships (internship_company, internship_job_title, " +
            "creator_username, reviews) values (?, ?, ?, array[]::integer[])";
    final String FIND_BY_ID_QUERY = "SELECT * FROM internships WHERE internship_id = ? ";
    final String FIND_BY_COMPANY_ID_QUERY = "SELECT * FROM internships WHERE internship_company = ?";


    /**
     * @return a specific internship by ID
     */
    @Override
    public Internship getInternshipByID(int internshipID) throws InternshipNotFoundException {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new InternshipRowMapper(), internshipID);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No Internships Under" + internshipID + " found");
            throw new InternshipNotFoundException("No Internships Under" + internshipID + " found");
        }
    }


    /**
     * @return all internships under one company ID
     */
    @Override
    public ArrayList<Internship> getInternshipsByCompany(int internship_company) throws InternshipNotFoundException {
        try {
            return (ArrayList<Internship>) jdbcTemplate.query(FIND_BY_COMPANY_ID_QUERY, new InternshipRowMapper(), internship_company);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No Internships Under" + internship_company + " found");
            throw new InternshipNotFoundException("No Internships Under" + internship_company + " found");
        }
    }

    @Override
    public void saveInternship(Internship internship){
        try{
            jdbcTemplate.update(INSERT_QUERY,
                    internship.getCompany_id(),
                    internship.getJobTitle(),
                    internship.getCreator_username());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
