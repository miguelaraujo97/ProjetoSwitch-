package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.ResourceOutputDTO;
import switchisep.project.dto.assemblers.ResourceDTOAssembler;
import switchisep.project.domain.resource.Resource;
import switchisep.project.repositories.interfaces.IResourceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViewResourcesServiceTest {

    @InjectMocks
    ViewResourcesService viewResourcesService;

    @Mock
    IResourceRepository resourceRepository;

    @Mock
    ResourceDTOAssembler resourceDTOAssembler;

    @Test
    void shouldReturnResourceById() {

        // Arrange

        String resourceId = "RS-01";

        Resource resourceMock = mock(Resource.class);

        Optional<Resource> resourceOptional = Optional.of(resourceMock);

        ResourceDTO resourceDtoMock = mock(ResourceDTO.class);

        Optional<ResourceDTO> resourceDTOOptional = Optional.of(resourceDtoMock);

        when(resourceRepository.findById(resourceId)).thenReturn(resourceOptional);

        when(resourceDTOAssembler.toDTO(resourceMock)).thenReturn(resourceDtoMock);

        // Act

        Optional<ResourceDTO> resourceDTOOptionalResult = viewResourcesService.getResourceById(resourceId);

        // Assert

        assertEquals(resourceDTOOptional, resourceDTOOptionalResult);
    }

    @Test
    void shouldNotReturnResourceById() {

        // Arrange

        String resourceId = "RS-01";

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.empty());

        // Act

        Optional<ResourceDTO> resourceDTOOptionalResult = viewResourcesService.getResourceById(resourceId);

        // Assert

        assertEquals(Optional.empty(), resourceDTOOptionalResult);
    }

    @Test
    void shouldReturnListOfResources() {

        // Arrange

        String projectCode = "A123";

        ResourceOutputDTO resourceOutputDTOMock = mock(ResourceOutputDTO.class);

        List<ResourceOutputDTO> resourceOutputDTOList = new ArrayList<>();

        resourceOutputDTOList.add(resourceOutputDTOMock);

        Resource resourceMock = mock(Resource.class);

        List<Resource> resourceList = new ArrayList<>();

        resourceList.add(resourceMock);

        when(resourceRepository.findAllByProjectCode(projectCode)).thenReturn(resourceList);

        when(resourceDTOAssembler.toOutputDTO(resourceMock)).thenReturn(resourceOutputDTOMock);

        // Act

        List<ResourceOutputDTO> resourceOutputDTOResult = viewResourcesService.getAllResourcesByProjectCode(projectCode);

        // Assert

        assertEquals(resourceOutputDTOList, resourceOutputDTOResult);
    }
}