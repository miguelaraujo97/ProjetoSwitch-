package switchisep.project.domain.resource;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResourceTest {

    @Test
    void shouldCreateValidResource() {

        // Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(10);
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        Email email = Email.createEmail("zemanel@yahoo.com.br");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        PercentageAllocation percentageAllocation = PercentageAllocation.createAllocation(50);
        CostPerHour costPerHour = CostPerHour.createCostPerHour(10.00);
        Role role = Role.createRole(Role.RoleEnum.Developer);

        // Act
        Resource resourceOne = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        // Assert
        assertEquals(resourceOne, resourceOne);
    }

    @Test
    void getProjectCodeSuccessfully() {

        //Arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        Email email = mock(Email.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        PercentageAllocation percentageAllocation = mock(PercentageAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        Resource resource = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        //Act
        ProjectCode projectCodeFound = resource.getProjectCode();

        //Assert
        assertEquals(projectCodeFound, projectCode);
    }

    @Test
    void getEmailSuccessfully() {

        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        Email email = Email.createEmail("amado@me.com");
        TimePeriod timePeriod = mock(TimePeriod.class);
        PercentageAllocation percentageAllocation = mock(PercentageAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        Resource resource = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        //Act
        Email emailFound = resource.getEmail();

        //Assert
        assertEquals(emailFound, email);
    }

    @Test
    void getResourceIDSuccessfully() {

        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        Email email = mock(Email.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        PercentageAllocation percentageAllocation = mock(PercentageAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        Resource resource = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        //Act
        ResourceID resourceIDFound = resource.getResourceID();

        //Assert
        assertNotNull(resourceIDFound);
    }

    @Test
    void getTimePeriodSuccessfully() {

        //Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(10);
        ProjectCode projectCode = mock(ProjectCode.class);
        Email email = mock(Email.class);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        PercentageAllocation percentageAllocation = mock(PercentageAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        Resource resource = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        //Act
        TimePeriod timePeriodFound = resource.getTimePeriod();

        //Assert
        assertEquals(timePeriodFound, timePeriod);
    }

    @Test
    void getPercentageOfAllocationSuccessfully() {

        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        Email email = mock(Email.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        PercentageAllocation percentageAllocation = PercentageAllocation.createAllocation(50);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        Resource resource = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        //Act
        PercentageAllocation percentageOfAllocationFound = resource.getPercentageAllocation();

        //Assert
        assertEquals(percentageOfAllocationFound, percentageAllocation);
    }

    @Test
    void getCostPerHourSuccessfully() {

        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        Email email = mock(Email.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        PercentageAllocation percentageAllocation = mock(PercentageAllocation.class);
        CostPerHour costPerHour = CostPerHour.createCostPerHour(10.00);
        Role role = mock(Role.class);
        Resource resource = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        //Act
        CostPerHour costPerHourFound = resource.getCostPerHour();

        //Assert
        assertEquals(costPerHourFound, costPerHour);
    }

    @Test
    void getRoleSuccessfully() {

        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        Email email = mock(Email.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        PercentageAllocation percentageAllocation = mock(PercentageAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = Role.createRole(Role.RoleEnum.Developer);
        Resource resource = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        //Act
        Role roleFound = resource.getRole();

        //Assert
        assertEquals(roleFound, role);
    }

    @Test
    void testEqualsForEqualObject() {

        // Arrange
        LocalDate startDate = LocalDate.now();

        LocalDate endDate = LocalDate.now().plusYears(10);

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        Email email = Email.createEmail("amado@me.com");

        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        PercentageAllocation percentageAllocation = PercentageAllocation.createAllocation(50);

        CostPerHour costPerHour = CostPerHour.createCostPerHour(10.00);

        Role role = Role.createRole(Role.RoleEnum.Developer);

        // Act
        Resource resourceOne = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        Resource resourceTwo = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        // Assert
        assertEquals(resourceOne, resourceTwo);
    }

    @Test
    void testEqualsForDifferentObjectDifferentClass() {

        // Arrange
        LocalDate startDate = LocalDate.now();

        LocalDate endDate = LocalDate.now().plusYears(10);

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        Email email = Email.createEmail("amado@me.com");

        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        PercentageAllocation percentageAllocation = PercentageAllocation.createAllocation(50);

        CostPerHour costPerHour = CostPerHour.createCostPerHour(10.00);

        Role role = Role.createRole(Role.RoleEnum.Developer);

        // Act
        Resource resourceOne = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        Object obj = new Object();

        // Assert
        assertNotEquals(resourceOne, obj);
    }

    @Test
    void testEqualsForDifferentObjectSameClass() {

        // Arrange
        LocalDate startDateOne = LocalDate.now();
        LocalDate startDateTwo = LocalDate.now().plusDays(1);

        LocalDate endDateOne = LocalDate.now().plusYears(10);
        LocalDate endDateTwo = LocalDate.now().plusYears(9);

        ProjectCode projectCodeOne = ProjectCode.createProjectCode("A123");
        ProjectCode projectCodeTwo = ProjectCode.createProjectCode("A124");

        Email emailOne = Email.createEmail("amado@me.com");
        Email emailTwo = Email.createEmail("amado@gmail.com");

        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDateOne, endDateOne);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDateTwo, endDateTwo);

        PercentageAllocation percentageAllocationOne = PercentageAllocation.createAllocation(50);
        PercentageAllocation percentageAllocationTwo = PercentageAllocation.createAllocation(51);

        CostPerHour costPerHourOne = CostPerHour.createCostPerHour(10.00);
        CostPerHour costPerHourTwo = CostPerHour.createCostPerHour(12.00);

        Role roleOne = Role.createRole(Role.RoleEnum.Developer);
        Role roleTwo = Role.createRole(Role.RoleEnum.ProductOwner);

        // Act
        Resource resourceOne = new Resource(projectCodeOne, emailOne, timePeriodOne, percentageAllocationOne,
                costPerHourOne, roleOne);

        Resource resourceTwo = new Resource(projectCodeTwo, emailTwo, timePeriodTwo, percentageAllocationTwo,
                costPerHourTwo, roleTwo);

        // Assert
        assertNotEquals(resourceOne, resourceTwo);
    }

    @Test
    void testHashCodeForDifferentObjectSameClass() {

        // Arrange
        LocalDate startDateOne = LocalDate.now();
        LocalDate startDateTwo = LocalDate.now().plusDays(1);

        LocalDate endDateOne = LocalDate.now().plusYears(10);
        LocalDate endDateTwo = LocalDate.now().plusYears(9);

        ProjectCode projectCodeOne = ProjectCode.createProjectCode("A123");
        ProjectCode projectCodeTwo = ProjectCode.createProjectCode("A124");

        Email emailOne = Email.createEmail("amado@me.com");
        Email emailTwo = Email.createEmail("amado@gmail.com");

        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDateOne, endDateOne);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDateTwo, endDateTwo);

        PercentageAllocation percentageAllocationOne = PercentageAllocation.createAllocation(50);
        PercentageAllocation percentageAllocationTwo = PercentageAllocation.createAllocation(51);

        CostPerHour costPerHourOne = CostPerHour.createCostPerHour(10.00);
        CostPerHour costPerHourTwo = CostPerHour.createCostPerHour(12.00);

        Role roleOne = Role.createRole(Role.RoleEnum.Developer);
        Role roleTwo = Role.createRole(Role.RoleEnum.ProductOwner);

        // Act
        Resource resourceOne = new Resource(projectCodeOne, emailOne, timePeriodOne, percentageAllocationOne,
                costPerHourOne, roleOne);

        Resource resourceTwo = new Resource(projectCodeTwo, emailTwo, timePeriodTwo, percentageAllocationTwo,
                costPerHourTwo, roleTwo);

        // Assert
        assertNotEquals(resourceOne.hashCode(), resourceTwo.hashCode());
    }

    @Test
    void testHashCodeForSameObject() {

        // Arrange
        LocalDate startDate = LocalDate.now();

        LocalDate endDate = LocalDate.now().plusYears(10);

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        Email email = Email.createEmail("amado@me.com");

        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        PercentageAllocation percentageAllocation = PercentageAllocation.createAllocation(50);

        CostPerHour costPerHour = CostPerHour.createCostPerHour(10.00);

        Role role = Role.createRole(Role.RoleEnum.Developer);

        // Act
        Resource resourceOne = new Resource(projectCode, email, timePeriod, percentageAllocation,
                costPerHour, role);

        // Assert
        assertEquals(resourceOne.hashCode(), resourceOne.hashCode());
    }
}