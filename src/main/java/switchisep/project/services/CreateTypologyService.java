package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.TypologyCreationDTO;
import switchisep.project.dto.TypologyDTO;
import switchisep.project.dto.assemblers.TypologyDomainDTOAssembler;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.typology.TypologyFactoryInterface;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;
import switchisep.project.repositories.interfaces.ITypologyRepository;

import java.util.Optional;

@Service
public class CreateTypologyService {

    @Autowired
    ITypologyRepository typologyRepository;

    @Autowired
    TypologyFactoryInterface typologyFactoryInterface;

    @Autowired
    TypologyDomainDTOAssembler typologyDomainDTOAssembler;

    public CreateTypologyService() {
    //Empty constructor
    }

    public Optional<TypologyDTO> createTypologyAndSave(TypologyCreationDTO typologyCreationDTO) {

        String description = typologyCreationDTO.getDescription();

        Description typologyDescription =
                Description.createDescription(description);

        if (typologyRepository.existsByTypologyDescription(typologyDescription)) {

            return Optional.empty();
        }

        TypologyID typologyID = TypologyID.createTypologyID();

        Typology typologyObjectCreated =
                typologyFactoryInterface.createTypology(typologyID,
                        typologyDescription);

        Optional<Typology> typologyObjectSaved =
                typologyRepository.save(typologyObjectCreated);

        if(!typologyObjectSaved.isPresent()){
            return Optional.empty();
        }

        TypologyDTO typologyDTOObjectSaved =
                typologyDomainDTOAssembler.toDTO(typologyObjectSaved.get());

        return Optional.of(typologyDTOObjectSaved);
    }
}
