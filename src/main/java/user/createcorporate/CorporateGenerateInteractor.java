package user.createcorporate;
import entity.Corporate;
import entity.User;
import service.ServerStatus;
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import user.requestconnect.exceptions.UserNotFoundException;

public class CorporateGenerateInteractor implements ICorporateGenerateInput {

    private final ICorporateDAO corporateDAO;
    private final IUserDAO userDAO;
    private final CorporateFactory corporateFactory;

    public CorporateGenerateInteractor(ICorporateDAO corporateDAO, IUserDAO userDAO, CorporateFactory corporateFactory){
        this.corporateDAO = corporateDAO;
        this.userDAO = userDAO;
        this.corporateFactory = corporateFactory;
    }

    public CorporateGenerateResponseModel create(CorporateGenerateRequestModel requestModel){

        String userId = requestModel.getUserId();
        User user;
        try {
            user = userDAO.getUser(userId);
        }
        catch (UserNotFoundException e) {
            return new CorporateGenerateResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        // validate that user is a corporate rep
        if (!user.getCorporateRep()) {
            return new CorporateGenerateResponseModel(ServerStatus.ERROR, "User is not a company rep.");
        }

        String companyName = requestModel.getCompanyName();
        String companyInfo = requestModel.getCompanyInfo();

        // validate that company name is unique
        if (corporateDAO.companyExists(companyName)) {
            return new CorporateGenerateResponseModel(ServerStatus.ERROR, "Company already exists.");
        }

        Corporate corporate = corporateFactory.create(user, companyName, companyInfo);
        int corporateId = corporateDAO.saveCorporate(corporate);

        return new CorporateGenerateResponseModel(ServerStatus.SUCCESS, "Corporate page created successfully!", corporateId);
    }

}

