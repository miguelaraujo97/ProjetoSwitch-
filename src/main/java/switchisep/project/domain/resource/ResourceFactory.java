package switchisep.project.domain.resource;

import org.springframework.stereotype.Component;
import switchisep.project.domain.valueobjects.*;

@Component
public class ResourceFactory implements ResourceFactoryInterface {

    public Resource createResource (ProjectCode projectCode, Email email, TimePeriod timePeriod,
                                    PercentageAllocation percentageAllocation, CostPerHour costPerHour, Role role){

        return new Resource(projectCode, email, timePeriod, percentageAllocation, costPerHour, role);
    }

}
