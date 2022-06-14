package switchisep.project.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;
import switchisep.project.dto.ProjectDto;
import switchisep.project.services.CreateProjectService;

import java.time.LocalDate;

@Service
@ApiIgnore
public class ProjectsBootstrap {

    private static final Logger LOG =
            LoggerFactory.getLogger(ProjectsBootstrap.class);
    @Autowired
    CreateProjectService createProjectService;

    public void execute() {

        LOG.info("Loading Projects ...");
        loadProjects();
        LOG.info("Projects loaded");

    }

    /**
     * Load data to input into the database
     */
    public void loadProjects() {

        String planned = "planned";
        String itDoesntMatter = "It doesn't matter";
        String fixedCost = "Fixed cost";
        createProjectService.createProjectAndSave(addProject("A0001",
                "Dummy 01", "XPTO, SA", "Just a dummy project",
                fixedCost, 150000.0, 2, 6,
                planned, "2022-02-25", "2026-05-22", itDoesntMatter));

        createProjectService.createProjectAndSave(addProject("A0002",
                "Dummy 02", "XPTO, SA", "Just a dummy project",
                fixedCost, 550000.0, 2, 6,
                planned, "2022-02-25", "2026-03-22", itDoesntMatter));

        String projDescription = "Just another dummy project";
        createProjectService.createProjectAndSave(addProject("A0003",
                "Dummy 03", "XYZ, Lda", projDescription,
                fixedCost, 35000.0, 4, 12,
                planned, "2022-02-25", "2026-03-22", itDoesntMatter));

        createProjectService.createProjectAndSave(addProject("A0004",
                "Dummy 04", "XYZw, Lda", projDescription,
                fixedCost, 50000.0, 4, 12,
                planned, "2022-02-25", "2026-03-22", itDoesntMatter));

        createProjectService.createProjectAndSave(addProject("A0005",
                "Dummy 05", "XPTO, Lda", projDescription,
                fixedCost, 350000.0, 4, 12,
                planned, "2022-02-25", "2026-03-22", itDoesntMatter));

        createProjectService.createProjectAndSave(addProject("A0666",
                "Inevitable nightmare", "Hell, LLC",
                "Doomed from the start",
                "Time and materials", 500000.0,
                3, 15,
                planned, "2022-02-25", "2026-03-22",
                "Hospitality industry"));

    }

    /**
     * Create ProjectDto
     *
     * @param code                   String
     * @param name                   String
     * @param customer               String
     * @param projectDescription     String
     * @param typologyDescription    String
     * @param budget                 String
     * @param sprintDuration         String
     * @param numberOfPlannedSprints String
     * @param status                 String
     * @param startDate              String
     * @param endDate                String
     * @param businessSector         String
     * @return ProjectDto
     */
    private ProjectDto addProject(String code, String name, String customer,
                                  String projectDescription,
                                  String typologyDescription, Double budget,
                                  Integer sprintDuration,
                                  Integer numberOfPlannedSprints,
                                  String status, String startDate,
                                  String endDate, String businessSector) {

        ProjectDto projectDto = new ProjectDto();
        projectDto.code = code;
        projectDto.name = name;
        projectDto.customer = customer;
        projectDto.projectDescription = projectDescription;
        projectDto.typologyDescription = typologyDescription;
        projectDto.budget = budget;
        projectDto.sprintDuration = sprintDuration;
        projectDto.numberOfPlannedSprints = numberOfPlannedSprints;
        projectDto.status = status;
        projectDto.startDate = LocalDate.parse(startDate);
        projectDto.endDate = LocalDate.parse(endDate);
        projectDto.businessSector = businessSector;

        return projectDto;
    }


}
