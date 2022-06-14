package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.ResourceOutputDTO;
import switchisep.project.dto.assemblers.ResourceDTOAssembler;
import switchisep.project.domain.domainservices.CreateResourceDomainService;
import switchisep.project.domain.resource.Resource;
import switchisep.project.repositories.ResourceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CreateResourceService {

    ResourceRepository resourceRepository;

    CreateResourceDomainService createResourceDomainService;

    ResourceDTOAssembler resourceDTOAssembler;

    public CreateResourceService(ResourceRepository resourceRepository,
                                 CreateResourceDomainService createResourceDomainService,
                                 ResourceDTOAssembler resourceDTOAssembler) {

        this.resourceRepository = resourceRepository;
        this.createResourceDomainService = createResourceDomainService;
        this.resourceDTOAssembler = resourceDTOAssembler;
    }

    public List<Resource> getResourceByEmail(String email) {
        return resourceRepository.findByEmail(email);
    }

    public Optional<ResourceOutputDTO> createAndAdd(ResourceDTO resourceDTO) {

        Optional<Resource> resourceOptional = createResourceDomainService.createResource(resourceDTO,
                getResourceByEmail(resourceDTO.email));

        if (resourceOptional.isPresent()) {

            Resource resource = resourceOptional.get();

            resourceRepository.save(resource);

            ResourceOutputDTO resourceOutputDTO = resourceDTOAssembler.toOutputDTO(resource);

            return Optional.of(resourceOutputDTO);

        } else {
            return Optional.empty();
        }
    }
}

