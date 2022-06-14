package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Objects;

public class ResourceDTO extends RepresentationModel<ResourceDTO> {

    public String projectCode;
    public String email;
    public LocalDate startDate;
    public LocalDate endDate;
    public int percentageAllocation;
    public double costPerHour;
    public String role;

    public ResourceDTO(String projectCode, String email, LocalDate startDate, LocalDate endDate, int percentageAllocation,
                       double costPerHour, String role) {
        this.projectCode = projectCode;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentageAllocation = percentageAllocation;
        this.costPerHour = costPerHour;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceDTO that = (ResourceDTO) o;
        return Objects.equals(email, that.email) &&
                percentageAllocation == that.percentageAllocation &&
                costPerHour == that.costPerHour &&
                Objects.equals(projectCode, that.projectCode) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, email, startDate, endDate, percentageAllocation, costPerHour, role);
    }
}
