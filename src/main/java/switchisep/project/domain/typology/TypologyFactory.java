package switchisep.project.domain.typology;

import org.springframework.stereotype.Component;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;
import switchisep.project.error_handling.BusinessRulesException;

@Component
public class TypologyFactory implements TypologyFactoryInterface {

    public Typology createTypology(TypologyID typologyID, Description description)
            throws BusinessRulesException {
        return new Typology(typologyID,description);
    }


}
