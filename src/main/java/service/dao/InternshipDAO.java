package service.dao;

import entity.Internship;

public class InternshipDAO implements IInternshipDAO{
    /**
     * @param internshipId an internship id
     * @return an internship object
     */
    @Override
    public Internship getInternship(int internshipId) {
        return new Internship();
    }

    /**
     * @param internship an internship object
     */
    @Override
    public void saveInternship(Internship internship) {

    }
}
