package switchisep.project.datamodel;

import switchisep.project.domain.valueobjects.*;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "sprints")
public class SprintJpa implements Serializable {


    @EmbeddedId
    private SprintID sprintID;
    @Embedded
    private ProjectCode projectCode; //ProjectID that references which project this sprint belongs to
    @Embedded
    private SprintStatus sprintStatus; //Can be planned, started or finished
    @ElementCollection
    @CollectionTable(name = "sprint_backlog_items")
    private List<SprintBacklogItem> sprintBacklogItems; //Contains reference to user story id
    @Embedded
    private SprintOrder sprintOrder; //(has a chronological reference to the Sprint
    @Embedded
    private TimePeriod plannedTimePeriod;

    public SprintJpa(SprintID sprintID, ProjectCode projectCode,
                     TimePeriod plannedTimePeriod,
                     SprintStatus sprintStatus, SprintOrder sprintOrder) {

        this.sprintID = sprintID;
        this.projectCode = projectCode;
        this.plannedTimePeriod = plannedTimePeriod;
        this.sprintStatus = sprintStatus;
        this.sprintOrder = sprintOrder;
        //this.sprintBacklogItems = new ArrayList<>();
    }


    public SprintJpa() {
    }

    public SprintID getSprintID() {
        return sprintID;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }

//    public List<SprintBacklogItem> getSprintBacklogItems() {
//        return sprintBacklogItems;
//    }

    public SprintOrder getSprintOrder() {
        return sprintOrder;
    }

    public TimePeriod getPlannedTimePeriod() {
        return plannedTimePeriod;
    }
}


