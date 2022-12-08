package user.createinternship;

import entity.Corporate;
import entity.Internship;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import service.dao.IInternshipDAO;
import service.ServerStatus;
import java.util.ArrayList;

/** The createinternship use case interactor that calls the createInternship method from the
 * CreateInternshipInputBoundary input boundary. When initialized, takes in an object that
 * implements IInternshipDAO to access the internship database through, and an object that
 * implements IUserDAO to access the user database through.
 */
@Component
public class CreateInternshipInteractor implements CreateInternshipInputBoundary {

    @Autowired
    private final IInternshipDAO internshipDataAccess;
    @Autowired
    private final IUserDAO userDataAccess;
    @Autowired
    private final ICorporateDAO corporateDAO;

    public CreateInternshipInteractor(IInternshipDAO internshipDataAccess, IUserDAO userDataAccess, ICorporateDAO corporateDAO){
        this.internshipDataAccess = internshipDataAccess;
        this.userDataAccess = userDataAccess;
        this.corporateDAO = corporateDAO;
    }

    /** Creates an Internship and updates the internship database.
     * @param inputDS a CreateInternshipInputDS request model
     * @return a CreateInternshipResponseDS response model to be sent back to the client
     */
    @Override
    public CreateInternshipResponseDS createInternship(CreateInternshipInputDS inputDS) {

        try {
            //check if user has right to create a company
            User creator = userDataAccess.getUser(inputDS.getCreatorUsername());
            System.out.println(creator.getUsername());
            System.out.println(creator.getAccessLevel());
            if (!creator.getCorporateRep()){
                // if user is not a corporate rep, return failure.
                return new CreateInternshipResponseDS(ServerStatus.ERROR, "not authorized to create new internship");
            }
            // create a new internship
            Internship internship = new Internship(inputDS.getCompanyID(), new ArrayList<>(),
                    inputDS.getJobTitle(), inputDS.getCreatorUsername());

            int internshipId = internshipDataAccess.saveInternshipAndReturnId(internship);
            Corporate corporate = corporateDAO.getCorporate(inputDS.getCompanyID());
            corporate.getInternships().add(internshipId);
            corporateDAO.updateCorporate(corporate, corporate.getId());

            return new CreateInternshipResponseDS(ServerStatus.SUCCESS, "success");
        } catch (Exception e){
            return new CreateInternshipResponseDS(ServerStatus.ERROR, e.getMessage());
        }
    }

}
