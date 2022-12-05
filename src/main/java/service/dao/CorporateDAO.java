package service.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import user.createcorporate.exceptions.CompanyNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class CorporateDAO implements ICorporateDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "insert into corporates (company, data) values (?, ?)";
    final String QUERY_ALL = "select * from corporates";
    final String SELECT_QUERY = "select * from corporates where company = ?";
    final String SELECT_SUBSTRING_QUERY = "SELECT * FROM corporates WHERE company LIKE CONCAT('%', ?, '%')";
    final String SELECT_QUERY_BY_ID = "select * from corporates where id = ?";
    final String EXISTS_QUERY = "select count(*) from corporates where company = ?";
    final String UPDATE_QUERY = "update corporates set data = ? where id = ?";

    @Override
    public Corporate getCorporate(String companyName) throws CompanyNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SELECT_QUERY, new CorporateDaoMapper(), companyName);
        }
        catch (DataAccessException e) {
            System.out.println("No company found.");
            throw new CompanyNotFoundException("No company found.");
        }
    }

    public Corporate getCorporate(int corporateId) throws CompanyNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SELECT_QUERY_BY_ID, new CorporateDaoMapper(), corporateId);
        }
        catch (DataAccessException e) {
            System.out.println("No company found.");
            throw new CompanyNotFoundException("No company found.");
        }
    }

    @Override
    public ArrayList<Corporate> getAllCorporates() {
        return (ArrayList<Corporate>) jdbcTemplate.query(QUERY_ALL, new CorporateDaoMapper());
    }

    @Override
    public ArrayList<Corporate> getCorporatesWithSubstring(String search) {
        try {
            return (ArrayList<Corporate>) jdbcTemplate.query(SELECT_SUBSTRING_QUERY, new CorporateDaoMapper(), search);
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw  e;
        }
    }

    @Override
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

    @Override
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
}
