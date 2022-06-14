package switchisep.project.datamodel.assemblers;

import org.junit.jupiter.api.Test;
import switchisep.project.datamodel.ResourceJpa;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.resource.ResourceFactory;
import switchisep.project.domain.valueobjects.*;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ResourceDomainDataAssemblerTest {

    @Test
    void convertDomainToDataSuccessfully() {

        //Arrange
        ResourceDomainDataAssembler resourceDomainDataAssembler = new ResourceDomainDataAssembler();
        Resource toData = mock(Resource.class);
        LocalDate startDate = LocalDate.of(2022, 6, 30);
        LocalDate endDate = LocalDate.of(2022, 11, 30);
        Email email = Email.createEmail("amado@gmail.com");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        ProjectCode projectCode = ProjectCode.createProjectCode("A12345");
        ResourceID resourceID = ResourceID.createResourceID("123");
        PercentageAllocation percentageOfAllocation = PercentageAllocation.createAllocation(50);
        CostPerHour costPerHour = CostPerHour.createCostPerHour(50.0);
        Role role = Role.createRole(Role.RoleEnum.valueOf("Developer"));

        when(toData.getEmail()).thenReturn(email);
        when(toData.getTimePeriod()).thenReturn(timePeriod);
        when(toData.getProjectCode()).thenReturn(projectCode);
        when(toData.getResourceID()).thenReturn(resourceID);
        when(toData.getRole()).thenReturn(role);
        when(toData.getPercentageAllocation()).thenReturn(percentageOfAllocation);
        when(toData.getCostPerHour()).thenReturn(costPerHour);

        ResourceJpa expected = new ResourceJpa("A12345", "amado@gmail.com", "123",
                startDate, endDate, 50, 50.0, "Developer");

        //Act
        ResourceJpa result = resourceDomainDataAssembler.toData(toData);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void convertDataToDomainSuccessfully() {

        //Arrange
        ResourceDomainDataAssembler resourceDomainDataAssembler = new ResourceDomainDataAssembler();
        ResourceFactory resourceFactory = new ResourceFactory();
        LocalDate startDate = LocalDate.of(2022, 6, 30);
        LocalDate endDate = LocalDate.of(2022, 11, 30);
        Email email = Email.createEmail("amado@gmail.com");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        ProjectCode projectCode = ProjectCode.createProjectCode("A12345");
        PercentageAllocation percentageOfAllocation = PercentageAllocation.createAllocation(50);
        CostPerHour costPerHour = CostPerHour.createCostPerHour(50.0);
        Role role = Role.createRole(Role.RoleEnum.valueOf("Developer"));
        ResourceJpa toDomain = new ResourceJpa("A12345", "amado@gmail.com", "123",
                startDate, endDate, 50, 50.0, "Developer");
        Resource expected = resourceFactory.createResource(projectCode, email, timePeriod, percentageOfAllocation,
                costPerHour, role);

        //Act
        Resource result = resourceDomainDataAssembler.toDomain(toDomain);

        //Assert
        assertEquals(expected, result);
    }
}