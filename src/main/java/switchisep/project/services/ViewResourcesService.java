package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.domain.resource.Resource;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.ResourceOutputDTO;
import switchisep.project.dto.assemblers.ResourceDTOAssembler;
import switchisep.project.repositories.interfaces.IResourceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewResourcesService {

    private final IResourceRepository resourceRepository;

    private final ResourceDTOAssembler resourceDTOAssembler;

    public ViewResourcesService(IResourceRepository resourceRepository, ResourceDTOAssembler resourceDTOAssembler) {
        this.resourceRepository = resourceRepository;
        this.resourceDTOAssembler = resourceDTOAssembler;
    }

    public Optional<ResourceDTO> getResourceById(String resourceId) {

        Optional<Resource> resourceOptional = resourceRepository.findById(resourceId);

        if (!resourceOptional.isPresent()) {

            return Optional.empty();
        }

        ResourceDTO resourceDTO = resourceDTOAssembler.toDTO(resourceOptional.get());

        return Optional.of(resourceDTO);
    }

    public List<ResourceOutputDTO> getAllResourcesByProjectCode(String projectCode) {

        List<ResourceOutputDTO> resourceOutputDTOSList = new ArrayList<>();

        List<Resource> resourceList = resourceRepository.findAllByProjectCode(projectCode);

        for (Resource resource : resourceList) {

            ResourceOutputDTO resourceOutputDTO = resourceDTOAssembler.toOutputDTO(resource);

            resourceOutputDTOSList.add(resourceOutputDTO);
        }

        return resourceOutputDTOSList;
    }
}
