package switchisep.project.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;
import switchisep.project.dto.NewUserStoryInfo;
import switchisep.project.services.CreateUserStoryService;

@Service
@ApiIgnore
public class UserStoriesBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(UserStoriesBootstrap.class);

    @Autowired
    CreateUserStoryService createUserStoryService;


    public void execute() {

        LOG.info("Loading User Stories ...");
        loadUserStories();
        LOG.info("User Stories loaded");

    }


    /**
     * Load data to input into the database
     */
    public void loadUserStories() {

        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 01"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 02"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 03"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 04"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 05"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 06"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 07"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 08"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 09"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 10"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 11"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 12"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 13"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 14"));
        createUserStoryService.createAndSaveUserStory(addUserStory("A0001", "Dummy 15"));


    }

    /**
     * @param projectCode String
     * @param description String
     * @return
     */
    private NewUserStoryInfo addUserStory(String projectCode, String description) {

        NewUserStoryInfo newUserStoryInfo = new NewUserStoryInfo();
        newUserStoryInfo.description = description;
        newUserStoryInfo.projectCode = projectCode;

        return newUserStoryInfo;
    }

}
