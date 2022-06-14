package switchisep.project.repositories.interfaces;

import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ResourceJpa;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.valueobjects.ProjectCode;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface IResourceRepository {

    List<Resource> findByEmail(String email);

    Resource save(Resource resource);

    Optional<ResourceJpa> findByRoleAndEndDateIsAfterAndProjectCode(
            String role, LocalDate date, String projectCode);

    Optional<ResourceJpa> findByEmailAndEndDateAfterAndProjectCode(
            String email, LocalDate date, String projectCode);

    ResourceJpa editEndDate(ResourceJpa resourceJpa, LocalDate date);

    List<ProjectCode> findAllProjectsCodeByEmail(String email);

    List<Resource> findAllByProjectCode(String projectCode);

    Optional<Resource> findById(String resourceId);

}
