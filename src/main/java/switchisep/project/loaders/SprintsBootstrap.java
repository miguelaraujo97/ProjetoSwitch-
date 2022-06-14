package switchisep.project.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;
import switchisep.project.dto.SprintCreationInfo;
import switchisep.project.services.CreateSprintService;

import java.time.LocalDate;

@Service
@ApiIgnore
public class SprintsBootstrap {
    private static final Logger LOG = LoggerFactory.getLogger(SprintsBootstrap.class);

    @Autowired
    CreateSprintService createSprintService;


    public void execute() {

        LOG.info("Loading Sprints ...");
        loadSprints();
        LOG.info("Sprints loaded");

    }


    /**
     * Load data to input into the database
     */
    public void loadSprints() {

        createSprintService.createAndSaveSprint(addSprint("2026-03-22"), "A0001");
        createSprintService.createAndSaveSprint(addSprint("2026-04-10"), "A0001");


    }

    /**
     * Create ProfileDTO
     *
     * @return ProfileDTO
     */
    private SprintCreationInfo addSprint(String startDate) {

        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        sprintCreationInfo.plannedStartDate = LocalDate.parse(startDate);

        return sprintCreationInfo;
    }
}
