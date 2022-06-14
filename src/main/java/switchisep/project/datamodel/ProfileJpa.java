package switchisep.project.datamodel;

import lombok.NoArgsConstructor;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileJpa implements Serializable {

    @EmbeddedId
    private ProfileID profileID;

    @Embedded
    private Name name;

    public ProfileJpa (ProfileID profileID, Name name){

        this.profileID = profileID;
        this.name = name;
    }

    public Name getName(){
        return this.name;
    }

    public ProfileID getId(){
        return this.profileID;
    }
}
