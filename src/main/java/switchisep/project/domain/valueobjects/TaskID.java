package switchisep.project.domain.valueobjects;

import java.util.Objects;

public class TaskID{

    private String taskID;

    private TaskID(String taskID) {

        this.taskID = taskID;
    }

    public static TaskID createTaskID(String taskID) {

        if (taskID.isEmpty()) {

            throw new IllegalArgumentException("Task ID can't be empty");
        }

        return new TaskID(taskID);
    }

    public String getTaskID() {

        return this.taskID;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskID taskID1 = (TaskID) o;
        return Objects.equals(taskID, taskID1.taskID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskID);
    }
}