package service;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Internship;

import user.exceptions.InternshipNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;

@Repository
public class InternshipDataAccess implements InternshipDBGateway{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO internships (data, companyid) values (?, ?)";
    final String FIND_BY_ID_QUERY = "SELECT * FROM internships WHERE id = ? ";
    final String FIND_BY_COMPANY_ID_QUERY = "SELECT * FROM internships WHERE companyid = ?";
    final String UPDATE_QUERY = "UPDATE internships SET data = ?, companyid = ? WHERE id = ?";



    /**x``
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
            ObjectMapper mapper = new ObjectMapper();
            // need to verify username is not duplicated
            String internshipString = mapper.writeValueAsString(internship);
            jdbcTemplate.update(INSERT_QUERY, internshipString, internship.getCompanyID());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateInternship(int id, Internship internship){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String internshipString = mapper.writeValueAsString(internship);
            jdbcTemplate.update(UPDATE_QUERY, internshipString, internship.getCompanyID(), id);

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
