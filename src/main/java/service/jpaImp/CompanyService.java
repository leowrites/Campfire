package service.jpaImp;

import entity.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import service.dao.ICorporateDAO;
import usecases.createcorporate.exceptions.CompanyNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class CompanyService implements ICorporateDAO {
    @Autowired
    CompanyRepository companyRepository;

    /**
     * Gets a corporate object based on the company name.
     *
     * @param companyName the name of the company
     * @return the Corporate object with name companyName
     * @throws CompanyNotFoundException when the company with name companyName does not exist
     */
    @Override
    public Corporate get(String companyName) throws CompanyNotFoundException {
        Corporate exampleCorporate = new Corporate();
        exampleCorporate.setCompanyName(companyName);
        Example<Corporate> example = Example.of(exampleCorporate);
        Optional<Corporate> company = companyRepository.findOne(example);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new CompanyNotFoundException("No company found!");
        }
    }

    /**
     * Gets a Corporate object based on its id.
     *
     * @param corporateId the id of the Corporate object
     * @return the Corporate object with id corporateId
     * @throws CompanyNotFoundException thrown when the company with id corporateId does not exist
     */
    @Override
    public Corporate get(UUID corporateId) throws CompanyNotFoundException {
        Optional<Corporate> company = companyRepository.findById(corporateId);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new CompanyNotFoundException("No Company Found!");
        }
    }

    /**
     * Gets all the Corporate objects in the database.
     *
     * @return an ArrayList of all Corporate objects
     */
    @Override
    public List<Corporate> getAllCorporates() {
        return companyRepository.findAll();
    }

    /**
     * Saves a new Corporate object.
     *
     * @param corporate the Corporate object to be stored
     * @return the saved Corporate object
     */
    @Override
    public Corporate save(Corporate corporate) {
        return companyRepository.save(corporate);
    }

    /**
     * Checks to see if the Corporate object with name companyName exists.
     *
     * @param companyName the name of the company
     * @return a boolean corresponding to if the company exists or not
     */
    @Override
    public boolean companyExists(String companyName) {
        Corporate exampleCorporate = new Corporate();
        exampleCorporate.setCompanyName(companyName);
        Example<Corporate> example = Example.of(exampleCorporate);
        return companyRepository.exists(example);
    }

    /**
     * Updates a Corporate object.
     *
     * @param corporate   the new Corporate object
     * @param corporateId the id of the Corporate object to be updated
     */
    @Override
    public Corporate update(Corporate corporate, UUID corporateId) {
        return companyRepository.save(corporate);
    }
}
