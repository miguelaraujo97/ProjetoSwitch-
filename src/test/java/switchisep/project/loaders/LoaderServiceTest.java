package switchisep.project.loaders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchisep.project.repositories.*;
import switchisep.project.services.CreateSprintService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LoaderServiceTest {

    @Autowired
    LoaderService loaderService;
    @Autowired
    SprintsBootstrap sprintsBootstrap;
    @Autowired
    CreateSprintService createSprintService;
    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    ProjectsBootstrap projectsBootstrap;
    @Autowired
    ProfilesBootstrap profilesBootstrap;
    @Autowired
    ResourcesBootstrap resourcesBootstrap;
    @Autowired
    TypologiesBootstrap typologiesBootstrap;
    @Autowired
    UsersBootstrap usersBootstrap;
    @Autowired
    UserStoriesBootstrap userStoriesBootstrap;

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    TypologyRepository typologyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserStoryRepository userStoryRepository;

    @Test
    void testLoaders(){

        typologiesBootstrap.execute();
        projectsBootstrap.execute();
        profilesBootstrap.execute();
        usersBootstrap.execute();
        resourcesBootstrap.execute();
        userStoriesBootstrap.execute();
        sprintsBootstrap.execute();

        projectRepository.deleteAll();
        profileRepository.deleteAll();
        resourceRepository.deleteAll();
        typologyRepository.deleteAll();
        userRepository.deleteAll();
        userStoryRepository.deleteAll();
        sprintRepository.deleteAll();

        assertEquals(profileRepository.getAllProfiles().size(), 0);


    }

    @Test
    void testLoaderService(){


        projectRepository.deleteAll();
        profileRepository.deleteAll();
        resourceRepository.deleteAll();
        typologyRepository.deleteAll();
        userRepository.deleteAll();
        userStoryRepository.deleteAll();
        sprintRepository.deleteAll();

        assertEquals(profileRepository.getAllProfiles().size(), 0);

    }


}