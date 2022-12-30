package service;

import entity.Privilege;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IPrivilegeDAO;
import service.dao.IRoleDAO;

import java.util.Collection;
import java.util.List;

@Component
@Configuration
public class SpringSetup implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    IPrivilegeDAO privilegeDAO;

    @Autowired
    IRoleDAO roleDAO;


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");
        Privilege writeInternshipPrivilege = createPrivilegeIfNotFound("WRITE_INTERNSHIP_PRIVILEGE");
        createRoleIfNotFound("ROLE_ADMIN", List.of(writePrivilege, deletePrivilege, readPrivilege));
        createRoleIfNotFound("ROLE_COMPANY_REP", List.of(writeInternshipPrivilege));
        createRoleIfNotFound("ROLE_AUTHENTICATED_USER", List.of(readPrivilege));
        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeDAO.getBy(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            return privilegeDAO.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privilegeCollection) {
        Role role = roleDAO.getBy(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privilegeCollection);
            return roleDAO.save(role);
        }
        role.setPrivileges(privilegeCollection);
        return role;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_COMPANY_REP > ROLE_AUTHENTICATED_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }
}
