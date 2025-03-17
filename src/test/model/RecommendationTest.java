package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Recommendation.
 */
public class RecommendationTest {
    
    private Recommendation highPriorityRec;
    private Recommendation mediumPriorityRec;
    private Recommendation lowPriorityRec;
    
    @BeforeEach
    void setUp() {
        // Initialize recommendations with different priorities
        highPriorityRec = new Recommendation(
            "Biomechanical",            // category
            1,                          // priority (high)
            "Improve landing technique", // description
            "Knee Valgus"               // targetRiskFactor
        );
        
        mediumPriorityRec = new Recommendation(
            "Training",                  // category
            2,                           // priority (medium)
            "Strengthen hamstrings",     // description
            "Hamstring Weakness"         // targetRiskFactor
        );
        
        lowPriorityRec = new Recommendation(
            "Equipment",                 // category
            3,                           // priority (low)
            "Use supportive knee braces", // description
            "Joint Laxity"               // targetRiskFactor
        );
    }
    
    @Test
    void testConstructor() {
        // Test that constructor properly initializes fields
        assertEquals("Biomechanical", highPriorityRec.getCategory());
        assertEquals(1, highPriorityRec.getPriority());
        assertEquals("Improve landing technique", highPriorityRec.getDescription());
        assertEquals("Knee Valgus", highPriorityRec.getTargetRiskFactor());
        
        assertEquals("Training", mediumPriorityRec.getCategory());
        assertEquals(2, mediumPriorityRec.getPriority());
        assertEquals("Strengthen hamstrings", mediumPriorityRec.getDescription());
        assertEquals("Hamstring Weakness", mediumPriorityRec.getTargetRiskFactor());
        
        assertEquals("Equipment", lowPriorityRec.getCategory());
        assertEquals(3, lowPriorityRec.getPriority());
        assertEquals("Use supportive knee braces", lowPriorityRec.getDescription());
        assertEquals("Joint Laxity", lowPriorityRec.getTargetRiskFactor());
    }
    
    @Test
    void testGetPriorityText() {
        // Test that priority text is correctly returned
        assertEquals("High", highPriorityRec.getPriorityText());
        assertEquals("Medium", mediumPriorityRec.getPriorityText());
        assertEquals("Low", lowPriorityRec.getPriorityText());
        
        // Test with invalid priority level
        Recommendation invalidRec = new Recommendation(
            "Test",
            4,  // Invalid priority
            "Test description",
            "Test factor"
        );
        assertEquals("Unknown", invalidRec.getPriorityText());
    }
    
    @Test
    void testToString() {
        // Test string representation
        String highPriorityString = highPriorityRec.toString();
        String mediumPriorityString = mediumPriorityRec.toString();
        String lowPriorityString = lowPriorityRec.toString();
        
        // Check high priority recommendation string
        assertTrue(highPriorityString.contains("[High Priority]"));
        assertTrue(highPriorityString.contains("Biomechanical"));
        assertTrue(highPriorityString.contains("Improve landing technique"));
        assertTrue(highPriorityString.contains("Knee Valgus"));
        
        // Check medium priority recommendation string
        assertTrue(mediumPriorityString.contains("[Medium Priority]"));
        assertTrue(mediumPriorityString.contains("Training"));
        assertTrue(mediumPriorityString.contains("Strengthen hamstrings"));
        assertTrue(mediumPriorityString.contains("Hamstring Weakness"));
        
        // Check low priority recommendation string
        assertTrue(lowPriorityString.contains("[Low Priority]"));
        assertTrue(lowPriorityString.contains("Equipment"));
        assertTrue(lowPriorityString.contains("Use supportive knee braces"));
        assertTrue(lowPriorityString.contains("Joint Laxity"));
    }
    
    @Test
    void testCategoryComparison() {
        // Create recommendations with different categories
        Recommendation biomechRec = new Recommendation("Biomechanical", 1, "Test", "Test");
        Recommendation trainingRec = new Recommendation("Training", 1, "Test", "Test");
        Recommendation equipmentRec = new Recommendation("Equipment", 1, "Test", "Test");
        
        // Check that categories are preserved correctly
        assertNotEquals(biomechRec.getCategory(), trainingRec.getCategory());
        assertNotEquals(trainingRec.getCategory(), equipmentRec.getCategory());
        assertEquals("Biomechanical", biomechRec.getCategory());
        assertEquals("Training", trainingRec.getCategory());
        assertEquals("Equipment", equipmentRec.getCategory());
    }
    
    @Test
    void testPriorityComparison() {
        // Create recommendations with same category but different priorities
        Recommendation highPriority = new Recommendation("Training", 1, "Test", "Test");
        Recommendation mediumPriority = new Recommendation("Training", 2, "Test", "Test");
        Recommendation lowPriority = new Recommendation("Training", 3, "Test", "Test");
        
        // Check that priorities are preserved and compared correctly
        assertTrue(highPriority.getPriority() < mediumPriority.getPriority());
        assertTrue(mediumPriority.getPriority() < lowPriority.getPriority());
        assertEquals(1, highPriority.getPriority());
        assertEquals(2, mediumPriority.getPriority());
        assertEquals(3, lowPriority.getPriority());
    }
}
