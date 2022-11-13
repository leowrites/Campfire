package useCases.createCorporatePage;

// CorporatePageFactory implements the interface PageFactory
// it creates CorporatePage instances in the PageGeneratorInteractor

import entity.CorporatePage;
import entity.Page;
import entity.User;

public class CorporatePageFactory implements PageFactory {

    CorporatePage newCorporatePage;

    @Override
    public Page create(String pageName, User owner) {
        newCorporatePage = new CorporatePage(pageName, owner);
        return newCorporatePage;
    }

    public CorporatePage addInfo(CorporatePage corporatePage, String companyName, String companyInfo){
        corporatePage.setCompanyName(companyName);
        corporatePage.setCompanyInfo(companyInfo);
        return corporatePage;
    }
}
