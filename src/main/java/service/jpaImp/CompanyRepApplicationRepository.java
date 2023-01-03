package service.jpaImp;

import entity.CompanyRepApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepApplicationRepository extends JpaRepository<CompanyRepApplication, UUID> {
}
