package switchisep.project.domain.domainservices;

import org.springframework.stereotype.Service;
import switchisep.project.dto.SprintCreationInfo;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.sprint.SprintFactoryInterface;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.error_handling.BusinessRulesException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CreateSprintDomainService {

    public Sprint createSprint(SprintCreationInfo sprintCreationInfo, Project associatedProject,
                                         List<Sprint> sprintList, SprintFactoryInterface sprintFactoryInterface) {

        SprintID sprintID = generateNextSprintID(sprintList, associatedProject.getProjectCode());

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        LocalDate plannedEndDate = sprintCreationInfo.plannedStartDate
                .plusWeeks(associatedProject.getProjectSprintDuration().getSprintDuration());

        TimePeriod sprintPlannedTimePeriod = TimePeriod.createTimePeriod(sprintCreationInfo.plannedStartDate,
                plannedEndDate);

        if (sprintDatesOverlapWithOtherSprints(sprintCreationInfo.plannedStartDate, plannedEndDate,
                sprintList)) {
            throw new BusinessRulesException("Sprint dates can't overlap");
        }

        Optional<Sprint> opNewSprint = associatedProject.createNewSprint(sprintID,
                sprintPlannedTimePeriod, sprintStatus, sprintFactoryInterface);

        if(!opNewSprint.isPresent()){
            throw new BusinessRulesException("Sprint dates must be between Project Dates");
        }


        return sortSprintOrdersUponSprintCreation(opNewSprint.get(), sprintList);

    }

    public SprintID generateNextSprintID(List<Sprint> sprintList, ProjectCode projectCode) {
        int numberOfSprintsPlusOne = sprintList.size() + 1;

        if (numberOfSprintsPlusOne < 10) {
            return SprintID.createSprintID("S00" + numberOfSprintsPlusOne + "P" + projectCode.getCode());
        } else if (numberOfSprintsPlusOne < 100) {
            return SprintID.createSprintID("S0" + numberOfSprintsPlusOne  + "P" + projectCode.getCode());
        } else
            return SprintID.createSprintID("S" + numberOfSprintsPlusOne  + "P" + projectCode.getCode());
    }

    public boolean sprintDatesOverlapWithOtherSprints(LocalDate newSprintStartDate, LocalDate newSprintEndDate,
                                                      List<Sprint> sprintList) {

        boolean overlaps = false;

        for (Sprint eachSprint : sprintList) {

            boolean overlapConditionOne = newSprintStartDate.isAfter(eachSprint.getPlannedTimePeriod().getStartDate())
                    && newSprintStartDate.isBefore(eachSprint.getPlannedTimePeriod().getEndDate());

            boolean overlapConditionTwo = newSprintEndDate.isAfter(eachSprint.getPlannedTimePeriod().getStartDate())
                    && newSprintEndDate.isBefore(eachSprint.getPlannedTimePeriod().getEndDate());

            boolean overlapConditionThree = newSprintStartDate.isBefore(eachSprint.getPlannedTimePeriod().getStartDate())
                    && newSprintEndDate.isAfter(eachSprint.getPlannedTimePeriod().getEndDate());

            boolean overlapConditionFour = newSprintStartDate.isEqual(eachSprint.getPlannedTimePeriod().getStartDate());

            boolean overlapConditionFive = newSprintStartDate.isEqual(eachSprint.getPlannedTimePeriod().getEndDate());

            if (overlapConditionOne || overlapConditionTwo || overlapConditionThree
                    || overlapConditionFour || overlapConditionFive) {

                overlaps = true;
            }
        }
        return overlaps;
    }

    public Sprint sortSprintOrdersUponSprintCreation(Sprint sprint, List<Sprint> sprintList) {

        sprintList.add(sprint);

        List<LocalDate> listOfSprintStartDates = listSprintListStartDates(sprintList);

        Collections.sort(listOfSprintStartDates);

        int counter = 1;

        for (LocalDate eachStartDate : listOfSprintStartDates) {
            for (Sprint eachSprint : sprintList) {

                if (eachSprint.getPlannedTimePeriod().getStartDate().equals(eachStartDate)) {

                    SprintOrder newSprintOrder = SprintOrder.createSprintOrder(counter);
                    eachSprint.swapSprintOrder(newSprintOrder);
                    counter++;
                }
            }
        }
        return sprint;
    }

    public List<LocalDate> listSprintListStartDates(List<Sprint> sprintList) {

        List<LocalDate> listOfStartDates = new ArrayList<>();

        for (Sprint sprint : sprintList) {
            listOfStartDates.add(sprint.getPlannedTimePeriod().getStartDate());
        }

        return listOfStartDates;
    }
}
