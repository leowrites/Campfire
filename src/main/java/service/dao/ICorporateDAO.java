package service.dao;

import entity.Corporate;
import user.createcorporate.exceptions.CompanyNotFoundException;

import java.util.ArrayList;

public interface ICorporateDAO {
    Corporate getCorporate(String companyName) throws CompanyNotFoundException;
    Corporate getCorporate(int corporateId) throws CompanyNotFoundException;
    ArrayList<Corporate> getAllCorporates();

    ArrayList<Corporate> getCorporatesWithSubstring(String search);
    int saveCorporate(Corporate corporate);
    boolean companyExists(String companyName);
    void updateCorporate(Corporate corporate, int corporateId);
}
