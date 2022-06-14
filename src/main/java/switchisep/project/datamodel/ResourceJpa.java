package switchisep.project.datamodel;

import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "Resources")
public class ResourceJpa implements Serializable {

    private String projectCode;

    private String email;

    @Id
    private String resourceID;

    private LocalDate startDate;

    private LocalDate endDate;

    private int percentageAllocation;

    private double costPerHour;

    private String role;

    public ResourceJpa(String projectCode, String email, String resourceID, LocalDate startDate,
                       LocalDate endDate, int percentageAllocation, double costPerHour, String role) {
        this.projectCode = projectCode;
        this.email = email;
        this.resourceID = resourceID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentageAllocation = percentageAllocation;
        this.costPerHour = costPerHour;
        this.role = role;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getEmail() {
        return email;
    }

    public String getResourceID() {
        return resourceID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getPercentageAllocation() {
        return percentageAllocation;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public String getRole() {
        return role;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceJpa that = (ResourceJpa) o;
        return percentageAllocation == that.percentageAllocation &&
                Double.compare(that.costPerHour, costPerHour) == 0 &&
                Objects.equals(projectCode, that.projectCode) &&
                Objects.equals(email, that.email) &&
                Objects.equals(resourceID, that.resourceID) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, email, resourceID, startDate,
                endDate, percentageAllocation, costPerHour, role);
    }
}
