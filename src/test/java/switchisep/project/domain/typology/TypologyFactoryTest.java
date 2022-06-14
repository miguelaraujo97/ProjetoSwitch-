package switchisep.project.domain.typology;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypologyFactoryTest {

    @Test
    void createNewTypology_IntegrationTest() {

        //Arrange
        TypologyFactory typologyFactory = new TypologyFactory();

        Description description = Description.createDescription("TYPOLOGY A");

        TypologyID typologyID = TypologyID.createTypologyID();


        Typology typology = new Typology(typologyID,description);

        //Act
        Typology typologyCreated = typologyFactory.createTypology(typologyID, description);
        //Assert
        assertEquals(typology, typologyCreated);
    }
}
