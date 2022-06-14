package switchisep.project.datamodel;



import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "typologies")
public class TypologyJpa implements Serializable {

    @EmbeddedId
    private TypologyID typologyID;

    @Embedded
    private Description typologyDescription;


    public TypologyJpa(TypologyID typologyID, Description typologyDescription) {
        this.typologyID=typologyID;
        this.typologyDescription=typologyDescription;
    }

    public TypologyJpa(){}

    public TypologyID getTypologyID() {
        return typologyID;
    }

    public Description getTypologyDescription() {
        return typologyDescription;
    }


}
