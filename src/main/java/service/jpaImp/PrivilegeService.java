package service.jpaImp;

import entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import service.dao.IPrivilegeDAO;

import java.util.Optional;
import java.util.UUID;

@Service
public class PrivilegeService implements IPrivilegeDAO {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public Privilege save(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public Privilege getBy(UUID privilegeId) {
        Optional<Privilege> privilegeOptional = privilegeRepository.findById(privilegeId);
        return privilegeOptional.orElse(null);
    }

    @Override
    public Privilege getBy(String name) {
        Privilege examplePrivilege = new Privilege();
        examplePrivilege.setName(name);
        Example<Privilege> example = Example.of(examplePrivilege);
        Optional<Privilege> privilegeOptional = privilegeRepository.findOne(example);
        return privilegeOptional.orElse(null);
    }

    @Override
    public void delete(UUID privilegeId) {
        privilegeRepository.deleteById(privilegeId);
    }
}
