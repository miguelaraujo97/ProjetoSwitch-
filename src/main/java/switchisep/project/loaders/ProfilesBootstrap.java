package switchisep.project.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.services.CreateProfileService;

@Service
@ApiIgnore
public class ProfilesBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(ProfilesBootstrap.class);

    @Autowired
    CreateProfileService createProfileService;

    public void execute() {

        LOG.info("Loading Profiles ...");
        loadProfiles();
        LOG.info("Profiles loaded");

    }

    /**
     * Load data to input into the database
     */
    public void loadProfiles() {

        createProfileService.createAndSaveProfile(addProfile("Administrator"));
        createProfileService.createAndSaveProfile(addProfile("Visitor"));
        createProfileService.createAndSaveProfile(addProfile("Director"));
        createProfileService.createAndSaveProfile(addProfile("Project Manager"));
        createProfileService.createAndSaveProfile(addProfile("Product Owner"));
        createProfileService.createAndSaveProfile(addProfile("Scrum Master"));
        createProfileService.createAndSaveProfile(addProfile("User"));

    }

    /**
     * Create ProfileDTO
     *
     * @param name String
     * @return ProfileDTO
     */
    private ProfileDTO addProfile(String name) {

        ProfileDTO profileDto = new ProfileDTO();
        profileDto.name = name;

        return profileDto;
    }
}
