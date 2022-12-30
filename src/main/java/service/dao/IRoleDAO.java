package service.dao;

import entity.Role;

import java.util.UUID;

public interface IRoleDAO {
    Role save(Role role);

    Role getBy(UUID roleId);

    Role getBy(String name);

    void delete(UUID roleId);
}
