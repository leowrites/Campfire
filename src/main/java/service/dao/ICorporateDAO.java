package service.dao;

import entity.Corporate;
import usecases.createcorporate.exceptions.CompanyNotFoundException;

import java.util.List;
import java.util.UUID;

/** An interface for the Corporate data access object.
 */
public interface ICorporateDAO {

    /** Gets a corporate object based on the company name.
     * @param companyName the name of the company
     * @return the Corporate object with name companyName
     * @throws CompanyNotFoundException when the company with name companyName does not exist
     */
    Corporate get(String companyName) throws CompanyNotFoundException;

    /** Gets a Corporate object based on its id.
     * @param corporateId the id of the Corporate object
     * @return the Corporate object with id corporateId
     * @throws CompanyNotFoundException thrown when the company with id corporateId does not exist
     */
    Corporate get(UUID corporateId) throws CompanyNotFoundException;

    /** Gets all the Corporate objects in the database.
     * @return an ArrayList of all Corporate objects
     */
    List<Corporate> getAllCorporates();

    /** Saves a new Corporate object.
     * @param corporate the Corporate object to be stored
     * @return the saved Corporate object
     */
    Corporate save(Corporate corporate);

    /** Checks to see if the Corporate object with name companyName exists.
     * @param companyName the name of the company
     * @return a boolean corresponding to if the company exists or not
     */
    boolean companyExists(String companyName);

    /** Updates a Corporate object.
     * @param corporate the new Corporate object
     * @param corporateId the id of the Corporate object to be updated
     */
    Corporate update(Corporate corporate, UUID corporateId);
}
