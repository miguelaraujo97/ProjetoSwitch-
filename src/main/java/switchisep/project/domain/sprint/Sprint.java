package switchisep.project.domain.sprint;

import switchisep.project.domain.valueobjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sprint implements Entity<Sprint> {


    private final SprintID sprintID;
    private final ProjectCode projectCode; //ProjectID that references which project this sprint belongs to
    private final SprintStatus sprintStatus; //Can be planned, started or finished
    private final List<SprintBacklogItem> sprintBacklogItems; //Contains reference to user story id
    private SprintOrder sprintOrder; //(has a chronological reference to the Sprint
    private final TimePeriod plannedTimePeriod;
    private TimePeriod definitiveTimePeriod; //Definitive time period is set when sprint is finished.

    public Sprint(SprintID sprintID, ProjectCode projectCode,
                  TimePeriod plannedTimePeriod, SprintStatus sprintStatus) {

        this.sprintID = sprintID;
        this.projectCode = projectCode;
        this.plannedTimePeriod = plannedTimePeriod;
        this.sprintStatus = sprintStatus;
        this.sprintBacklogItems = new ArrayList<>();
        this.sprintOrder = SprintOrder.createSprintOrder(1);
    }

    public SprintID getSprintID() {
        return sprintID;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public TimePeriod getPlannedTimePeriod() {
        return plannedTimePeriod;
    }

    public SprintOrder getSprintOrder() {
        return sprintOrder;
    }

    public void swapSprintOrder(SprintOrder newSprintOrder) {
        this.sprintOrder = newSprintOrder;
    }

    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }

    @Override
    public boolean sameIdentityAs(Sprint other) {
        return other != null && sprintID.sameValueAs(other.sprintID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprint other = (Sprint) o;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }

}

   /* public SprintStatus startTheSprint() {
        if (sprintIsStarted() || !sprintIsNotFinished()) {
            throw new IllegalArgumentException("Sprint was already Started or has already Finish");
        }
        LocalDate nowDate = LocalDate.now();
        this.startDate = nowDate;
        this.endDate = nowDate.plusWeeks(this.sprintDuration);
        ;
        this.sprintStatus = SprintStatus.STARTED;
        return this.sprintStatus;
    }*/

/**
 * US22 (JoaoSa)
 * method to finalize sprints. it checks whether the Sprint was
 * already initialized and not finalized. in that case, it sets the status to
 * Finished and sets the endDate to the LocalDate.now().
 *
 * @return sprintStatus Sprint.SprintStatus (ENUM)
 * @param userStoryID
 * @param taskName
 * @param taskDescription
 * @param taskType
 * @param startDate
 * @param endDate
 * @param effortEstimate
 * @return true if sprint is in the started state
 * @author Miguel Araújo
 * Method that receives the necessary parameters for a Task object creation and
 * finds the correct user story (by user story id) to create the task object.
 * Validates that the sprint is started.
 * <p>
 * Method that calls the constructor of TaskExtended objects to create a new
 * object and add it to the sprint task list associated with a sprint.
 * @param taskName
 * @param taskDescription
 * @param taskType
 * @param startDate
 * @param endDate
 * @param effortEstimate
 * <p>
 * Method that checks if the sprint status is not finished.
 * @return boolean, true if Sprint is not finished (is notStarted o
 * started).
 * <p>
 * Method that checks if the sprint status is equal to started.
 * @return boolean, true if Sprint is started (active).
 * <p>
 * Method that adds created task to task list.
 * @param Task input value
 * <p>
 * Method that returns a copy of sprintTaskList (list of tasks of a sprint)
 * <p>
 * Method that return a  sprint task by task id.
 * @param taskId
 * @return A task.
 * <p>
 * Method to register the work done in a user story task.
 * @param userStoryID
 * @param taskID
 * @param hoursSpent
 * @return true with register was done, false otherwise.
 * <p>
 * Method to register the work done in a sprint task.
 * @param taskID
 * @param hoursSpent
 * @return true with register was done, false otherwise.
 * <p>
 * Method to register the work done and decide in each task.
 * @param userStoryID
 * @param taskID
 * @param hoursSpent
 * @return true with register was done, false otherwise.
 */
  /*  public SprintStatus finishTheSprint() {
        if (!(sprintIsNotFinished() && sprintIsStarted())) {
            throw new IllegalArgumentException("Sprint has Never Started or has already Finish");
        }
        this.endDate = LocalDate.now();
        this.sprintStatus = SprintStatus.FINISHED;
        return this.sprintStatus;
    }*/

/**
 * @param userStoryID
 * @param taskName
 * @param taskDescription
 * @param taskType
 * @param startDate
 * @param endDate
 * @param effortEstimate
 * @return true if sprint is in the started state
 * @author Miguel Araújo
 * Method that receives the necessary parameters for a Task object creation and
 * finds the correct user story (by user story id) to create the task object.
 * Validates that the sprint is started.
 */
   /* public boolean createNewTask(int userStoryID, String taskName, Description taskDescription,
                                 String taskType, LocalDate startDate, LocalDate endDate,
                                 Integer effortEstimate) {
        boolean isCreated = false;

        if (sprintStatus.equals(SprintStatus.STARTED)) {

            sprintBacklog.getUserStoryById(userStoryID).createNewTask(taskName, taskDescription, taskType,
                    startDate, endDate, effortEstimate);

            isCreated = true;
        }
        return isCreated;
    }*/

/**
 * Method that calls the constructor of TaskExtended objects to create a new
 * object and add it to the sprint task list associated with a sprint.
 *
 * @param taskName
 * @param taskDescription
 * @param taskType
 * @param startDate
 * @param endDate
 * @param effortEstimate
 */

   /* public boolean createNewTask(String taskName,
                                 Description taskDescription,
                                 String taskType,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 Integer effortEstimate) {

        int taskID = sprintTaskList.size() + 1;

        SprintID sprintID = SprintID.createSprintID("S1");
        //Temporary call
        //TODO IMPLEMENT ACTUAL SPRINT ID VO

        boolean isCreated = false;

        Task task = TaskFactory.createNewTask(sprintID, taskID, taskName, taskDescription,
                taskType, startDate, endDate, effortEstimate);


        if (sprintIsNotFinished()) {
            addCreatedTaskToSprintTaskList(task);

            isCreated = true;

        } else
            isCreated = false;

        return isCreated;
    }*/

/**
 * Method that checks if the sprint status is not finished.
 *
 * @return boolean, true if Sprint is not finished (is notStarted o
 * started).
 */
  /*  private boolean sprintIsNotFinished() {
        return !this.sprintStatus.equals(SprintStatus.FINISHED);
    }*/

/**
 * Method that checks if the sprint status is equal to started.
 *
 * @return boolean, true if Sprint is started (active).
 */
   /* private boolean sprintIsStarted() {
        return this.sprintStatus.equals(SprintStatus.STARTED);
    }*/

/**
 * Method that adds created task to task list.
 *
 * @param Task input value
 */
  /*  public void addCreatedTaskToSprintTaskList(Task Task) {
        sprintTaskList.add(Task);
    }*/

/**
 * Method that returns a copy of sprintTaskList (list of tasks of a sprint)
 */
  /*  public List<Task> getSprintTaskList() {

        return Collections.unmodifiableList(sprintTaskList);
    }*/

/**
 * Method that return a  sprint task by task id.
 *
 * @param taskId
 * @return A task.
 */
  /*  public Task getSprintTaskById(int taskId) {

        for (Task t : sprintTaskList)
            if (t.getTaskID() == taskId)

                return t;
        throw new IllegalArgumentException("No sprint task found with this " +
                "taskId.");
    }*/

/**
 * Method to register the work done in a user story task.
 *
 * @param userStoryID
 * @param taskID
 * @param hoursSpent
 * @return true with register was done, false otherwise.
 */
  /*  private boolean registerWorkDoneInUsTask(int userStoryID, int taskID,
                                             int hoursSpent) {

        getSprintBacklog().getUserStoryById(userStoryID).getTaskById(taskID).addHoursSpentInTask(hoursSpent);

        return getSprintBacklog().getUserStoryById(userStoryID).getTaskById(taskID).changePercentageOfExecution();

    }*/

/**
 * Method to register the work done in a sprint task.
 *
 * @param taskID
 * @param hoursSpent
 * @return true with register was done, false otherwise.
 */

  /*  private boolean registerWorkDoneInSprintTask(int taskID, int hoursSpent) {

        getSprintTaskById(taskID).addHoursSpentInTask(hoursSpent);

        return getSprintTaskById(taskID).changePercentageOfExecution();
    }*/

/**
 * Method to register the work done and decide in each task.
 *
 * @param userStoryID
 * @param taskID
 * @param hoursSpent
 * @return true with register was done, false otherwise.
 */


   /* public boolean registerWorkDoneInTask(int userStoryID, int taskID, int hoursSpent) {

    public boolean registerWorkDoneInTask(int userStoryID, int taskID,
                                          int hoursSpent) {

        boolean registerDone;

        if (userStoryID == 0) {

            registerDone = registerWorkDoneInSprintTask(taskID,
                    hoursSpent);

        } else
            registerDone = registerWorkDoneInUsTask(userStoryID,
                    taskID, hoursSpent);

        return registerDone;

    }*/






