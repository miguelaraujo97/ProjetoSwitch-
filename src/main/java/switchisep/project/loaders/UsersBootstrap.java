package switchisep.project.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;
import switchisep.project.dto.UserRegistrationRequest;
import switchisep.project.services.RegisterUserAppService;


@Service
@ApiIgnore
public class UsersBootstrap {

    private static final Logger LOG =
            LoggerFactory.getLogger(UsersBootstrap.class);
    @Autowired
    RegisterUserAppService registerUserAppService;

    public void execute() {

        LOG.info("Loading Users ...");
        loadUsers();
        LOG.info("Users loaded");

    }


    /**
     * Load data to input into the database
     */
    public void loadUsers() {

        String dummyPass = "qwerty1!";
        String functionAnything = "Anything";
        String functionFlexible = "Flexible";
        String photoUrl = "https://user.jpg";

        registerUserAppService.createAndSaveUser(addUser(
                "João Silva", "js@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Manel Costa", "ms@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Xico Ferreira", "xf@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Tiago Cancado", "tc@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Urbino das Urzes", "udu@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Ze da Esquina", "ze@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Nel Moleiro", "nel.m@mymail.com", functionFlexible,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Zé do Bento", "zb@mymail.com", functionFlexible,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Tó Farrulo", "to.f@mymail.com", functionFlexible,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Tino das Cruzes", "tdc@mymail.com",
                "Not so flexible", "qwerty1!", photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Quim Barreiros", "qb@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Tiago Geringonca", "tg@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Zé Manel", "zm@mymail.com", functionAnything,
                dummyPass, photoUrl));
        registerUserAppService.createAndSaveUser(addUser(
                "Antonio Silva", "as@mymail.com", functionAnything,
                dummyPass, photoUrl));

    }

    /**
     * Create User
     *
     * @return ProjectDto
     */
    private UserRegistrationRequest addUser(String userName, String email,
                                            String function, String password,
                                            String photo) {

        UserRegistrationRequest userDto = new UserRegistrationRequest();
        userDto.userName = userName;
        userDto.email = email;
        userDto.function = function;
        userDto.password = password;
        userDto.photo = photo;

        return userDto;
    }
}
