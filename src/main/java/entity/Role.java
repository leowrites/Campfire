package entity;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @ManyToMany
    private Collection<User> users;

    @ManyToMany
    @JoinTable
    private Collection<Privilege> privileges;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
