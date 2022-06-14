package switchisep.project.domain.resource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;
import static org.mockito.Mockito.*;

class ResourceFactoryTest {

    @Test
    void shouldCreateValidResource() {

        //Arrange
        ResourceFactory resourceFactory = new ResourceFactory();
        ProjectCode projectCode = mock(ProjectCode.class);
        Email email = mock(Email.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        PercentageAllocation percentageAllocation = mock(PercentageAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        Resource resource = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        //Act
        Resource resourceCreated = resourceFactory.createResource(projectCode, email, timePeriod,
                percentageAllocation, costPerHour, role);

        //Assert
        assertNotNull(resourceCreated);
        assertEquals(resourceCreated, resource);
    }

}