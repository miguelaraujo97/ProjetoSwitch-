package switchisep.project.domain.valueobjects;

import java.util.Objects;


public class ProjectCustomer implements ValueObject<ProjectCustomer> {

    private String customerName;

    public ProjectCustomer() {
    }

    /**
     * @param customerName customer responsible for the project
     * @author João Reis /Ricardo Pereira
     * Private constructor that accepts a string as an argument.
     */
    private ProjectCustomer(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Static method to instantiate a projectCustomer VO
     *
     * @param customerName
     * @return an object projectCustomer
     */
    public static ProjectCustomer createProjectCustomer(String customerName) {

        Objects.requireNonNull(customerName, "A customer name must be inserted");
        if (customerName.trim().equals("")) {
            throw new IllegalArgumentException("The customer name can not be an empty string");
        }

        return new ProjectCustomer(customerName);

    }

    /**
     * Necessary for the dtoNative.
     * @return string customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectCustomer other = (ProjectCustomer) o;


        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return customerName.hashCode();
    }

    @Override
    public boolean sameValueAs(ProjectCustomer other) {
        return other != null && this.customerName.equals(other.customerName);
    }
}
