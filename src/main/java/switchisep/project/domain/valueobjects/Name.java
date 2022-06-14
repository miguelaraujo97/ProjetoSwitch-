package switchisep.project.domain.valueobjects;

import lombok.*;
import org.springframework.stereotype.Component;
import switchisep.project.error_handling.BusinessRulesException;
import switchisep.project.error_handling.EmptyObjectException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@NoArgsConstructor
public class Name implements ValueObject<Name> {

    @Getter
    private String inputName;

    private Name(String name) {
        inputName = name;
    }


    /**
     * @param name to create a Value Object Name. This name can only contain
     *             letters.
     * @return Value Object Name
     * @author Celso Castro 1211755
     * <p>
     * Method to instantiate a new Name
     */
    public static Name createName(String name) throws BusinessRulesException, EmptyObjectException {

        Objects.requireNonNull(name, "Must enter a name");
        if (name.isBlank()) {
            throw new EmptyObjectException("Name Cannot Be Null or Blank");
        }
        if (!codeRegexValidation(name)) {
            throw new BusinessRulesException("Name must only contain " +
                    "letters");
        }
        return new Name(name);
    }

    private static boolean codeRegexValidation(String name) {
        boolean isValid;

        String regex = "^[ A-Za-z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        isValid = matcher.matches();
        return isValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return inputName.equals(name.inputName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputName);
    }

    @Override
    public boolean sameValueAs(Name other) {
        return other != null && this.inputName.equals(other.inputName);
    }
}
