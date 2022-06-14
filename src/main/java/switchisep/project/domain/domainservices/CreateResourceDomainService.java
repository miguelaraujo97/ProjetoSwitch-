package switchisep.project.domain.domainservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.resource.ResourceFactoryInterface;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateResourceDomainService {

    ResourceFactoryInterface resourceFactoryInterface;

    @Autowired
    public CreateResourceDomainService(ResourceFactoryInterface resourceFactoryInterface) {

        this.resourceFactoryInterface = resourceFactoryInterface;
    }

    public List<Resource> getListOfOverlappingResources
            (LocalDate startDate, LocalDate endDate, List<Resource> resourceList) {

        List<Resource> listOfOverlappingResources = new ArrayList<>();

        for (Resource resourceToCompare : resourceList) {

            boolean overlapConditionOne = resourceToCompare.getTimePeriod().getStartDate().isAfter(startDate) &&
                    resourceToCompare.getTimePeriod().getStartDate().isBefore(endDate);

            boolean overlapConditionTwo = resourceToCompare.getTimePeriod().getEndDate().isAfter(startDate) &&
                    resourceToCompare.getTimePeriod().getEndDate().isBefore(endDate);

            boolean overlapConditionThree = resourceToCompare.getTimePeriod().getStartDate().isEqual(startDate) &&
                    resourceToCompare.getTimePeriod().getEndDate().isEqual(endDate);

            if (overlapConditionOne || overlapConditionTwo || overlapConditionThree) {

                listOfOverlappingResources.add(resourceToCompare);
            }
        }

        return listOfOverlappingResources;
    }

    public boolean checkIfPercentageOfAllocationIsLessThanOrEqualTo100
            (int percentageOfAllocation, List<Resource> resourceList) {

        final int MAX_PERCENTAGE_OF_ALLOCATION = 100;
        int totalPercentageOfAllocation = 0;

        for (Resource resource : resourceList) {

           totalPercentageOfAllocation += resource.getPercentageAllocation().getPercentageNumber();
        }

        return percentageOfAllocation + totalPercentageOfAllocation <= MAX_PERCENTAGE_OF_ALLOCATION;
    }

    public Optional<Resource> createResource(ResourceDTO resourceDTO, List<Resource> resourceList) {

        if (checkIfPercentageOfAllocationIsLessThanOrEqualTo100(resourceDTO.percentageAllocation,
                        getListOfOverlappingResources(resourceDTO.startDate, resourceDTO.endDate, resourceList))) {

            return Optional.of(resourceFactoryInterface.createResource(ProjectCode.createProjectCode(resourceDTO.projectCode),
                    Email.createEmail(resourceDTO.email),
                    TimePeriod.createTimePeriod(resourceDTO.startDate, resourceDTO.endDate),
                    PercentageAllocation.createAllocation(resourceDTO.percentageAllocation),
                    CostPerHour.createCostPerHour(resourceDTO.costPerHour),
                    Role.createRole(Role.RoleEnum.valueOf(resourceDTO.role))));

        } else {

            return Optional.empty();
        }
    }
}
