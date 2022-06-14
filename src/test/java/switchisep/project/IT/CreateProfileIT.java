package switchisep.project.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.controllers.ProfilesController;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.repositories.ProfileRepository;
import switchisep.project.services.CreateProfileService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CreateProfileIT {

    @Autowired
    ProfilesController profilesController;

    @Autowired
    CreateProfileService createProfileService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProfileRepository profileRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNewProfileSuccessfully() throws Exception {

        //Arrange
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.name = "Director";

        //Act & Assert
        mockMvc.perform(post("/profiles").content(objectMapper.writeValueAsString(profileDTO))
                        .accept("application/json").contentType("application/json"))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void createNewProfileThatAlreadyExists() throws Exception {

        //Arrange
        ProfileDTO profileDTO1 = new ProfileDTO();
        String name = "Administrator";
        profileDTO1.name = name;
        Profile prf = new Profile(Name.createName(name));
        profileRepository.save(prf);


        MvcResult result = mockMvc.perform(post("/profiles")
                        .content(objectMapper.writeValueAsString(profileDTO1))
                        .accept("application/json")
                        .contentType("application/json"))
                .andExpect(status().isConflict()).andReturn();

        String mvcResult = result.getResponse().getContentAsString();
        String expected = "{\"message\":\"Profile already exists.\"}";


        //assert
        Assertions.assertNotNull(mvcResult);
        assertEquals(expected, mvcResult);
    }

    @Test
    void createNewProfile_BusinessRulesException() throws Exception {

        //Arrange
        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.name = "Admi1nistrator";


        MvcResult result = mockMvc.perform(post("/profiles")
                        .content(objectMapper.writeValueAsString(profileDTO1))
                        .accept("application/json")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn();

        String mvcResult = result.getResponse().getContentAsString();
        String expected = "{\"message\":\"Name must only contain letters\"}";


        //assert
        Assertions.assertNotNull(mvcResult);
        assertEquals(expected, mvcResult);
    }
    @Test
    void createNewProfile_EmptyObjectException() throws Exception {

        //Arrange
        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.name = "";


        MvcResult result = mockMvc.perform(post("/profiles")
                        .content(objectMapper.writeValueAsString(profileDTO1))
                        .accept("application/json")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn();

        String mvcResult = result.getResponse().getContentAsString();
        String expected = "{\"message\":\"Name Cannot Be Null or Blank\"}";


        //assert
        Assertions.assertNotNull(mvcResult);
        assertEquals(expected, mvcResult);
    }

}
