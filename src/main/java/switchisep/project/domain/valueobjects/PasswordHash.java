package switchisep.project.domain.valueobjects;

import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PasswordHash implements ValueObject<PasswordHash> {
    private String hashedPassword;

    private PasswordHash(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public PasswordHash() {
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public static PasswordHash createHashPassword(String writtenPassword) {
        if (Objects.isNull(writtenPassword) || writtenPassword.isEmpty() ||
        writtenPassword.isBlank()) {
            throw new IllegalArgumentException("Invalid password.");
        }
        return new PasswordHash(BCrypt.hashpw(writtenPassword, BCrypt.gensalt(12)));
    }

    public boolean checkSecurePasswordHash(String writtenPassword) {
        boolean result;
        if (null == writtenPassword) {
            throw new IllegalArgumentException("Invalid written / hashed provided for comparison.");
        } else {
            result = BCrypt.checkpw(writtenPassword, this.hashedPassword);
        }
        return result;
    }

    @Override
    public boolean sameValueAs(PasswordHash other) {
        return other != null && this.getHashedPassword().
                equals(other.getHashedPassword());
    }

}
