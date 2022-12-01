package service;

import entity.Internship;
import exceptions.InternshipNotFoundException;
import java.util.ArrayList;

import java.util.ArrayList;


public interface InternshipDBGateway {

    Internship getInternshipByID(int InternshipId) throws InternshipNotFoundException;

    ArrayList<Internship> getInternshipsByCompany(int companyID) throws InternshipNotFoundException;

    void saveInternship(Internship internship);

}
