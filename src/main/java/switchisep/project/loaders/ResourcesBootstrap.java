package switchisep.project.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.services.CreateResourceService;

import java.time.LocalDate;

@Service
@ApiIgnore
public class ResourcesBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(ResourcesBootstrap.class);

    @Autowired
    CreateResourceService createResourceService;


    public void execute() {

        LOG.info("Loading Resources ...");
        loadResources();
        LOG.info("Resources loaded");

    }


    /**
     * Load data to input into the database
     */
    public void loadResources() {

        createResourceService.createAndAdd(addResource("A0001", "tc@mymail.com",
                "2022-02-25", "2022-03-25",
                20, 35.0, "ProjectManager"));

        createResourceService.createAndAdd(addResource("A0001", "js@mymail.com",
                "2022-02-23", "2022-03-25",
                20, 25.0, "ProductOwner"));


    }

    /**
     * Create a resource
     *
     * @param projectCode          String
     * @param email                String
     * @param startDate            LocalDate
     * @param endDate              LocalDate
     * @param percentageAllocation Integer
     * @param costPerHour          Double
     * @param role                 String
     * @return
     */
    private ResourceDTO addResource(String projectCode, String email, String startDate, String endDate, Integer percentageAllocation, Double costPerHour, String role) {

        return new ResourceDTO(projectCode, email, LocalDate.parse(startDate), LocalDate.parse(endDate), percentageAllocation, costPerHour, role);
    }
}
