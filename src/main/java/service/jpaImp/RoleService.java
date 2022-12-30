package service.jpaImp;

import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import service.dao.IRoleDAO;

import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService implements IRoleDAO {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getBy(UUID roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        return roleOptional.orElse(null);
    }

    @Override
    public Role getBy(String name) {
        Role exampleRole = new Role();
        exampleRole.setName(name);
        Example<Role> example = Example.of(exampleRole);
        Optional<Role> roleOptional = roleRepository.findOne(example);
        return roleOptional.orElse(null);
    }

    @Override
    public void delete(UUID roleId) {
        roleRepository.deleteById(roleId);
    }
}
