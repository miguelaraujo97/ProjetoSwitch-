package switchisep.project.loaders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchisep.project.repositories.SprintRepository;
import switchisep.project.services.CreateSprintService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@SpringBootTest
class SprintsBootstrapTest {

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

//    @Test
//    void testLoaders(){
//
//
//        projectsBootstrap.execute();
//        profilesBootstrap.execute();
//        resourcesBootstrap.execute();
//        typologiesBootstrap.execute();
//        usersBootstrap.execute();
//        userStoriesBootstrap.execute();
//        sprintsBootstrap.execute();
//
//
//
//    }



}