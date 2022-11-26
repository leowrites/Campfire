package user.createCorporatePage;

// CorporatePageFactory implements the interface PageFactory
// it creates CorporatePage instances in the PageGeneratorInteractor

import entity.CorporatePage;
import entity.CorporateRep;

public class PageFactory {

    public CorporatePage create(String pageName, CorporateRep owner, String companyName, String companyInfo) {
        return new CorporatePage(pageName, owner, companyName, companyInfo);
    }

}
