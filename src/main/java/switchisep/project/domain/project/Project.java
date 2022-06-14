package switchisep.project.domain.project;

import org.springframework.hateoas.RepresentationModel;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.sprint.SprintFactoryInterface;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class Project extends RepresentationModel<Project> implements Entity<Project> {


    private final ProjectCode code;
    private final ProjectName name;
    private ProjectDescription projectDescription;
    private final ProjectBudget projectBudget;
    private final Description typologyDescription;
    private final ProjectCustomer customer;
    private SprintDuration projectSprintDuration;
    private ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints;
    private ProjectStatus status;
    private TimePeriod timePeriod;
    private final ProjectBusinessSector projectBusinessSector;



    /**
     * @author João Reis /Ricardo Pereira
     * US005
     * Constructor for project objects.
     * This implementation of project uses a Builder pattern for the object
     * creation.
     * The code, name, customer are mandatory attributes.
     * ProductBacklog and SprintStore are empty upon creation
     **/

    private Project(Project.Builder builder) {
        this.code = builder.code;
        this.name = builder.name;
        this.projectDescription = builder.projectDescription;
        this.projectNumberOfPlannedSprints =
                builder.projectNumberOfPlannedSprints;
        this.status = builder.status;
        this.projectBudget = builder.projectBudget;
        this.typologyDescription = builder.typologyDescription;
        this.projectSprintDuration = builder.projectSprintDuration;
        this.customer = builder.customer;
        this.timePeriod = builder.timePeriod;
        this.projectBusinessSector = builder.projectBusinessSector;

    }

    /**
     * @param code Unique alphanumerical identifier for each project.
     * @return True if the codes are equal, false otherwise.
     * @author João Reis /Ricardo Pereira
     * Method to check if input code is equal to Project object code.
     */
    public boolean checkProjectExist(ProjectCode code) {
        return Objects.equals(this.code, code);
    }

    /**
     * Setter to a newTimePeriod
     *
     * @param newTimePeriod TimePeriod
     */

    public boolean setTimePeriod(TimePeriod newTimePeriod) {
        boolean updated = false;
        boolean timePeriodIsSame = newTimePeriod.sameValueAs(timePeriod);
        if (!timePeriodIsSame) {

            this.timePeriod = newTimePeriod;
            updated = true;
        }
        return updated;
    }

    /**
     * Getter method for the project's ID. Necessary for Dto creation.
     *
     * @return The project's ID.
     */
    public ProjectCode getProjectCode() {
        return code;
    }

    /**
     * Getter method for the project's name. Necessary for Dto creation.
     *
     * @return The project's name.
     */
    public ProjectName getName() {
        return name;
    }

    /**
     * Getter method for the project's description. Necessary for Dto creation.
     *
     * @return The project's description.
     */
    public ProjectDescription getProjectDescription() {
        return projectDescription;
    }

    /**
     * Getter method for the project's budget. Necessary for Dto creation.
     *
     * @return The project's budget.
     */
    public ProjectBudget getProjectBudget() {
        return projectBudget;
    }

    /**
     * Getter method for the project's typology. Necessary for Dto creation.
     *
     * @return The project's typology.
     */
    public Description getTypologyDescription() {
        return typologyDescription;
    }

    /**
     * Getter method for the project's customer. Necessary for Dto creation.
     *
     * @return The project's customer.
     */
    public ProjectCustomer getCustomer() {
        return customer;
    }

    /**
     * Getter method for the project's default sprint duration. Necessary for Dto creation.
     *
     * @return The project's sprint duration.
     */
    public SprintDuration getProjectSprintDuration() {
        return projectSprintDuration;
    }

    /**
     * Setter to a newNumberOfPlannedSprints
     *
     * @param newNumberOfPlannedSprints ProjectNumberOfPlannedSprints
     */
    public boolean setProjectNumberOfPlannedSprints(ProjectNumberOfPlannedSprints newNumberOfPlannedSprints) {
        boolean updated = false;
        boolean numberOfPlannedSprintsSame = newNumberOfPlannedSprints.sameValueAs(projectNumberOfPlannedSprints);
        if (!numberOfPlannedSprintsSame) {

            this.projectNumberOfPlannedSprints = newNumberOfPlannedSprints;
            updated = true;
        }
        return updated;
    }




    /**
     * Setter to a new project Status
     *
     * @param newStatus ProjectStatus
     */
    public boolean setProjectStatus(ProjectStatus newStatus) {
        boolean updated = false;
        boolean statusIsSame = newStatus.sameValueAs(status);

        if (!statusIsSame) {

            this.status = newStatus;
            updated = true;
        }
        return updated;
    }

    /**
     * Setter to a new project description
     *
     * @param newDescription ProjectDescription
     */
    public boolean setProjectDescription(ProjectDescription newDescription) {
        boolean updated = false;
        boolean descriptionIsSame = newDescription.sameValueAs(projectDescription);

        if (!descriptionIsSame) {

            this.projectDescription = newDescription;
            updated = true;
        }
        return updated;
    }

    /**
     * Setter to a newSprintDuration
     *
     * @param newSprintDuration SprintDuration
     */
    public boolean setProjectSprintDuration(SprintDuration newSprintDuration) {
        boolean updated = false;
        boolean sprintDurationIsSame = newSprintDuration.sameValueAs(projectSprintDuration);
        if (!sprintDurationIsSame) {

            this.projectSprintDuration = newSprintDuration;
            updated = true;
        }
        return updated;
    }



    /**
     * Getter method for the project's default number of planned sprints. Necessary for Dto creation.
     *
     * @return The project's number of planned sprints.
     */
    public ProjectNumberOfPlannedSprints getProjectNumberOfPlannedSprints() {
        return projectNumberOfPlannedSprints;
    }

    /**
     * Getter method for the project's status. Necessary for Dto creation.
     *
     * @return The project's status.
     */
    public ProjectStatus getStatus() {
        return status;
    }


    /**
     * Getter method for the project's period. Necessary for Dto creation.
     *
     * @return The project's time period.
     */
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    /**
     * Getter method for the project's business sector. Necessary for Dto creation.
     *
     * @return The project's business sector.
     */
    public ProjectBusinessSector getProjectBusinessSector() {
        return projectBusinessSector;
    }


    /**
     * Method that is responsible for creating new Sprints. Calls validation method to check if
     * the Sprint dates (TimePeriod) are valid (contained in Project dates).
     *
     * @param sprintID
     * @param sprintTimePeriod
     * @param sprintStatus
     * @return Optional object with a Sprint object if created, empty if not.
     */
    public Optional<Sprint> createNewSprint(SprintID sprintID, TimePeriod sprintTimePeriod,
                                            SprintStatus sprintStatus, SprintFactoryInterface sprintFactoryInterface) {

        if (validateSprintDatesWithProjectDates(sprintTimePeriod)) {
            Sprint newSprint = sprintFactoryInterface.createSprint(sprintID, this.code,
                    sprintTimePeriod, sprintStatus);

            return Optional.of(newSprint);
        }
        return Optional.empty();
    }

    /**
     * Method to validate if sprint start and end dates are inside project start and
     * end dates. Ignores validation if project dates are not defined.
     *
     * @param sprintTimePeriod
     * @return true if valid. False if not.
     */
    public boolean validateSprintDatesWithProjectDates(TimePeriod sprintTimePeriod) {

        return this.timePeriod == null || (!sprintTimePeriod.getStartDate().isBefore(this.timePeriod.getStartDate())
                && !sprintTimePeriod.getEndDate().isAfter(this.timePeriod.getEndDate()));
    }

    @Override
    public boolean sameIdentityAs(Project other) {
        return other != null && code.sameValueAs(other.code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project other = (Project) o;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public static class Builder {

        //Required parameters

        //Mandatory parameters
        private final ProjectCode code;
        private final ProjectName name;
        private final ProjectCustomer customer;


        //Optional parameters
        private ProjectDescription projectDescription = ProjectDescription.createProjectDescription("Insert project description.");
        private ProjectBudget projectBudget = ProjectBudget.createBudget(0);
        private Description typologyDescription = Description.createDescription("Insert typology.");
        private SprintDuration projectSprintDuration =
                SprintDuration.createSprintDuration(1);
        private ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints =
                ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(0);
        private ProjectStatus status = ProjectStatus.PLANNED;
        private TimePeriod timePeriod = TimePeriod.createTimePeriod(LocalDate.now(),LocalDate.now().plusYears(2));
        private ProjectBusinessSector projectBusinessSector = ProjectBusinessSector.createProjectBusinessSector("Insert business sector");

        public Builder(final ProjectCode code, final ProjectName name,
                       final ProjectCustomer customer) {

            Objects.requireNonNull(code, "A code must be inserted");
            Objects.requireNonNull(name, "A name must be inserted");
            Objects.requireNonNull(customer, "A customer must be inserted");

            this.code = code;
            this.name = name;
            this.customer = customer;

        }

        public Project.Builder projectDescription(ProjectDescription projectDescription) {
            this.projectDescription = projectDescription;
            return this;

        }

        public Project.Builder projectNumberOfPlannedSprints(ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints) {
            this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
            return this;
        }

        public Project.Builder projectStatus(ProjectStatus status) {
            this.status = status;
            return this;
        }

        public Project.Builder projectTimePeriod(TimePeriod timePeriod) {
            this.timePeriod = timePeriod;
            return this;
        }

        public Project.Builder projectBudget(ProjectBudget projectBudget) {
            this.projectBudget = projectBudget;
            return this;
        }

        public Project.Builder projectTypologyDescription(Description typologyDescription) {
            this.typologyDescription = typologyDescription;
            return this;
        }

        public Project.Builder projectSprintDuration(SprintDuration projectSprintDuration) {
            this.projectSprintDuration = projectSprintDuration;
            return this;
        }

        public Project.Builder projectBusinessSector(ProjectBusinessSector projectBusinessSector) {
            this.projectBusinessSector = projectBusinessSector;
            return this;
        }


        public Project build() {
            return new Project(this);

        }

    }
}
