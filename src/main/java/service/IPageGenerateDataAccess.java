package service;
import entity.Page;
import entity.CorporatePage;
import entity.User;
import user.createCorporatePage.exceptions.PageCreationFailedException;
import user.requestconnect.exceptions.UserNotFoundException;

/**
 * This interface requires the PageGenerateDataAccess to have two createPage methods,
 * one takes in a Page object, the other one takes in a CorporatePage object,
 * each throws PageCreationFailedException
 */
public interface IPageGenerateDataAccess {

    Page checkPageExists(String pageLabel) throws PageCreationFailedException;

    //this is an overload for corporatePage
    void createPage(CorporatePage corporatePage);
}
