package switchisep.project.dto;


import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class ResourceOutputDTO extends RepresentationModel<ResourceOutputDTO>  {

    public String resourceId;
    public String email;
    public LocalDate startDate;
    public LocalDate endDate;
    public int percentageAllocation;
    public double costPerHour;
    public String role;

    public ResourceOutputDTO(String resourceId, String email,
                             LocalDate startDate, LocalDate endDate, int percentageAllocation,
                             double costPerHour, String role) {

        this.resourceId = resourceId;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentageAllocation = percentageAllocation;
        this.costPerHour = costPerHour;
        this.role = role;
    }
}
