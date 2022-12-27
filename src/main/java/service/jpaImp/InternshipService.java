package service.jpaImp;

import entity.Internship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import service.dao.IInternshipDAO;
import usecases.exceptions.InternshipNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class InternshipService implements IInternshipDAO {
    @Autowired
    InternshipRepository internshipRepository;

    @Override
    public Internship getInternshipByID(int InternshipId) throws InternshipNotFoundException {
        return null;
    }

    @Override
    public Internship getInternship(UUID internshipId) throws InternshipNotFoundException {
        Optional<Internship> internship = internshipRepository.findById(internshipId);
        if(internship.isPresent()) {
            return internship.get();
        } else {
            throw new InternshipNotFoundException("Internship Not Found!");
        }
    }

    @Override
    public ArrayList<Internship> getInternshipsByCompany(int companyID) throws InternshipNotFoundException {
        return null;
    }

    @Override
    public List<Internship> getInternshipByCompanyId(UUID companyId) {
        Internship exampleInternship = new Internship();
        exampleInternship.setCompanyId(companyId);
        Example<Internship> example = Example.of(exampleInternship);
        return internshipRepository.findAll(example);
    }

    @Override
    public int saveInternshipAndReturnId(Internship internship) {
        return 0;
    }

    @Override
    public Internship save(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public void updateInternship(int id, Internship internship) {

    }
}
