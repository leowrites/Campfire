package service.dao;

import entity.Internship;
import user.exceptions.InternshipNotFoundException;
import java.util.ArrayList;

/** An interface for the Internship data access object.
 */
public interface IInternshipDAO {

    /** Gets an Internship object based on its id.
     * @param internshipId the id of the Internship object
     * @return the Internship object with id internshipID
     * @throws InternshipNotFoundException thrown when the internship with id internshipID does not exist
     */
    Internship getInternshipByID(int internshipId) throws InternshipNotFoundException;

    /** Gets an Internship object based on its id.
     * @param companyId the id of the Internship object
     * @return the Internship object with id companyId
     * @throws InternshipNotFoundException thrown when the internship with id companyId does not exist
     */
    ArrayList<Internship> getInternshipsByCompany(int companyId) throws InternshipNotFoundException;

    /** Saves a new Internship object.
     * @param internship the Internship object to be stored
     */
    void saveInternship(Internship internship);

    /** Saves a new Internship object and returns its id.
     * @param internship the Internship object to be stored
     * @return an int representing the id of the Internship object
     */
    int saveInternshipAndReturnId(Internship internship);

    /** Updates an Internship object.
     * @param id the id of the Internship object to be updated
     * @param internship the new Internship object
     */
    void updateInternship(int id, Internship internship);
}
