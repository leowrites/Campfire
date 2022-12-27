package service.jpaImp;

import entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, UUID> {
}
