package switchisep.project.datamodel.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.datamodel.TypologyJpa;
import switchisep.project.domain.typology.Typology;

@Service
public class TypologyDomainDataAssembler {

    public TypologyJpa toData(Typology typology ) {

        return new TypologyJpa( typology.getTypologyID(), typology.getTypologyDescription());
    }

    public Typology toDomain( TypologyJpa typologyJPA ) {

        return new Typology(typologyJPA.getTypologyID(),typologyJPA.getTypologyDescription());
    }

}
