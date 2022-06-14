package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class UserUiDTO extends RepresentationModel<UserUiDTO> {

    public final int userId;
    public final String name;
    public final String email;
    public final String function;

    public UserUiDTO(int userId, String name, String email, String function) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUiDTO userUiDTO = (UserUiDTO) o;
        return Objects.equals(name, userUiDTO.name) &&
                Objects.equals(email, userUiDTO.email) &&
                Objects.equals(function, userUiDTO.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, function);
    }
}
