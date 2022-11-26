package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CorporatePage;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.createCorporatePage.exceptions.PageCreationFailedException;

@Repository
public class PageGenerateDataAccess implements IPageGenerateDataAccess{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY_PAGE =
            "INSERT INTO corporate_pages (PageName, UserID) values (?, ?)";
    final String INSERT_QUERY_CORPORATE =
            "INSERT INTO corporate_pages (PageName, UserID, CompanyName, CompanyInfo) values (?, ?, ?, ?)";

    /**
     * save a new page object to db
     * @param page a Page object
     */

    @Override
    public void createPage(Page page) throws PageCreationFailedException {
        jdbcTemplate.update(INSERT_QUERY_PAGE, page.getPageLabel(), page.getPageOwner().getId());


    }

    //this is an overload for corporatePage
    public void createPage(CorporatePage corporatePage) throws PageCreationFailedException {
        jdbcTemplate.update(INSERT_QUERY_CORPORATE, corporatePage.getPageLabel(), corporatePage.getPageOwner().getId(),
                    corporatePage.getCompanyName(), corporatePage.getCompanyInfo());

    }
}
