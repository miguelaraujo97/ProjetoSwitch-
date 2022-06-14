package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class TypologyID implements ValueObject<TypologyID> {

    private final String typologyID;
    private static final String REF = "TYP";

    private TypologyID() {
        this.typologyID = REF + "-" + UUID.randomUUID();
    }

    private TypologyID(String id) {

        this.typologyID = id;
    }

    public static TypologyID createTypologyID() {
        return new TypologyID();
    }

    public static TypologyID createTypologyIdWithString(String typologyID) {
        return new TypologyID(typologyID);
    }

    public String getTypologyID() {
        return typologyID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typologyID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypologyID that = (TypologyID) o;
        return typologyID.equals(that.typologyID);
    }

    @Override
    public boolean sameValueAs(TypologyID other) {
        return other != null && Objects.equals(this.typologyID,
                other.typologyID);
    }


}
