package user.createcorporate;

import entity.Corporate;
import entity.User;

/** A class in the createcorporate use case that serves to create Corporate objects as a factory.
 */
public class CorporateFactory {

    /**
     * This CorporateFactory can produce Corporate objects.
     * @param rep is the CorporateRep user who manages this Corporate
     * @param companyName is the name of the company
     * @param companyInfo is a summary of the company
     * @return a Corporate object that is newly created
     */

    public Corporate create(String rep, String companyName, String companyInfo) {
        return new Corporate(rep, companyName, companyInfo);
    }

}
