package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ResourceJpa;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.resource.ResourceFactoryInterface;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ResourceRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EditRoleService {

    ResourceRepository resourceRepository;
    ResourceFactoryInterface resourceFactoryInterface;

    @Autowired
    public EditRoleService(ResourceRepository resourceRepository, ResourceFactoryInterface resourceFactoryInterface) {

        this.resourceRepository = resourceRepository;
        this.resourceFactoryInterface = resourceFactoryInterface;
    }

    public Resource setSpecialRole(String email, LocalDate startDate, String role, String projectCode) {

        Optional<ResourceJpa> previousResourceWithSpecialRole =
                resourceRepository.findByRoleAndEndDateIsAfterAndProjectCode(role, startDate, projectCode);
        Optional<ResourceJpa> specialRoleToBe =
                resourceRepository.findByEmailAndEndDateAfterAndProjectCode(email, startDate, projectCode);
        Resource resourceWithNewSpecialRole;
        Resource updatedPreviousResourceWithSpecialRole;

        if (previousResourceWithSpecialRole.isPresent() &&
                !previousResourceWithSpecialRole.get().getEmail().equals(email)) {

            updatedPreviousResourceWithSpecialRole = resourceFactoryInterface.createResource(
                    ProjectCode.createProjectCode(projectCode),
                    Email.createEmail(previousResourceWithSpecialRole.get().getEmail()),
                    TimePeriod.createTimePeriod(startDate, previousResourceWithSpecialRole.get().getEndDate()),
                    PercentageAllocation.createAllocation(
                            previousResourceWithSpecialRole.get().getPercentageAllocation()),
                    CostPerHour.createCostPerHour(specialRoleToBe.get().getCostPerHour()),
                    Role.createRole(Role.RoleEnum.valueOf(specialRoleToBe.get().getRole())));

            resourceRepository.save(updatedPreviousResourceWithSpecialRole);
            resourceRepository.editEndDate(previousResourceWithSpecialRole.get(), startDate.minusDays(1));
        }

        resourceWithNewSpecialRole = resourceFactoryInterface.createResource(ProjectCode.createProjectCode(projectCode),
                Email.createEmail(email),
                TimePeriod.createTimePeriod(startDate,specialRoleToBe.get().getEndDate()),
                PercentageAllocation.createAllocation(specialRoleToBe.get().getPercentageAllocation()),
                CostPerHour.createCostPerHour(specialRoleToBe.get().getCostPerHour()),
                Role.createRole(Role.RoleEnum.valueOf(role)));

        resourceRepository.save(resourceWithNewSpecialRole);
        resourceRepository.editEndDate(specialRoleToBe.get(),startDate.minusDays(1));

        return resourceWithNewSpecialRole;
    }

}

