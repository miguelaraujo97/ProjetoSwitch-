package switchisep.project.dto;

import switchisep.project.domain.valueobjects.Description;

import java.time.LocalDate;

public class TaskDTO {

    public int taskID;
    public String taskName;
    public Description taskDescription;
    public LocalDate startDate;
    public LocalDate endDate;
    public String taskType; //Meeting, Documentation, Design, Implementation, Testing, Deployment, etc.
    public String taskStatus; // Planned / Running / Finished / Blocked
    public double hoursSpent;
    public Integer effortEstimate;
    public double percentageOfExecution;

}
