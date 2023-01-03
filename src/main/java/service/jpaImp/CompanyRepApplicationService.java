package service.jpaImp;

import entity.CompanyRepApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyRepApplicationService implements ICompanyRepApplicationDAO{
    private final CompanyRepApplicationRepository repository;

    @Autowired
    public CompanyRepApplicationService(CompanyRepApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompanyRepApplication save(CompanyRepApplication application) {
        return repository.save(application);
    }

    @Override
    public CompanyRepApplication findBy(UUID id) {
        Optional<CompanyRepApplication> optionalApplication = repository.findById(id);
        return optionalApplication.orElse(null);
    }

    @Override
    public CompanyRepApplication findBy(Example<CompanyRepApplication> example) {
        Optional<CompanyRepApplication> optionalApplication = repository.findOne(example);
        return optionalApplication.orElse(null);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(UUID id) {
        return repository.existsById(id);
    }
}
