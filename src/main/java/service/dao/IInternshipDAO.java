package service.dao;

import entity.Internship;

public interface IInternshipDAO {
    /**
     * @param internshipId an internship id
     * @return an internship object
     */
    Internship getInternship(int internshipId);

    /**
     * @param internship an internship object
     */
    void saveInternship(Internship internship);
}
