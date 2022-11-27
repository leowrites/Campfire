package service;

import entity.CorporatePage;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.createCorporatePage.PageDaoMapper;
import user.createCorporatePage.exceptions.PageCreationFailedException;

// PageGenerateDataAccess connects the use case to the database

@Repository
public class PageGenerateDataAccess implements IPageGenerateDataAccess{

    // variables needed for database connections
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String DATA_QUERY = "select * from corporate_pages where PageLabel = ?";
    final String INSERT_QUERY_CORPORATE =
            "INSERT INTO corporate_pages (PageLabel, UserID, CompanyName, CompanyInfo) values (?, ?, ?, ?)";


    /**
     * checkPageExists method checks if a page with the same name already exists
     * if so, a PageCreationFailedException will be thrown
     * @param pageLabel is the unique string identifier of a page, example: "Apple's Page"
     * @return the method returns null
     * @throws PageCreationFailedException: if a page already exists, the exception will be thrown
     */
    @Override
    public Page checkPageExists(String pageLabel) throws PageCreationFailedException {

        Page page = jdbcTemplate.queryForObject(DATA_QUERY, new PageDaoMapper(), pageLabel);
        if (page != null) {
            throw new PageCreationFailedException("Page already exists.");
        }
        return null;
    }

    @Override
    /**
     *
     * this method adds the data of the new CorporatePage object created to the database
     * @param corporatePage: a corporatePage object, which is a concrete class of Page
     */
    public void createPage(CorporatePage corporatePage){
        jdbcTemplate.update(INSERT_QUERY_CORPORATE, corporatePage.getPageLabel(), corporatePage.getPageOwner().getId(),
                    corporatePage.getCompanyName(), corporatePage.getCompanyInfo());
    }
}
