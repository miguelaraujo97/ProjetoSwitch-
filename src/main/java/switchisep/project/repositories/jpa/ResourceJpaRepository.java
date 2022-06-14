package switchisep.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.ResourceJpa;
import switchisep.project.domain.valueobjects.ResourceID;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ResourceJpaRepository extends JpaRepository<ResourceJpa, ResourceID> {

    List<ResourceJpa> findByEmail(String email);

    Optional<ResourceJpa> findByRoleAndEndDateIsAfterAndProjectCode(String role, LocalDate date, String projectCode);

    Optional<ResourceJpa> findByEmailAndEndDateAfterAndProjectCode(String email, LocalDate date, String projectCode);

    Optional<ResourceJpa> findByResourceID(String resourceID);

    List<ResourceJpa> findAllByProjectCode(String projectCode);
}
