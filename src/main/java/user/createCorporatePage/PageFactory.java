package user.createCorporatePage;

import entity.Page;
import entity.User;

// The pageFactory interface must be implemented in concrete classes,
// such as corporatePageFactory and profilePageFactory.
public interface PageFactory {
    Page create(String pageName, User owner);
    Page create(String pageName, User owner, String companyName, String companyInfo);
}