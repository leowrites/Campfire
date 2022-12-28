package usecases.createinternship;

import entity.Corporate;
import entity.Internship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import service.dao.IInternshipDAO;
import service.ServerStatus;
import usecases.createcorporate.exceptions.CompanyNotFoundException;

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
            // create a new internship
            Internship internship = new Internship(inputDS.getCompanyID(),
                    inputDS.getJobTitle(), inputDS.getCreatorUsername());
            Internship savedInternship = internshipDataAccess.save(internship);

            Corporate corporate = corporateDAO.get(inputDS.getCompanyID());
            corporate.getInternships().add(savedInternship);
            corporateDAO.save(corporate);
            return new CreateInternshipResponseDS(ServerStatus.SUCCESS, "success");
        } catch (CompanyNotFoundException e){
            return new CreateInternshipResponseDS(ServerStatus.ERROR, e.getMessage());
        }
    }
}
