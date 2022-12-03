package user.createinternship;

import entity.Internship;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.dao.IUserDAO;
import service.dao.IInternshipDAO;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateInternshipInteractor implements CreateInternshipInputBoundary {

    @Autowired
    final IInternshipDAO internshipDataAccess;

    @Autowired
    final IUserDAO userDataAccess;

    public CreateInternshipInteractor(IInternshipDAO internshipDataAccess, IUserDAO userDataAccess){
        this.internshipDataAccess = internshipDataAccess;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public CreateInternshipResponseDS createInternship(CreateInternshipInputDS inputDS) {

        try {
            //check if user has right to create a company
            User creator = userDataAccess.getUser(inputDS.getCreatorUsername());
            System.out.println(creator.getUsername());
            System.out.println(creator.getAccessLevel());
            if (!creator.getCorporateRep()){
                // if user is not a corporate rep, return failure.
                return new CreateInternshipResponseDS("Not authorized to create internship");
            }
            // create a new internship
            Internship internship = new Internship(inputDS.getCompanyID(), new ArrayList<>(),
                    inputDS.getJobTitle(), inputDS.getCreatorUsername());

            internshipDataAccess.saveInternship(internship);

            return new CreateInternshipResponseDS("success");
        } catch (Exception e){
            return new CreateInternshipResponseDS(e.getMessage());
        }
    }

}
