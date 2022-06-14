package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectBudgetTest {

    @Test
    void shouldReturnException_negativeBudget(){
        // Arrange
        double budget = -5;

        // act & assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () ->
                        ProjectBudget.createBudget(budget)
                );
        assertEquals("ProjectBudget has to be greater or equal zero.",
                exception.getMessage());

    }

    @Test
    void shouldReturnTrue_positiveBudget(){
        // Arrange
        double budget = 0;

        // Act
        ProjectBudget projectBudget = ProjectBudget.createBudget(budget);
        ProjectBudget projectBudget1 = ProjectBudget.createBudget(0);

        // Assert
        assertEquals(projectBudget1, projectBudget);

    }


    @Test
    void checkEqualsHasCodeOverride(){
        //Arrange
        double budgetData = 10000;
        double budgetData1 = 100;

        //Act
        ProjectBudget projectBudget = ProjectBudget.createBudget(budgetData);
        ProjectBudget projectBudget1 = ProjectBudget.createBudget(budgetData);
        ProjectBudget projectBudget2 = ProjectBudget.createBudget(budgetData1);
        Object obj = new Object();

        // Assert
        assertEquals(projectBudget, projectBudget);
        assertEquals(projectBudget, projectBudget1);

        assertNotEquals(projectBudget,projectBudget2);
        assertNotEquals(projectBudget, null);
        assertNotEquals(projectBudget,obj);


        assertEquals(projectBudget.hashCode(),projectBudget1.hashCode());
        assertNotEquals(projectBudget.hashCode(),projectBudget2.hashCode());
    }
}