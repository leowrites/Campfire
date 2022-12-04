package service.dao;

import entity.Corporate;
import user.createcorporate.exceptions.CompanyNotFoundException;

import java.util.ArrayList;

public interface ICorporateDAO {
    Corporate getCorporateFromCompanyName(String companyName) throws CompanyNotFoundException;
    ArrayList<Corporate> getAllCorporates();

    ArrayList<Corporate> getCorporatesWithSubstring();
    int saveCorporate(Corporate corporate);

    boolean companyExists(String companyName);

}
