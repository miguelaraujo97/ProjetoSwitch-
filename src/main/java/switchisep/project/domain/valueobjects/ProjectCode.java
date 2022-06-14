package switchisep.project.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProjectCode implements ValueObject<ProjectCode> {

    private String code;

    public ProjectCode() {
    }

    /**
     * @author João Reis /Ricardo Pereira
     * Private constructor that accepts a String as an argument.
     * @param code Alphanumeric identification of a project.
     */
    private  ProjectCode(String code) {

        Objects.requireNonNull(code, "A code must be inserted");
        if (code.trim().equals("")) {
            throw new IllegalArgumentException("The code can not be an empty string");
        }

        if (!codeRegexPatternValidation(code)){

            throw new IllegalArgumentException("The codes must be alphanumerical");

        }

        this.code = code;
    }

    /**
     * Static method to instantiate a code VO
     *
     * @param code
     * @return a code
     */

    public static ProjectCode createProjectCode(String code){

        return new ProjectCode(code);
    }

    public String getCode() {
        return code;
    }

    /**
     * Method that uses regex to determine if code is valid.
     * This regex uses alphanumeric.
     *
     * @param code
     * @return true is format is valid.
     */
    private static boolean codeRegexPatternValidation(String code) {
        boolean isValid;

        String regex = "[a-zA-Z]{1,}[0-9]{1,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        isValid = matcher.matches();
        return isValid;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectCode other = (ProjectCode) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean sameValueAs(ProjectCode other) {
        return other != null && this.code.equals(other.code);
    }
}
