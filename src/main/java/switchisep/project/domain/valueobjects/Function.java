package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class Function implements ValueObject<Function>, Serializable {

    /**
     * Value object Function.
     */
    private String functionValue;

    private Function(String function){
        this.functionValue = function;
    }

    public Function() {
    }

    /**
     * method to create a function.
     *
     * @param function value to create the value object
     * @return this value object.
     */
    public static Function createFunction(String function) {
        if (isStringEmptyBlankOrNull(function)) {
            throw new IllegalArgumentException("Function cannot be null or "
                    + "empty");
        }
        return new Function(function);
    }

    public String getFunction() {
        return functionValue;
    }

    /**
     * @param function function
     * @return true if string is not null and is not empty.
     * @author Miguel Araújo
     */
    private static boolean isStringEmptyBlankOrNull(String function) {
        return function == null || function.isEmpty() || function.isBlank();
    }

    @Override
    public boolean sameValueAs(Function other) {
        return other != null && this.functionValue.equals(
                other.functionValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Function)) return false;
        Function other = (Function) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(functionValue);
    }
}
