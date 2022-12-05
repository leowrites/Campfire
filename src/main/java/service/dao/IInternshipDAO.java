package service.dao;

import entity.Internship;
import user.exceptions.InternshipNotFoundException;
import java.util.ArrayList;


public interface IInternshipDAO {

    Internship getInternshipByID(int InternshipId) throws InternshipNotFoundException;

    ArrayList<Internship> getInternshipsByCompany(int companyID) throws InternshipNotFoundException;

    void saveInternship(Internship internship);

    int saveInternshipAndReturnId(Internship internship);

    void updateInternship(int id, Internship internship);
}
