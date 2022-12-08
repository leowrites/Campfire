package service.dao;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Internship;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import user.createcorporate.exceptions.CompanyNotFoundException;
import user.exceptions.InternshipNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

/** A data access object for the Internship database.
 */
public class InternshipDAO implements IInternshipDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO internships (data, companyid) values (?, ?)";
    final String FIND_BY_ID_QUERY = "SELECT * FROM internships WHERE id = ? ";
    final String FIND_BY_COMPANY_ID_QUERY = "SELECT * FROM internships WHERE companyid = ?";
    final String UPDATE_QUERY = "UPDATE internships SET data = ?, companyid = ? WHERE id = ?";



    /** Gets an Internship object from the database based on its id.
     * @param internshipId the id of the Internship object
     * @return the Internship object with id internshipId
     * @throws InternshipNotFoundException thrown when the internship with id internshipId does not exist
     */
    @Override
    public Internship getInternshipByID(int internshipId) throws InternshipNotFoundException {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new InternshipDaoMapper(), internshipId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No Internships Under" + internshipId + " found");
            throw new InternshipNotFoundException("No Internships Under" + internshipId + " found");
        }
    }


    /** Gets an Internship object from the database based on its id.
     * @param companyId the id of the Internship object
     * @return the Internship object with id companyId
     * @throws InternshipNotFoundException thrown when the internship with id companyId does not exist
     */
    @Override
    public ArrayList<Internship> getInternshipsByCompany(int companyId) throws InternshipNotFoundException {
        try {
            return (ArrayList<Internship>) jdbcTemplate.query(FIND_BY_COMPANY_ID_QUERY, new InternshipDaoMapper(),
                    companyId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No Internships Under" + companyId + " found");
            throw new InternshipNotFoundException("No Internships Under" + companyId + " found");
        }
    }

    /** Saves a new Internship object as a json.
     * @param internship the Internship object to be stored
     */
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

    /** Saves a new Internship object as a json and returns its id.
     * @param internship the Internship object to be stored
     * @return an int representing the id of the Internship object in the table
     */
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

    /** Updates an Internship object.
     * @param id the id of the Internship object to be updated
     * @param internship the new Internship object
     */
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
