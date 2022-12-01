package user.createinternship;

import entity.Internship;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.IUserDataAccess;
import service.InternshipDBGateway;

import java.util.ArrayList;

@Component
public class CreateInternshipInteractor implements CreateInternshipInputBoundary {

    @Autowired
    final InternshipDBGateway internshipDataAccess;

    @Autowired
    final IUserDataAccess userDataAccess;

    public CreateInternshipInteractor(InternshipDBGateway internshipDataAccess, IUserDataAccess userDataAccess){
        this.internshipDataAccess = internshipDataAccess;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public CreateInternshipResponseDS createInternship(CreateInternshipInputDS inputDS) {

        try {
            //check if user has right to create a company
            User creator = userDataAccess.getUser(inputDS.getCreatorUsername());
            System.out.println(creator.getUsername());
            System.out.println(creator.getaccesslevel());
            if (creator.getaccesslevel() == 0){
                // if access level is 0, return failure.
                return new CreateInternshipResponseDS("Not authorized to create internship");
            }
            // create a new internship
            Internship internship = new Internship(inputDS.getCompanyID(), -1, new ArrayList<>(),
                    inputDS.getJobTitle(), inputDS.getCreatorUsername());

            internshipDataAccess.saveInternship(internship);

            return new CreateInternshipResponseDS("success");
        } catch (Exception e){
            return new CreateInternshipResponseDS(e.getMessage());
        }
    }

    public void test(){
        try{
            Internship internship = internshipDataAccess.getInternshipByID(1);
            System.out.println(internship.getReviews());

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
