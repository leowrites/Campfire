package service.dao;

import entity.Privilege;

import java.util.UUID;

public interface IPrivilegeDAO {
    Privilege save(Privilege privilege);

    Privilege getBy(UUID privilegeId);

    Privilege getBy(String name);

    void delete(UUID privilegeId);
}
