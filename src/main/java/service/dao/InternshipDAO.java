package service.dao;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Internship;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import user.exceptions.InternshipNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;


public class InternshipDAO implements IInternshipDAO {

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
            return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new InternshipDaoMapper(), internshipID);
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
            return (ArrayList<Internship>) jdbcTemplate.query(FIND_BY_COMPANY_ID_QUERY, new InternshipDaoMapper(),
                    internship_company);
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
    public int saveInternshipAndReturnId(Internship internship) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            ObjectMapper mapper = new ObjectMapper();
            // need to verify username is not duplicated
            String internshipString = mapper.writeValueAsString(internship);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, internshipString);
                statement.setInt(2, internship.getCompanyID());
                return statement;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
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
