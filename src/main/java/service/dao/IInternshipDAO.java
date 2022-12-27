package service.dao;

import entity.Internship;
import org.springframework.data.relational.core.sql.In;
import usecases.exceptions.InternshipNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public interface IInternshipDAO {

    Internship getInternshipByID(int InternshipId) throws InternshipNotFoundException;
    Internship getInternship(UUID internshipId) throws InternshipNotFoundException;

    ArrayList<Internship> getInternshipsByCompany(int companyID) throws InternshipNotFoundException;

    List<Internship> getInternshipByCompanyId(UUID companyId);

    int saveInternshipAndReturnId(Internship internship);

    Internship save(Internship internship);

    void updateInternship(int id, Internship internship);
}
