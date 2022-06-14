package switchisep.project.domain.typology;


import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.Entity;
import switchisep.project.domain.valueobjects.TypologyID;


import java.util.Objects;


public class Typology implements Entity<Typology> {

    private final TypologyID typologyID;
    private final Description typologyDescription;

    public Typology(TypologyID typologyID, Description description){
        this.typologyDescription = description;
        this.typologyID = typologyID;
    }

    public boolean checkTypologyDescription(Description typologyDescription){
        return Objects.equals(this.typologyDescription, typologyDescription);
    }

    public Description getTypologyDescription(){
        return this.typologyDescription;
    }

    public TypologyID getTypologyID(){
        return this.typologyID;
    }

    @Override
    public boolean sameIdentityAs(Typology other) {
        return other != null && typologyDescription.sameValueAs(other.typologyDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(typologyDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology other = (Typology) o;
        return sameIdentityAs(other);
    }
}
