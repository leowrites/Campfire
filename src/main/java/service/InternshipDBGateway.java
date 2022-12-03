package service;

import entity.Internship;
import user.exceptions.InternshipNotFoundException;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;


public interface InternshipDBGateway {

    Internship getInternshipByID(int InternshipId) throws InternshipNotFoundException;

    ArrayList<Internship> getInternshipsByCompany(int companyID) throws InternshipNotFoundException;

    void saveInternship(Internship internship);

    void updateInternship(int id, Internship internship);
}
