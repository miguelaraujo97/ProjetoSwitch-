package switchisep.project.dto.assemblers;

import org.springframework.stereotype.Component;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.ResourceInputDTO;
import switchisep.project.dto.ResourceOutputDTO;
import switchisep.project.domain.resource.Resource;

@Component
public class ResourceDTOAssembler {

    public ResourceDTO toDTO (Resource resource){

    return new ResourceDTO(resource.getProjectCode().getCode(), resource.getEmail().getEmail(),
            resource.getTimePeriod().getStartDate(), resource.getTimePeriod().getEndDate(),
            resource.getPercentageAllocation().getPercentageNumber(), resource.getCostPerHour().getCostPerHour(),
            resource.getRole().getRole());
    }

    public ResourceDTO toDomainDTO (ResourceInputDTO resource, String projectCode){

        return new ResourceDTO(projectCode, resource.email,
                resource.startDate, resource.endDate, resource.percentageAllocation, resource.costPerHour,
                resource.role);
    }

    public ResourceOutputDTO toOutputDTO(Resource resource) {

        return new ResourceOutputDTO( resource.getResourceID().getResourceID(), resource.getEmail().getEmail(),
                resource.getTimePeriod().getStartDate(), resource.getTimePeriod().getEndDate(),
                resource.getPercentageAllocation().getPercentageNumber(), resource.getCostPerHour().getCostPerHour(),
                resource.getRole().getRole());
    }









}

