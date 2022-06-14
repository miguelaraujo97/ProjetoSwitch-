package switchisep.project.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import switchisep.project.dto.TypologyCreationDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateTypologyIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createProjectIntegrationTest_success() throws Exception{

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description="TYPOLOGY A";

   mockMvc.perform(post("/typologies")
                        .content(objectMapper.writeValueAsString(typologyCreationDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    void createProjectIntegrationTest_typologyAlreadyExists() throws Exception{

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description="TYPOLOGY B";
        TypologyCreationDTO typologyCreationDTO1 = new TypologyCreationDTO();
        typologyCreationDTO1.description="TYPOLOGY B";

        mockMvc.perform(post("/typologies")
                        .content(objectMapper.writeValueAsString(typologyCreationDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isCreated()).andReturn();

        String result = (mockMvc.perform(post("/typologies")
                        .content(objectMapper.writeValueAsString(typologyCreationDTO1))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isConflict()).andReturn()).getResponse().getContentAsString();

        assertEquals("Typology Already Exists", result);
    }

    @Test
    void createProjectIntegrationTest_badEntryData() throws Exception{

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description="";

        String result = (mockMvc.perform(post("/typologies")
                        .content(objectMapper.writeValueAsString(typologyCreationDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn()).getResponse().getContentAsString();

//TODO isUnprocessableEntity()

        assertEquals("Empty or null data", result);
    }
}
