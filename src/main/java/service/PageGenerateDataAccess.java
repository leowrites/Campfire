package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CorporatePage;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.createCorporatePage.PageDaoMapper;
import user.createCorporatePage.exceptions.PageCreationFailedException;

@Repository
public class PageGenerateDataAccess implements IPageGenerateDataAccess{

    // variables needed for database connections
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String DATA_QUERY = "select * from corporate_pages where PageLabel = ?";
    final String INSERT_QUERY_CORPORATE =
            "INSERT INTO corporate_pages (PageName, UserID, CompanyName, CompanyInfo) values (?, ?, ?, ?)";


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
     * this is an overload for corporatePage
     * @param corporatePage: a corporatePage object, which is a concrete class of Page
     */
    public void createPage(CorporatePage corporatePage){
        jdbcTemplate.update(INSERT_QUERY_CORPORATE, corporatePage.getPageLabel(), corporatePage.getPageOwner().getId(),
                    corporatePage.getCompanyName(), corporatePage.getCompanyInfo());
    }
}
