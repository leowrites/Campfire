package user.createCorporatePage;

// CorporatePageFactory implements the interface PageFactory
// it creates CorporatePage instances in the PageGeneratorInteractor

import entity.CorporatePage;
import entity.CorporateRep;
import entity.Page;
import entity.User;

public class CorporatePageFactory implements PageFactory{



    CorporatePage newCorporatePage;

//    @Override
    public Page create(String pageName, User owner) {
        newCorporatePage = new CorporatePage(pageName, owner);
        return newCorporatePage;
    }

    @Override
    public Page create(String pageName, User owner, String companyName, String companyInfo) {
        return null;
    }

    public Page create(String pageName, CorporateRep owner, String companyName, String companyInfo) {
        newCorporatePage = new CorporatePage(pageName, owner, companyName, companyInfo);
        return newCorporatePage;
    }

    public CorporatePage addInfo(CorporatePage corporatePage, String companyName, String companyInfo){
        corporatePage.setCompanyName(companyName);
        corporatePage.setCompanyInfo(companyInfo);
        return corporatePage;
    }
}
