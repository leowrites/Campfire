package service;

import entity.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.createCorporate.CorporateDaoMapper;
import user.createCorporate.exceptions.CorporateCreationFailedException;

// CorporateGenerateDataAccess connects the use case to the database

@Repository
public class CorporateGenerateDataAccess implements ICorporateGenerateDataAccess {

    // variables needed for database connections
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String DATA_QUERY = "select * from corporate_pages where PageLabel = ?";
    final String INSERT_QUERY_CORPORATE =
            "INSERT INTO corporate_pages (UserID, CompanyName, CompanyInfo) values (?, ?, ?)";


    /**
     * checkPageExists method checks if a page with the same name already exists
     * if so, a CorporateCreationFailedException will be thrown
     * @param companyName is the unique string identifier of a page, example: "Apple's Page"
     * @return the method returns null
     * @throws CorporateCreationFailedException : if a page already exists, the exception will be thrown
     */
    @Override
    public Corporate checkCorporateExists(String companyName) throws CorporateCreationFailedException {

        Corporate corporate = jdbcTemplate.queryForObject(DATA_QUERY, new CorporateDaoMapper(), companyName);
        if (corporate != null) {
            throw new CorporateCreationFailedException("Company already exists.");
        }
        return null;
    }

    @Override
    /**
     *
     * this method adds the data of the new Corporate object created to the database
     * @param corporate: a corporate object
     */
    public void createCorporate(Corporate corporate){
        jdbcTemplate.update(INSERT_QUERY_CORPORATE, corporate.getOwner().getId(),
                    corporate.getCompanyName(), corporate.getCompanyInfo());
    }
}
