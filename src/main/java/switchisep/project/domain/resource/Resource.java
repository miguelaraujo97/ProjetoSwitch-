package switchisep.project.domain.resource;

import switchisep.project.domain.valueobjects.*;
import java.util.Objects;
import java.util.UUID;

public class Resource {

    private final ProjectCode projectCode;
    private final Email email;
    private final ResourceID resourceID;
    private final TimePeriod timePeriod;
    private final PercentageAllocation percentageAllocation;
    private final CostPerHour costPerHour;
    private final Role role;

    public Resource(ProjectCode projectCode, Email email, TimePeriod timePeriod,
                    PercentageAllocation percentageAllocation, CostPerHour costPerHour, Role role) {

        this.projectCode = projectCode;
        this.email = email;
        this.resourceID = ResourceID.createResourceID(UUID.randomUUID().toString());
        this.timePeriod = timePeriod;
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.role = role;
    }

    public Resource(ProjectCode projectCode, Email email, ResourceID resourceID, TimePeriod timePeriod,
                    PercentageAllocation percentageAllocation, CostPerHour costPerHour, Role role) {

        this.projectCode = projectCode;
        this.email = email;
        this.resourceID = resourceID;
        this.timePeriod = timePeriod;
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.role = role;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public Email getEmail() {return email;}

    public ResourceID getResourceID() {return resourceID;}

    public TimePeriod getTimePeriod() {
        return timePeriod;}

    public PercentageAllocation getPercentageAllocation() {
        return percentageAllocation;
    }

    public CostPerHour getCostPerHour() {
        return costPerHour;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource that = (Resource) o;
        return Objects.equals(projectCode, that.projectCode) &&
                Objects.equals(email, that.email) &&
                Objects.equals(timePeriod, that.timePeriod) &&
                Objects.equals(percentageAllocation, that.percentageAllocation) &&
                Objects.equals(costPerHour, that.costPerHour) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceID, projectCode, email, timePeriod, percentageAllocation, costPerHour, role);
    }
}

