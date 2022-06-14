package switchisep.project.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.ApiIgnore;

@Component
@ApiIgnore
public class LoaderService implements CommandLineRunner {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final Logger LOG = LoggerFactory.getLogger(LoaderService.class);

    @Autowired
    private ProfilesBootstrap profilesBootstrap;
    @Autowired
    private ProjectsBootstrap projectsBootstrap;
    @Autowired
    private UsersBootstrap usersBootstrap;
    @Autowired
    private ResourcesBootstrap resourcesBootstrap;
    @Autowired
    private UserStoriesBootstrap userStoriesBootstrap;
    @Autowired
    private TypologiesBootstrap typologiesBootstrap;
    @Autowired
    private SprintsBootstrap sprintsBootstrap;
    @Value("${load.bootstrapData}")
    private boolean loadDefaultData;

    @Override
    public void run(String... args) throws Exception {
        LOG.info("\n\n" +
                "   ______       ___ __  ________  __    \n" +
                "  / ___/ |     / (_) /_/ ____/ / / /    \n" +
                "  \\__ \\| | /| / / / __/ /   / /_/ /     \n" +
                " ___/ /| |/ |/ / / /_/ /___/ __  /      \n" +
                "/____/_|__/|__/_/\\__/\\____/_/ /_/  __ __\n" +
                "  / ____/______  ______  ____     / // /\n" +
                " / / __/ ___/ / / / __ \\/ __ \\   / // /_\n" +
                "/ /_/ / /  / /_/ / /_/ / /_/ /  /__  __/\n" +
                "\\____/_/   \\__,_/ .___/\\____/     /_/   \n" +
                "               /_/                      \n" +
                ANSI_GREEN +
                "   :: Project Management System ::" +
                ANSI_RESET + " \n" +
                "   :: 2021/2022 (v1.0-SNAPSHOT) :: \n");

        ///////////
        /////////// DATA TO LOAD
        if (loadDefaultData) {

            profilesBootstrap.execute();
            usersBootstrap.execute();
            projectsBootstrap.execute();
            resourcesBootstrap.execute();
            userStoriesBootstrap.execute();
            sprintsBootstrap.execute();
            typologiesBootstrap.execute();

        }
        /////////// END DATA TO LOAD
        ///////////

        LOG.info("Application running");
    }

}
