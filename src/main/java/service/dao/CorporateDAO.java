package service.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import usecases.createcorporate.exceptions.CompanyNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** A data access object for the Corporate database.
 */
public class CorporateDAO implements ICorporateDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "insert into corporates (company, data) values (?, ?)";
    final String QUERY_ALL = "select * from corporates";
    final String SELECT_QUERY = "select * from corporates where company = ?";
    final String SELECT_QUERY_BY_ID = "select * from corporates where id = ?";
    final String EXISTS_QUERY = "select count(*) from corporates where company = ?";
    final String UPDATE_QUERY = "update corporates set data = ? where id = ?";

    /** Gets a corporate object from the database based on the company name.
     * @param companyName the name of the company
     * @return the Corporate object with name companyName
     * @throws CompanyNotFoundException when the company with name companyName does not exist
     */
    @Override
    public Corporate get(String companyName) throws CompanyNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SELECT_QUERY, new CorporateDaoMapper(), companyName);
        }
        catch (DataAccessException e) {
            System.out.println("No company found.");
            throw new CompanyNotFoundException("No company found.");
        }
    }

    /** Gets a Corporate object from the database based on its id.
     * @param corporateId the id of the Corporate object
     * @return the Corporate object with id corporateId
     * @throws CompanyNotFoundException thrown when the company with id corporateId does not exist
     */
    public Corporate get(UUID corporateId) throws CompanyNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SELECT_QUERY_BY_ID, new CorporateDaoMapper(), corporateId);
        }
        catch (DataAccessException e) {
            System.out.println("No company found.");
            throw new CompanyNotFoundException("No company found.");
        }
    }

    /** Gets all the Corporate objects in the database.
     * @return an ArrayList of all Corporate objects
     */
    @Override
    public List<Corporate> getAllCorporates() {
        return (ArrayList<Corporate>) jdbcTemplate.query(QUERY_ALL, new CorporateDaoMapper());
    }

    /** Saves a new Corporate object as a json.
     * @param corporate the Corporate object to be stored
     * @return an int representing the id of the Corporate object in the table
     */
    public int saveCorporate(Corporate corporate) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            ObjectMapper m = new ObjectMapper();
            String corporateString = m.writeValueAsString(corporate);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, corporate.getCompanyName());
                statement.setString(2, corporateString);
                return statement;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        }
        catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
            return 0;
        }
    }

    /**
     * Saves a new Corporate object.
     *
     * @param corporate the Corporate object to be stored
     * @return the saved Corporate object
     */
    @Override
    public Corporate save(Corporate corporate) {
        return null;
    }

    /** Checks the Corporate database to see if the Corporate object with name companyName exists.
     * @param companyName the name of the company
     * @return a boolean corresponding to if the company exists or not
     */
    @Override
    public boolean companyExists(String companyName) {
        int count;
        try {
            count = jdbcTemplate.queryForObject(EXISTS_QUERY, Integer.class, companyName);
        }
        catch (DataAccessException e) {
            return false;
        }
        return count > 0;
    }

    /** Updates a Corporate object.
     * @param corporate the new Corporate object
     * @param corporateId the id of the Corporate object to be updated
     */
    public void updateCorporate(Corporate corporate, int corporateId) {
        try {
            ObjectMapper m = new ObjectMapper();
            DaoHelper.formatDate(m);
            String corporateString = m.writeValueAsString(corporate);
            jdbcTemplate.update(UPDATE_QUERY, corporateString, corporateId);
        }
        catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
        }
    }

    /**
     * Updates a Corporate object.
     *
     * @param corporate   the new Corporate object
     * @param corporateId the id of the Corporate object to be updated
     */
    @Override
    public Corporate update(Corporate corporate, UUID corporateId) {
        return null;
    }
}
