package switchisep.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApiConfig {

    public static final String TAG_PROJECT = "Project";
    public static final String TAG_PROJECT_MESSAGE = "Create, Read, Update project";
    public static final String TAG_USER = "User";
    public static final String TAG_USER_MESSAGE = "Create, Read, Update user";
    public static final String TAG_PROFILE = "Profile";
    public static final String TAG_PROFILE_MESSAGE = "Create profile";
    public static final String TAG_TYPOLOGY = "Typology";
    public static final String TAG_TYPOLOGY_MESSAGE = "Create Typology";
    public static final String TAG_SPRINT = "Sprint";
    public static final String TAG_SPRINT_MESSAGE = "Create sprint";
    public static final String TAG_USER_STORY = "User Story";
    public static final String TAG_USER_STORY_MESSAGE = "Create, Read, Update user story";
    public static final String TAG_RESOURCE = "Resource";
    public static final String TAG_RESOURCE_MESSAGE = "Create, Read, Update resource";
    public static final String TAG_REQUEST = "Request";
    public static final String TAG_REQUEST_MESSAGE = "Create request";
    public static final String TAG_ACTIVITIES = "Activities";
    public static final String TAG_ACTIVITIES_MESSAGE = "Read activities";

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("SWitCH")
                .select()
                .apis(RequestHandlerSelectors.basePackage("switchisep.project.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())

                ;
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("SWitCH G4 API")
                .description("Project Management System")
                .license("MIT")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("", "", "noemail@noemail.com.pt"))
                .build();
    }

}
