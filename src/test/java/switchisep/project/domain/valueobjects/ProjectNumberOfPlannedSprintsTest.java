package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNumberOfPlannedSprintsTest {

    @Test
    void invalidNumberOfPlannedSprints() {
        //Arrange
        int numberOfPlannedSprintsData = -1;

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData));

    }


    //Tests for equals and hashCode methods

    @Test
    void testEqualsForSameObject(){

        int numberOfPlannedSprintsData = 10;

        //Act
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData);

        // Assert
        assertEquals(projectNumberOfPlannedSprints, projectNumberOfPlannedSprints);
    }

    @Test
    void testEqualsForEqualObject(){

        //Arrange
        int numberOfPlannedSprintsData = 10;

        //Act
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints1 = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData);
        // Assert
        assertEquals(projectNumberOfPlannedSprints, projectNumberOfPlannedSprints1);
    }

    @Test
    void testEqualsForDifferentObject_DifferentClass(){

        //Arrange
        int numberOfPlannedSprintsData = 0 ;
        Object obj = new Object();
        //Act
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData);

        //Act & Assert
        assertNotEquals(projectNumberOfPlannedSprints,obj);
        assertNotEquals(projectNumberOfPlannedSprints,null);
    }

    @Test
    void testEqualsForDifferentObject_SameClass(){

        //Arrange
        int numberOfPlannedSprintsData = 10;
        int numberOfPlannedSprintsData1 = 1;

        //Act
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints1 = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData1);
        //Assert
        assertNotEquals(projectNumberOfPlannedSprints, projectNumberOfPlannedSprints1);
    }



    @Test
    void hashCodeForDifferentObjects_SameClass() {
        //Arrange
        int numberOfPlannedSprintsData = 10;
        int numberOfPlannedSprintsData1 = 0;

        //Act
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints1 = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(numberOfPlannedSprintsData1);

        //Assert
        assertNotEquals(projectNumberOfPlannedSprints.hashCode(), projectNumberOfPlannedSprints1.hashCode());
    }
}