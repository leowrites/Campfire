package service;
import entity.Corporate;
import user.createCorporate.exceptions.CorporateCreationFailedException;

/**
 * This interface requires the CorporateGenerateDataAccess a createCorporate method,
 * it takes in a Corporate object, and throws CorporateCreationFailedException
 */
public interface ICorporateGenerateDataAccess {

    Corporate checkCorporateExists(String companyName) throws CorporateCreationFailedException;

    //this is an overload for corporate
    void createCorporate(Corporate corporate);
}
