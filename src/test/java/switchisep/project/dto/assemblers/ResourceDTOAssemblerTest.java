package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.ResourceInputDTO;
import switchisep.project.dto.ResourceOutputDTO;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.resource.ResourceFactory;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ResourceDTOAssemblerTest {

    @InjectMocks
    ResourceDTOAssembler resourceDTOAssembler;

    @Test
    void convertResourceToDTO() {

        //Arrange
        ResourceDTOAssembler resourceDTOAssembler = new ResourceDTOAssembler();
        ResourceFactory resourceFactory = new ResourceFactory();
        String projectCode = "A123";
        int userID = 1;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";
        ProjectCode projectCode1 = ProjectCode.createProjectCode(projectCode);
        Email email = Email.createEmail("amado@me.com");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(percentageOfAllocation);
        CostPerHour costPerHour1 = CostPerHour.createCostPerHour(costPerHour);
        Role role1 = Role.createRole(Role.RoleEnum.Developer);

        Resource resource = resourceFactory.createResource(projectCode1, email, timePeriod,
                percentageOfAllocation1, costPerHour1, role1);
        ResourceDTO resourceDTOExpected = new ResourceDTO(projectCode, email.getEmail(), startDate, endDate,
                percentageOfAllocation, costPerHour, role);

        //Act
        ResourceDTO resourceDTOActual = resourceDTOAssembler.toDTO(resource);

        //Assert
        assertEquals(resourceDTOExpected, resourceDTOActual);
    }

    @Test
    void toDomainDTO(){

        ResourceInputDTO resourceInputDTO = new ResourceInputDTO();
        resourceInputDTO.costPerHour = 1;
        resourceInputDTO.email = "email@email.com";
        resourceInputDTO.endDate = LocalDate.of(2020, 10,10);
        resourceInputDTO.startDate = LocalDate.of(2020, 9,9);
        resourceInputDTO.role = "role";
        resourceInputDTO.percentageAllocation = 50;

        ResourceDTO resourceDTO = new ResourceDTO("A123", "email@email.com",
                LocalDate.of(2020, 10,10), LocalDate.of(2020, 9,9),
                50, 1, "role");

        ResourceDTO result = resourceDTOAssembler.toDomainDTO(resourceInputDTO, "A123");

        assertFalse(result.equals(resourceDTO));
    }

    @Test
    void toOutputDTO(){

        //Arrange
        ResourceDTOAssembler resourceDTOAssembler = new ResourceDTOAssembler();
        ResourceFactory resourceFactory = new ResourceFactory();
        String projectCode = "A123";
        int userID = 1;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";
        ProjectCode projectCode1 = ProjectCode.createProjectCode(projectCode);
        Email email = Email.createEmail("amado@me.com");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        PercentageAllocation percentageOfAllocation1 = PercentageAllocation.createAllocation(percentageOfAllocation);
        CostPerHour costPerHour1 = CostPerHour.createCostPerHour(costPerHour);
        Role role1 = Role.createRole(Role.RoleEnum.Developer);
        Resource resource = resourceFactory.createResource(projectCode1, email, timePeriod,
                percentageOfAllocation1, costPerHour1, role1);

        ResourceOutputDTO resourceDTO = new ResourceOutputDTO(resource.getResourceID().getResourceID(),
                resource.getEmail().getEmail(), resource.getTimePeriod().getStartDate(),
                resource.getTimePeriod().getEndDate(), resource.getPercentageAllocation().getPercentageNumber(),
                resource.getCostPerHour().getCostPerHour(), resource.getRole().getRole());

        ResourceOutputDTO result = resourceDTOAssembler.toOutputDTO(resource);

        assertEquals(resourceDTO, result);
    }
}