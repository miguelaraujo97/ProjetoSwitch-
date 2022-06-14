package switchisep.project.dto.assemblers;

import org.springframework.stereotype.Component;
import switchisep.project.dto.TypologyDTO;
import switchisep.project.domain.typology.Typology;

@Component
public class TypologyDomainDTOAssembler {

    public TypologyDomainDTOAssembler() {
        //empty
    }

    public TypologyDTO toDTO(Typology typology) {

        TypologyDTO typologyDTO = new TypologyDTO();

        typologyDTO.typologyId = typology.getTypologyID().getTypologyID();
        typologyDTO.typologyDescription = typology.getTypologyDescription().getDescription();

        return typologyDTO;
    }
}
