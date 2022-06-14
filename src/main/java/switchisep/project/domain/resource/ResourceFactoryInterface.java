package switchisep.project.domain.resource;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.*;

@Service
public interface ResourceFactoryInterface {

     Resource createResource (ProjectCode projectCode, Email email, TimePeriod timePeriod,
                              PercentageAllocation percentageAllocation, CostPerHour costPerHour, Role role);
}
