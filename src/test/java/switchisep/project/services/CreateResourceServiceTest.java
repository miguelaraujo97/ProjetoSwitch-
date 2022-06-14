package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.ResourceOutputDTO;
import switchisep.project.dto.assemblers.ResourceDTOAssembler;
import switchisep.project.domain.domainservices.CreateResourceDomainService;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ResourceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateResourceServiceTest {

    @InjectMocks
    CreateResourceService createResourceService;

    @Mock
    ResourceRepository resourceRepository;

    @Mock
    CreateResourceDomainService createResourceDomainService;

    @Mock
    ResourceDTOAssembler resourceDTOAssembler;

    @Test
    void getResourceByEmailResourcesFound() {

        //arrange
        List<Resource> expected = new ArrayList<>();
        Resource resource1 = mock(Resource.class);
        Resource resource2 = mock(Resource.class);
        expected.add(resource1);
        expected.add(resource2);
        Email email = Email.createEmail("amado@me.com");
        when(resourceRepository.findByEmail(email.getEmail())).thenReturn(expected);

        //act
        List<Resource> actual = createResourceService.getResourceByEmail(email.getEmail());

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void getResourceByUserIdResourcesNotFound() {

        //arrange
        List<Resource> expected = new ArrayList<>();
        Email email = Email.createEmail("amado@gmail.com");
        when(resourceRepository.findByEmail(email.getEmail())).thenReturn(expected);

        //act
        List<Resource> actual = createResourceService.getResourceByEmail(email.getEmail());

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void createAndAddSuccessfullyDifferentDatesPercentageOfAllocation50() {

        //arrange

        ProjectCode projectCode = ProjectCode.createProjectCode("a123");
        Email email = Email.createEmail("amado@me.com");
        ResourceDTO resourceDTO = new ResourceDTO("a123", "amado@me.com", LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25),50, 30, "Developer");

        LocalDate startDate = LocalDate.of(1233, 12, 12);
        LocalDate endDate = LocalDate.of(1233, 12, 25);

        Resource resource1 = mock(Resource.class);
        Resource resource3 = new Resource(projectCode, email,
                TimePeriod.createTimePeriod(startDate,endDate ),
                PercentageAllocation.createAllocation(50), CostPerHour.createCostPerHour(30), Role.createRole(Role.RoleEnum.Developer));
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource3);
        List<Resource> overLappingResources = new ArrayList<>();

        when(createResourceService.getResourceByEmail(email.getEmail())).thenReturn(resourceList);

        when(createResourceDomainService.createResource(resourceDTO, resourceList)).thenReturn(Optional.of(resource3));

        when(resourceRepository.save(resource3)).thenReturn(resource3);

        ResourceOutputDTO resourceOutputDTO = new ResourceOutputDTO("R01", "amado@me.com",
                LocalDate.of(1233,12,12),
                LocalDate.of(1233,12,25),50,30,"Developer");

        Optional<ResourceOutputDTO> expected = Optional.of(resourceOutputDTO);

        when(resourceDTOAssembler.toOutputDTO(resource3)).thenReturn(resourceOutputDTO);

        //act

        Optional<ResourceOutputDTO> actual = createResourceService.createAndAdd(resourceDTO);

        //assert

        assertEquals(expected, actual);


    }

    @Test
    void createAndAddUnsuccessfullySameDatesPercentageOfAllocation100() {

        //arrange

        ProjectCode projectCode = ProjectCode.createProjectCode("a123");
        Email email = Email.createEmail("amado@gmail.com");
        ResourceDTO resourceDTO = new ResourceDTO("a123",email.getEmail(), LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25),100, 30, "Developer");

        LocalDate startDate = LocalDate.of(1234, 12, 12);
        LocalDate endDate = LocalDate.of(1234, 12, 25);

        Resource resource3 = new Resource(projectCode, email,
                TimePeriod.createTimePeriod(startDate,endDate ),
                PercentageAllocation.createAllocation(100), CostPerHour.createCostPerHour(30), Role.createRole(Role.RoleEnum.Developer));
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource3);
        List<Resource> overLappingResources = new ArrayList<>();
        overLappingResources.add(resource3);

        when(createResourceService.getResourceByEmail(email.getEmail())).thenReturn(resourceList);
        when(createResourceDomainService.createResource(resourceDTO, resourceList)).thenReturn(Optional.empty());

        Optional<ResourceOutputDTO> expected = Optional.empty();

        //act

        Optional<ResourceOutputDTO> actual = createResourceService.createAndAdd(resourceDTO);

        //assert

        assertEquals(expected, actual);


    }


}