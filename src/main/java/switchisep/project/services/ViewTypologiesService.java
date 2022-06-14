package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.TypologyDTO;
import switchisep.project.dto.assemblers.TypologyDomainDTOAssembler;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.valueobjects.TypologyID;
import switchisep.project.repositories.interfaces.ITypologyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewTypologiesService {

    private final ITypologyRepository typologyRepository;

    private final TypologyDomainDTOAssembler typologyDomainDTOAssembler;

    public ViewTypologiesService(ITypologyRepository typologyRepository, TypologyDomainDTOAssembler typologyDomainDTOAssembler) {
        this.typologyRepository = typologyRepository;
        this.typologyDomainDTOAssembler = typologyDomainDTOAssembler;
    }

    /**
     * Method that gets all the available Typologies
     *
     * @return A list of all Typologies converted to TypologyDTO.g
     */

    public List<TypologyDTO> getAllTypologies() {

        List<TypologyDTO> typologyDTOList = new ArrayList<>();

        List<Typology> typologyList = typologyRepository.findAllTypologies();

        for ( Typology typology : typologyList) {

            TypologyDTO typologyDTO = typologyDomainDTOAssembler.toDTO(typology);

            typologyDTOList.add(typologyDTO);
        }

        return typologyDTOList;

    }

    public Optional<TypologyDTO> getTypologyById(String typologyId) {

        TypologyID typologyID = TypologyID.createTypologyIdWithString(typologyId);

        Optional<Typology> optionalTypology = typologyRepository.findTypologyById(typologyID);

        if (!optionalTypology.isPresent()) {

            return Optional.empty();
        }

        TypologyDTO typologyDTO = typologyDomainDTOAssembler.toDTO(optionalTypology.get());

        return Optional.of(typologyDTO);
    }
}
