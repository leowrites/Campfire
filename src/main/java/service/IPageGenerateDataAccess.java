package service;
import entity.Page;
import entity.CorporatePage;
import user.createCorporatePage.exceptions.PageCreationFailed;

public interface IPageGenerateDataAccess {
    void createPage(Page page) throws PageCreationFailed;

    //this is an overload for corporatePage
    void createPage(CorporatePage corporatePage) throws PageCreationFailed;
}
