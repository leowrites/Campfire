package user.createCorporatePage;

// CorporatePageFactory implements the interface PageFactory
// it creates CorporatePage instances in the PageGeneratorInteractor

import entity.CorporatePage;
import entity.CorporateRep;

public class PageFactory {

    /**
     * This PageFactory can produce pages of different types.
     * For now, only the method for CorporatePage has been written.
     * @param pageLabel is the unique string identifier of the page. Example: "A"pple's Page"
     * @param owner is the CorporateRep user who manages this CorporatePage
     * @param companyName is the name of the company
     * @param companyInfo is a summary of the company
     * @return a CorporatePage object that is newly created
     */

    public CorporatePage create(String pageLabel, CorporateRep owner, String companyName, String companyInfo) {
        return new CorporatePage(pageLabel, owner, companyName, companyInfo);
    }

}
