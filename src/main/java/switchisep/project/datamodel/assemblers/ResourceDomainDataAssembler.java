package switchisep.project.datamodel.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ResourceJpa;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.valueobjects.*;
@Service
public class ResourceDomainDataAssembler {

       public ResourceJpa toData(Resource resource) {
       return new ResourceJpa(
               resource.getProjectCode().getCode(),
               resource.getEmail().getEmail(),
               resource.getResourceID().getResourceID(),
               resource.getTimePeriod().getStartDate(), resource.getTimePeriod().getEndDate(),
               resource.getPercentageAllocation().getPercentageNumber(),
               resource.getCostPerHour().getCostPerHour(),
               resource.getRole().getRole()
       );
    }

    public Resource toDomain(ResourceJpa resourceJpa) {
       return new Resource(ProjectCode.createProjectCode(resourceJpa.getProjectCode()),
               Email.createEmail(resourceJpa.getEmail()),
               ResourceID.createResourceID(resourceJpa.getResourceID()),
               TimePeriod.createTimePeriod(resourceJpa.getStartDate(), resourceJpa.getEndDate()),
               PercentageAllocation.createAllocation(resourceJpa.getPercentageAllocation()),
               CostPerHour.createCostPerHour(resourceJpa.getCostPerHour()),
               Role.createRole(Role.RoleEnum.valueOf(resourceJpa.getRole()))
       );
    }
}
