package switchisep.project.domain.domainservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.resource.ResourceFactoryInterface;
import switchisep.project.domain.valueobjects.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateResourceDomainServiceTest {

    @InjectMocks
    CreateResourceDomainService createResourceDomainService;
    @Mock
    ResourceFactoryInterface resourceFactoryInterface;

    @Test
    void getListOfOverlappingResources_allResourcesOverlap_conditionOneIsMet() {

        //Arrange
        LocalDate startDate1 = LocalDate.now();
        LocalDate endDate1 = LocalDate.now().plusYears(3);
        LocalDate startDate2 = LocalDate.now().plusYears(1);
        LocalDate endDate2 = LocalDate.now().plusYears(4);
        TimePeriod timePeriodFirstCondition = TimePeriod.createTimePeriod(startDate2, endDate2);
        Resource resourceInList = mock(Resource.class);
        List<Resource> resourceListExpected = new ArrayList<>();
        resourceListExpected.add(resourceInList);
        when(resourceInList.getTimePeriod()).thenReturn(timePeriodFirstCondition);

        //Act
        List<Resource> resourceListActual = createResourceDomainService.getListOfOverlappingResources(startDate1,
                endDate1, resourceListExpected);

        //Assert
        assertEquals(resourceListExpected, resourceListActual);
    }

    @Test
    void getListOfOverlappingResources_allResourcesOverlap_conditionTwoIsMet() {

        //Arrange
        LocalDate startDate1 = LocalDate.now();
        LocalDate endDate1 = LocalDate.now().plusYears(3);
        LocalDate startDate2 = LocalDate.now().minusYears(1);
        LocalDate endDate2 = LocalDate.now().plusYears(2);
        TimePeriod timePeriodFirstCondition = TimePeriod.createTimePeriod(startDate2, endDate2);
        Resource resourceInList = mock(Resource.class);
        List<Resource> resourceListExpected = new ArrayList<>();
        resourceListExpected.add(resourceInList);
        when(resourceInList.getTimePeriod()).thenReturn(timePeriodFirstCondition);

        //Act
        List<Resource> resourceListActual = createResourceDomainService.getListOfOverlappingResources(startDate1,
                endDate1, resourceListExpected);

        //Assert
        assertEquals(resourceListExpected, resourceListActual);
    }

    @Test
    void getListOfOverlappingResources_allResourcesOverlap_conditionThreeIsMet() {

        //Arrange
        LocalDate startDate1 = LocalDate.now();
        LocalDate endDate1 = LocalDate.now().plusYears(3);
        LocalDate startDate2 = LocalDate.now();
        LocalDate endDate2 = LocalDate.now().plusYears(3);
        TimePeriod timePeriodFirstCondition = TimePeriod.createTimePeriod(startDate2, endDate2);
        Resource resourceInList = mock(Resource.class);
        List<Resource> resourceListExpected = new ArrayList<>();
        resourceListExpected.add(resourceInList);
        when(resourceInList.getTimePeriod()).thenReturn(timePeriodFirstCondition);

        //Act
        List<Resource> resourceListActual = createResourceDomainService.getListOfOverlappingResources(startDate1,
                endDate1, resourceListExpected);

        //Assert
        assertEquals(resourceListExpected, resourceListActual);
    }

    @Test
    void getListOfOverlappingResources_noResourcesOverlap() {

        //Arrange
        LocalDate startDate1 = LocalDate.now();
        LocalDate endDate1 = LocalDate.now().plusYears(3);
        LocalDate startDate2 = LocalDate.now().plusYears(4);
        LocalDate endDate2 = LocalDate.now().plusYears(5);
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate2, endDate2);
        LocalDate startDate3 = LocalDate.now().minusYears(4);
        LocalDate endDate3 = LocalDate.now().minusYears(3);
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate3, endDate3);
        Resource resource2 = mock(Resource.class);
        Resource resource3 = mock(Resource.class);
        List<Resource> resourceListExpected = new ArrayList<>();

        //Act
        List<Resource> resourceListActual = createResourceDomainService.getListOfOverlappingResources(startDate1,
                endDate1, resourceListExpected);

        //Assert
        assertEquals(resourceListExpected, resourceListActual);
    }

    @Test
    void totalPercentageOfAllocationIsLessThan100() {

        //Arrange
        int percentageOfAllocationToAdd = 20;
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(30);
        PercentageAllocation percentageOfAllocation2 = PercentageAllocation.createAllocation(20);
        Resource resource1 = mock(Resource.class);
        Resource resource2 = mock(Resource.class);
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource1);
        resourceList.add(resource2);
        when(resource1.getPercentageAllocation()).thenReturn(percentageOfAllocation1);
        when(resource2.getPercentageAllocation()).thenReturn(percentageOfAllocation2);

        //Act
        boolean result = createResourceDomainService.
                checkIfPercentageOfAllocationIsLessThanOrEqualTo100(percentageOfAllocationToAdd, resourceList);

        //Assert
        assertTrue(result);
    }

    @Test
    void totalPercentageOfAllocationIs100() {

        //Arrange
        int percentageOfAllocationToAdd = 20;
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(50);
        PercentageAllocation percentageOfAllocation2 = PercentageAllocation.createAllocation(30);
        Resource resource1 = mock(Resource.class);
        Resource resource2 = mock(Resource.class);
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource1);
        resourceList.add(resource2);
        when(resource1.getPercentageAllocation()).thenReturn(percentageOfAllocation1);
        when(resource2.getPercentageAllocation()).thenReturn(percentageOfAllocation2);

        //Act
        boolean result = createResourceDomainService.
                checkIfPercentageOfAllocationIsLessThanOrEqualTo100(percentageOfAllocationToAdd, resourceList);

        //Assert
        assertTrue(result);
    }

    @Test
    void totalPercentageOfAllocationIsMoreThan100() {

        //Arrange
        int percentageOfAllocationToAdd = 20;
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(50);
        PercentageAllocation percentageOfAllocation2 = PercentageAllocation.createAllocation(31);
        Resource resource1 = mock(Resource.class);
        Resource resource2 = mock(Resource.class);
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource1);
        resourceList.add(resource2);
        when(resource1.getPercentageAllocation()).thenReturn(percentageOfAllocation1);
        when(resource2.getPercentageAllocation()).thenReturn(percentageOfAllocation2);

        //Act
        boolean result = createResourceDomainService.
                checkIfPercentageOfAllocationIsLessThanOrEqualTo100(percentageOfAllocationToAdd, resourceList);

        //Assert
        assertFalse(result);
    }

    @Test
    void createResourceSuccessfully_emptyResourceList_noConditionMet() {

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";
        ProjectCode projectCode1 = ProjectCode.createProjectCode(projectCode);
        Email email1 = Email.createEmail(email);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(percentageOfAllocation);
        CostPerHour costPerHour1 = CostPerHour.createCostPerHour(costPerHour);
        Role role1 = Role.createRole(Role.RoleEnum.Developer);
        ResourceDTO resourceDTO = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        Resource resource = mock(Resource.class);
        List<Resource> resourceList = new ArrayList<>();
        when(resourceFactoryInterface.createResource(projectCode1, email1, timePeriod, percentageOfAllocation1,
                costPerHour1, role1)).thenReturn(resource);

        //Act
        Optional<Resource> resourceCreated = createResourceDomainService.createResource(resourceDTO, resourceList);

        //Assert
        assertEquals(Optional.of(resource), resourceCreated);
    }

    @Test
    void createResourceSuccessfully_emptyResourceList_noOverlappingResources() {

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        LocalDate startDate1 = LocalDate.now().plusYears(4);
        LocalDate endDate1 = LocalDate.now().plusYears(5);
        int percentageOfAllocation = 30;
        double costPerHour = 1.5;
        String role = "Developer";
        ProjectCode projectCode1 = ProjectCode.createProjectCode(projectCode);
        Email email1 = Email.createEmail(email);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate1, endDate1);
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(percentageOfAllocation);
        CostPerHour costPerHour1 = CostPerHour.createCostPerHour(costPerHour);
        Role role1 = Role.createRole(Role.RoleEnum.Developer);
        ResourceDTO resourceDTO = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        Resource resource = mock(Resource.class);
        Resource resource1 = mock(Resource.class);
        List<Resource> resourceList = new ArrayList<>();
        when(resourceFactoryInterface.createResource(projectCode1, email1, timePeriod, percentageOfAllocation1,
                costPerHour1, role1)).thenReturn(resource);
        resourceList.add(resource1);
        when(resource1.getTimePeriod()).thenReturn(timePeriod1);
        List<Resource> finalResourceList =
                createResourceDomainService.getListOfOverlappingResources(startDate, endDate, resourceList);

        //Act
        Optional<Resource> resourceCreated = createResourceDomainService.
                createResource(resourceDTO, finalResourceList);

        //Assert
        assertEquals(Optional.of(resource), resourceCreated);
    }

    @Test
    void createResourceSuccessfully_emptyResourceList_percentageOfAllocationIsValid() {

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";
        ProjectCode projectCode1 = ProjectCode.createProjectCode(projectCode);
        Email email1 = Email.createEmail(email);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(percentageOfAllocation);
        CostPerHour costPerHour1 = CostPerHour.createCostPerHour(costPerHour);
        Role role1 = Role.createRole(Role.RoleEnum.Developer);
        ResourceDTO resourceDTO = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        Resource resource = mock(Resource.class);
        Resource resource1 = mock(Resource.class);
        List<Resource> resourceList = new ArrayList<>();
        when(resourceFactoryInterface.createResource(projectCode1, email1, timePeriod, percentageOfAllocation1,
                costPerHour1, role1)).thenReturn(resource);
        resourceList.add(resource1);
        when(resource1.getTimePeriod()).thenReturn(timePeriod);
        when(resource1.getPercentageAllocation()).thenReturn(percentageOfAllocation1);
        List<Resource> finalResourceList = createResourceDomainService.
                getListOfOverlappingResources(startDate, endDate, resourceList);
        createResourceDomainService.checkIfPercentageOfAllocationIsLessThanOrEqualTo100(percentageOfAllocation,
                finalResourceList);

        //Act
        Optional<Resource> resourceCreated = createResourceDomainService.
                createResource(resourceDTO, resourceList);

        //Assert
        assertEquals(Optional.of(resource), resourceCreated);
    }

    @Test
    void createResourceUnsuccessfully_percentageOfAllocationIsOverTheLimit() {

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 60;
        double costPerHour = 1.5;
        String role = "Developer";
        ProjectCode projectCode1 = ProjectCode.createProjectCode(projectCode);
        Email email1 = Email.createEmail(email);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(percentageOfAllocation);
        CostPerHour costPerHour1 = CostPerHour.createCostPerHour(costPerHour);
        Role role1 = Role.createRole(Role.RoleEnum.Developer);
        ResourceDTO resourceDTO = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        Resource resource = mock(Resource.class);
        Resource resource1 = mock(Resource.class);
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource1);
        when(resource1.getPercentageAllocation()).thenReturn(percentageOfAllocation1);
        when(resource1.getTimePeriod()).thenReturn(timePeriod);
        List<Resource> finalResourceList = createResourceDomainService.
                getListOfOverlappingResources(startDate, endDate, resourceList);
        createResourceDomainService.checkIfPercentageOfAllocationIsLessThanOrEqualTo100(percentageOfAllocation,
                finalResourceList);

        //Act
        Optional<Resource> resourceCreated = createResourceDomainService.
                createResource(resourceDTO, resourceList);

        //Assert
        assertEquals(Optional.empty(), resourceCreated);
    }

}