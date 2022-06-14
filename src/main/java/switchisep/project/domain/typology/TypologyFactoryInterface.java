package switchisep.project.domain.typology;

import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;

public interface TypologyFactoryInterface {

    Typology createTypology(TypologyID typologyID, Description description)
            throws IllegalArgumentException;
}
