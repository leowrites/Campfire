package usecases.createcorporate;
import entity.Corporate;
import entity.User;
import service.ServerStatus;
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;

/** The createcorporate use case interactor that calls the create method from the
 * ICorporateGenerateInput input boundary. When initialized, takes in an object that implements
 * ICorporateDAO to acces the corporate database through, an object that implements IUserDAO to
 * access the user database through, and a corporate factory object to create corporate objects
 * in corporateFactory.
 */
public class CorporateGenerateInteractor implements ICorporateGenerateInput {

    private final ICorporateDAO corporateDAO;
    private final IUserDAO userDAO;
    private final CorporateFactory corporateFactory;

    public CorporateGenerateInteractor(ICorporateDAO corporateDAO, IUserDAO userDAO, CorporateFactory corporateFactory){
        this.corporateDAO = corporateDAO;
        this.userDAO = userDAO;
        this.corporateFactory = corporateFactory;
    }

    /** Creates a Corporate object as specified by the inputs in requestModel, then
     * updates the corporate database.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    public CorporateGenerateResponseModel create(CorporateGenerateRequestModel requestModel){

        String userId = requestModel.getUsername();
        User user;
        try {
            user = userDAO.getUser(userId);
        }
        catch (UserNotFoundException e) {
            return new CorporateGenerateResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        String companyName = requestModel.getCompanyName();
        String companyInfo = requestModel.getCompanyInfo();

        // validate that company name is unique
        if (corporateDAO.companyExists(companyName)) {
            return new CorporateGenerateResponseModel(ServerStatus.ERROR, "Company already exists.");
        }

        Corporate corporate = corporateFactory.create(user, companyName, companyInfo);
        Corporate savedCorporate = corporateDAO.save(corporate);

        return new CorporateGenerateResponseModel(ServerStatus.SUCCESS, "Corporate page created successfully!",
                savedCorporate.getId());
    }
}

