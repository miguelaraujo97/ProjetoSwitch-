package switchisep.project.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;
import switchisep.project.dto.TypologyCreationDTO;
import switchisep.project.services.CreateTypologyService;

@Service
@ApiIgnore
public class TypologiesBootstrap {
    private static final Logger LOG = LoggerFactory.getLogger(TypologiesBootstrap.class);

    @Autowired
    CreateTypologyService createTypologyService;


    public void execute() {

        LOG.info("Loading Profiles ...");
        loadTypologies();
        LOG.info("Profiles loaded");

    }


    /**
     * Load data to input into the database
     */
    public void loadTypologies() {

        createTypologyService.createTypologyAndSave(addTypology("Fixed Cost"));
        createTypologyService.createTypologyAndSave(addTypology("Time And Materials"));


    }

    /**
     * @param description
     * @return
     */
    private TypologyCreationDTO addTypology(String description) {

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description = description;

        return typologyCreationDTO;
    }
}
