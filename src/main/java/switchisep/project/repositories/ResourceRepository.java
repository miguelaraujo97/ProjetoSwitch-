package switchisep.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import switchisep.project.datamodel.ResourceJpa;
import switchisep.project.datamodel.assemblers.ResourceDomainDataAssembler;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.TimePeriod;
import switchisep.project.repositories.interfaces.IResourceRepository;
import switchisep.project.repositories.jpa.ResourceJpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ResourceRepository implements IResourceRepository {

    ResourceJpaRepository resourceJpaRepository;

    ResourceDomainDataAssembler resourceDomainDataAssembler;

    @Autowired
    public ResourceRepository(ResourceJpaRepository resourceJpaRepository,
                              ResourceDomainDataAssembler resourceDomainDataAssembler) {

        this.resourceJpaRepository = resourceJpaRepository;
        this.resourceDomainDataAssembler = resourceDomainDataAssembler;
    }

    public List<Resource> findByEmail(String email) {

        List<ResourceJpa> resourcesFound = resourceJpaRepository.findByEmail(email);
        List<Resource> resourcesFoundToDomain = new ArrayList<>();

        for (ResourceJpa resourceJpa : resourcesFound) {

            resourcesFoundToDomain.add(resourceDomainDataAssembler.toDomain(resourceJpa));
        }

        return resourcesFoundToDomain;
    }

    public Resource save(Resource resource) {

        resourceJpaRepository.save(resourceDomainDataAssembler.toData(resource));
        return resource;
    }

    public Optional<ResourceJpa> findByRoleAndEndDateIsAfterAndProjectCode(
            String role, LocalDate date, String projectCode) {

        return resourceJpaRepository.findByRoleAndEndDateIsAfterAndProjectCode(role, date, projectCode);
    }

    public Optional<ResourceJpa> findByEmailAndEndDateAfterAndProjectCode(
            String email, LocalDate date, String projectCode) {

        return resourceJpaRepository.findByEmailAndEndDateAfterAndProjectCode(email, date, projectCode);
    }

    public ResourceJpa editEndDate(ResourceJpa resourceJpa, LocalDate date) {

        TimePeriod timePeriod = TimePeriod.createTimePeriod(resourceJpa.getStartDate(), date);
        resourceJpa.setEndDate(timePeriod.getEndDate());
        resourceJpaRepository.save(resourceJpa);

        return resourceJpa;
    }

    public List<ProjectCode> findAllProjectsCodeByEmail(String email) {

        List<ResourceJpa> resourceJpaList = resourceJpaRepository.findByEmail(email);

        List<ProjectCode> projectCodeList = new ArrayList<>();

        for (ResourceJpa resourceJpa : resourceJpaList) {

            ProjectCode projectCode = ProjectCode.createProjectCode(resourceJpa.getProjectCode());

            projectCodeList.add(projectCode);
        }

        return projectCodeList;
    }

    @Transactional
    public List<Resource> findAllByProjectCode(String projectCode) {

        List<Resource> resourceList = new ArrayList<>();

        List<ResourceJpa> resourceJpaList = resourceJpaRepository.findAllByProjectCode(projectCode);

        for (ResourceJpa resourceJpa : resourceJpaList) {

            Resource resource = resourceDomainDataAssembler.toDomain(resourceJpa);

            resourceList.add(resource);
        }

        return resourceList;
    }

    public Optional<Resource> findById(String resourceId) {

        Optional<ResourceJpa> resourceJpaOptional = resourceJpaRepository.findByResourceID(resourceId);

        if (!resourceJpaOptional.isPresent()) {

            return Optional.empty();
        }

        Resource resource = resourceDomainDataAssembler.toDomain(resourceJpaOptional.get());

        return Optional.of(resource);
    }

    public void deleteAll() {
        resourceJpaRepository.deleteAll();
    }

}





