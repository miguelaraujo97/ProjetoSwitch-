package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCustomerTest {

    @Test
    void projectCustomerNameNull() {

        //Arrange
        String customerNameData = null;


        //Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            ProjectCustomer.createProjectCustomer(customerNameData);
        });

    }

    @Test
    void emptyStringCustomerName() {

        //Arrange

        String customerNameData = "";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ProjectCustomer.createProjectCustomer(customerNameData);
        });

    }

    @Test
    void emptyStringWithSpaceCustomerName() {
        //Arrange
        String customerNameData = "   ";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ProjectCustomer.createProjectCustomer(customerNameData);
        });

    }

    @Test
    void equalsCustomerName() {
        //Arrange
        String customerNameData = "A123";

        //Act
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer(customerNameData);
        ProjectCustomer projectCustomer1 = ProjectCustomer.createProjectCustomer(customerNameData);
        //Assert
        assertEquals(projectCustomer, projectCustomer1);
    }

    @Test
    void notEqualsCustomerName() {
        //Arrange
        String customerNameData = "A123";
        String customerNameData1 = "A122";

        //Act
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer(customerNameData);
        ProjectCustomer projectCustomer1 = ProjectCustomer.createProjectCustomer(customerNameData1);
        //Assert
        assertNotEquals(projectCustomer, projectCustomer1);
    }


    //----------------------------------------------------------------------------------------------------------------
    //Tests for equals and hashCode methods

    @Test
    void testEqualsForSameObject(){

        //Arrange
        String customerNameData = "A123";
        //Act
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer(customerNameData);
        //Assert
        assertEquals(projectCustomer,projectCustomer);
    }

    @Test
    void testEqualsForEqualObject(){
        //Arrange
        String customerNameData = "A123";
        String customerNameData1 = "A123";
        //Act
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer(customerNameData);
        ProjectCustomer projectCustomer1 = ProjectCustomer.createProjectCustomer(customerNameData1);
        //Assert
        assertEquals(projectCustomer,projectCustomer1);
    }


    @Test
    void testEqualsForDifferentObject_DifferentClass(){
        //Arrange
        String customerNameData = "A123";
        //Act
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer(customerNameData);
        Object obj = new Object();
        //Act & Assert
        assertNotEquals(projectCustomer,obj);
        assertNotEquals(projectCustomer,null);
    }

    @Test
    void testEqualsForDifferentObject_SameClass(){
        //Arrange
        String customerNameData = "A123";
        //Act
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer(customerNameData);
        ProjectCustomer projectCustomer1 = ProjectCustomer.createProjectCustomer(customerNameData);
        //Assert
        assertEquals(projectCustomer,projectCustomer1);
    }



    @Test
    void hashCodeForDifferentObjects_SameClass() {
        //Arrange
        String customerNameData = "A123";
        String customerNameData1 = "A122";
        //Act
        ProjectCustomer customerName = ProjectCustomer.createProjectCustomer(customerNameData);
        ProjectCustomer customerName1 = ProjectCustomer.createProjectCustomer(customerNameData1);
        //Assert
        assertNotEquals(customerName.hashCode(), customerName1.hashCode());
    }

}