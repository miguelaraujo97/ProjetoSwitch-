package switchisep.project.datamodel;

import switchisep.project.domain.valueobjects.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "projects")
public class ProjectJpa implements Serializable {
    @EmbeddedId
    private  ProjectCode code;

    @Embedded
    private  ProjectName name;

    @Embedded
    private ProjectDescription projectDescription;

    @Embedded
    private  ProjectBudget budget;

    @Embedded
    @AttributeOverride(name = "description", column= @Column(name = "typologyDescription"))
    private  Description typologyDescription;

    @Embedded
    private ProjectCustomer customer;

    @Embedded
    @AttributeOverride(name = "duration", column = @Column(name = "sprintDuration"))
    private SprintDuration sprintDuration;

    @Embedded
    private ProjectNumberOfPlannedSprints numberOfPlannedSprints;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Embedded
    private TimePeriod timePeriod;

    @Embedded
    private  ProjectBusinessSector businessSector;

    /**
     * No arguments' constructor.
     */
    public ProjectJpa (){

    }

    /**
     * Public constructor for the projectJpa with parameters.
     * @param code
     * @param name
     * @param customer
     * @param projectDescription
     * @param budget
     * @param sprintDuration
     * @param status
     * @param numberOfPlannedSprints
     * @param typologyDescription
     * @param timePeriod
     * @param businessSector
     */

    public ProjectJpa(ProjectCode code, ProjectName name, ProjectCustomer customer, ProjectDescription projectDescription,
                      ProjectBudget budget, SprintDuration sprintDuration, ProjectStatus status,
                      ProjectNumberOfPlannedSprints numberOfPlannedSprints, Description typologyDescription,
                      TimePeriod timePeriod, ProjectBusinessSector businessSector) {

        this.code = code;
        this.name = name;
        this.customer= customer;
        this.projectDescription = projectDescription;
        this.budget = budget;
        this.sprintDuration = sprintDuration;
        this.status = status;
        this.numberOfPlannedSprints = numberOfPlannedSprints;
        this.typologyDescription = typologyDescription;
        this.timePeriod = timePeriod;
        this.businessSector = businessSector;

    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return code.
     */
    public ProjectCode getCode() {
        return code;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return name.
     */
    public ProjectName getName() {
        return name;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return description.
     */
    public ProjectDescription getProjectDescription() {
        return projectDescription;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return budget.
     */
    public ProjectBudget getBudget() {
        return budget;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return typologyDescription.
     */
    public Description getTypologyDescription() {
        return typologyDescription;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return customer.
     */

    public ProjectCustomer getCustomer() {
        return customer;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return sprintDuration.
     */
    public SprintDuration getSprintDuration() {
        return sprintDuration;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return numberOfPlannedSprints.
     */
    public ProjectNumberOfPlannedSprints getNumberOfPlannedSprints() {
        return numberOfPlannedSprints;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return project status.
     */
    public ProjectStatus getStatus() {
        return status;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return timePeriod.
     */
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    /**
     * Getter necessary for the assembly from projectJpa to project (domain).
     * @return businessSector.
     */
    public ProjectBusinessSector getBusinessSector() {
        return businessSector;
    }


}
