package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Component
public class Email implements ValueObject<Email>, Serializable {

    private String email;

    public Email(String email) {
        this.email = email;
    }

    public Email(){}

    public static Email createEmail(String email) {
        if (email == null || email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!emailRegexPatternValidation(email)) {
            throw new IllegalArgumentException("Email does not meet email " +
                    "format");
        } else
            return new Email(email);
    }

    /**
     * Method that uses regex to determine if e-mail is valid.
     * This regex uses all the characters permitted by RFC 5322.
     *
     * @param email
     * @return true is format is valid.
     */
    public static boolean emailRegexPatternValidation(String email) {
        boolean isValid;
        // Java email validation permitted by RFC 5322
        String regex = "^(?=.{1,64}@)[A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2," +
                "})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        isValid = matcher.matches();
        return isValid;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean sameValueAs(Email other) {
        return other != null && this.email.equals(other.email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email other = (Email) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}