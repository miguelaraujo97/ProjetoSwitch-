package switchisep.project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.datamodel.ResourceJpa;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.resource.ResourceFactoryInterface;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ResourceRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditRoleServiceTest {

    @InjectMocks
    EditRoleService editRoleService;
    @Mock
    ResourceRepository resourceRepository;
    @Mock
    ResourceFactoryInterface resourceFactoryInterface;
    @Mock
    ResourceJpa resourceJpa;

//    @Test
//    void setSpecialRole() {
//        //arrange
//
//        ResourceJpa resourceWithNewSpecialRole = mock(ResourceJpa.class);
//
//        Optional<ResourceJpa> mambo = Optional.of(resourceWithNewSpecialRole);
//
//        String roleToBe = "ScrumMaster";
//        LocalDate startDate = LocalDate.of(2022,12,12);
//        LocalDate endDate = LocalDate.of(2025,12,12);
//        String email = "email@email.com";
//        String projectCode = "A2345";
//        ProjectCode projectCodeObject = ProjectCode.createProjectCode(projectCode);
//        Role roleObject = Role.createRole(Role.RoleEnum.valueOf(roleToBe));
//        Email emailObject = Email.createEmail(email);
//        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate,endDate);
//        PercentageAllocation  percentageAllocation = PercentageAllocation.createAllocation(50);
//        CostPerHour  costPerHour = CostPerHour.createCostPerHour(50);
//        Resource specialRoleToBe = new Resource(projectCodeObject,emailObject,timePeriod,percentageAllocation,costPerHour,roleObject);
//
//        when(resourceRepository.findByRoleAndEndDateIsAfterAndProjectCode(roleToBe,startDate,projectCode)).thenReturn(Optional.empty());
//        when(resourceRepository.findByEmailAndEndDateAfterAndProjectCode(email,startDate,projectCode)).thenReturn(mambo);
//        when(resourceFactoryInterface.createResource(projectCodeObject,emailObject,timePeriod,percentageAllocation, costPerHour,roleObject)).thenReturn(specialRoleToBe);
//        when(resourceJpa.getEndDate()).thenReturn(endDate);
//        when(resourceJpa.getPercentageAllocation()).thenReturn(percentageAllocation.getPercentageNumber());
//        when(resourceJpa.getCostPerHour()).thenReturn(costPerHour.getCostPerHour());
//
//
//        Resource expected = resourceFactoryInterface.createResource(projectCodeObject,emailObject,timePeriod,percentageAllocation,costPerHour,roleObject);
//
//        //act
//
//        Resource actual = editRoleService.setSpecialRole(email,startDate,roleToBe,projectCode);
//
//        //assert
//        Assertions.assertEquals(expected,actual);
//    }

}