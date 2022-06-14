package switchisep.project.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.assemblers.ResourceDTOAssembler;
import switchisep.project.controllers.ResourceController;
import switchisep.project.repositories.ResourceRepository;
import switchisep.project.services.CreateResourceService;
import switchisep.project.services.EditRoleService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ResourceIT {

    @Autowired
    ResourceController resourceController;

    @Autowired
    CreateResourceService createResourceService;

    @Autowired
    EditRoleService editRoleService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourceDTOAssembler resourceDTOAssembler;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addResourceToProject() throws Exception {

        //Arrange
        ResourceDTO resourceDTO = new ResourceDTO("a123", "souza@me.com",
                LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25), 10,
                30, "Developer");

        //Act and assert
        mockMvc.perform(post("/projects/{projectCode}/resources", "a123").content(objectMapper.writeValueAsString(resourceDTO))
                        .accept("application/json").contentType("application/json"))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void addResourceToProjectFail() throws Exception {

        //Arrange
        ResourceDTO resourceDTO = new ResourceDTO("a123", "amado@me.com",
                LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25), 100, 30, "Developer");

        //Act and assert

        mockMvc.perform(post("/projects/{projectCode}/resources", "a123")
                        .content(objectMapper.writeValueAsString(resourceDTO))
                        .accept("application/json").contentType("application/json"))
                .andExpect(status().isCreated()).andReturn();

        mockMvc.perform(post("/projects/{projectCode}/resources", "a123").content(objectMapper.writeValueAsString(resourceDTO))
                        .accept("application/json").contentType("application/json"))
                .andExpect(status().isUnprocessableEntity()).andReturn();
    }

    @Test
    void changeSpecialRoleToResourceSuccessfully() throws Exception {

        //Arrange
        ResourceDTO resourceDTOtoChange = new ResourceDTO("a123", "amado@me.com",
                LocalDate.of(1234, 12, 23),
                LocalDate.of(1234, 12, 25), 10,
                30, "ScrumMaster");

        ResourceDTO resourceDTO2 = new ResourceDTO("a123", "dessa@me.com",
                LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25), 10,
                30, "ScrumMaster");

        ResourceDTO resourceDTO3 = new ResourceDTO("a123", "amado@me.com",
                LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25), 10,
                30, "Developer");

        createResourceService.createAndAdd(resourceDTO2);
        createResourceService.createAndAdd(resourceDTO3);

        //Act and assert
        mockMvc.perform(patch("/projects/{projectCode}/resources", "a123")
                        .content(objectMapper.writeValueAsString(resourceDTOtoChange))
                        .accept("application/json")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        resourceRepository.deleteAll();
    }

    @Test
    void changeSpecialRoleToResourceUnsuccessfully_invalidStartDate() throws Exception {

        //Arrange
        ResourceDTO resourceDTOtoChange = new ResourceDTO("a123", "amado@me.com",
                LocalDate.of(1234, 12, 10),
                LocalDate.of(1234, 12, 25), 10,
                30, "ScrumMaster");

        ResourceDTO resourceDTO2 = new ResourceDTO("a123", "dessa@me.com",
                LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25), 10,
                30, "ScrumMaster");

        ResourceDTO resourceDTO3 = new ResourceDTO("a123", "amado@me.com",
                LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25), 10,
                30, "Developer");

        createResourceService.createAndAdd(resourceDTO2);
        createResourceService.createAndAdd(resourceDTO3);

        //Act and assert
        mockMvc.perform(patch("/projects/{projectCode}/resources", "a123")
                        .content(objectMapper.writeValueAsString(resourceDTOtoChange))
                        .accept("application/json")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();

        resourceRepository.deleteAll();
    }

}

