package service.dao;

import entity.Corporate;
import user.createcorporate.exceptions.CompanyNotFoundException;

import java.util.ArrayList;

public interface ICorporateDAO {
    Corporate getCorporate(String companyName) throws CompanyNotFoundException;
    ArrayList<Corporate> getAllCorporates();
    void saveCorporate(Corporate corporate);

    boolean companyExists(String companyName);
}
