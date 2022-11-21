package service;
import entity.Page;
import entity.CorporatePage;
import user.createCorporatePage.exceptions.PageCreationFailedException;

public interface IPageGenerateDataAccess {
    void createPage(Page page) throws PageCreationFailedException;

    //this is an overload for corporatePage
    void createPage(CorporatePage corporatePage) throws PageCreationFailedException;
}
