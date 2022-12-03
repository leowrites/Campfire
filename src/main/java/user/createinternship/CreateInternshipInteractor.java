package user.createinternship;

import entity.Internship;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.dao.IUserDAO;
import service.InternshipDBGateway;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateInternshipInteractor implements CreateInternshipInputBoundary {

    @Autowired
    final InternshipDBGateway internshipDataAccess;

    @Autowired
    final IUserDAO userDataAccess;

    public CreateInternshipInteractor(InternshipDBGateway internshipDataAccess, IUserDAO userDataAccess){
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
            if (creator.getAccessLevel() == 0){
                // if access level is 0, return failure.
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

    public void test_get_internship(){
        try{
            Internship internship = internshipDataAccess.getInternshipByID(1);
            System.out.println(internship.getReviews());

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void test_update_review(){
        try{
            List<Integer> integerList = new ArrayList<Integer>();
            Internship internship = new Internship(3, integerList, "plumber", "corporaterep@mail.utoronto.ca");

            internshipDataAccess.updateInternship(1, internship);

            List<Internship> internships = internshipDataAccess.getInternshipsByCompany(3);
            System.out.println(internships);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
