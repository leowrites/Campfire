package service.jpaImp;

import entity.CompanyRepApplication;
import org.springframework.data.domain.Example;

import java.util.UUID;

public interface ICompanyRepApplicationDAO {
    CompanyRepApplication save(CompanyRepApplication application);
    CompanyRepApplication findBy(UUID id);
    CompanyRepApplication findBy(Example<CompanyRepApplication> example);
    void delete (UUID id);
    boolean exists(UUID id);
}
