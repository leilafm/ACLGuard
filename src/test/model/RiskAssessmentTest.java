package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test class for RiskAssessment.
 */
public class RiskAssessmentTest {
    
    private RiskAssessment assessment;
    
    @BeforeEach
    void setUp() {
        // Initialize a risk assessment with test values
        assessment = new RiskAssessment(
            "Test Athlete",    // athleteName
            7.5,               // overallRiskScore
            "High",            // riskCategory
            6.0,               // demographicRiskScore
            8.0,               // biomechanicalRiskScore
            7.0                // medicalHistoryRiskScore
        );
    }
    
    @Test
    void testConstructor() {
        // Test that constructor properly initializes fields
        assertEquals("Test Athlete", assessment.getAthleteName());
        assertEquals(7.5, assessment.getOverallRiskScore());
        assertEquals("High", assessment.getRiskCategory());
        assertEquals(6.0, assessment.getDemographicRiskScore());
        assertEquals(8.0, assessment.getBiomechanicalRiskScore());
        assertEquals(7.0, assessment.getMedicalHistoryRiskScore());
        
        // Test that collections are initialized but empty
        assertTrue(assessment.getKeyRiskFactors().isEmpty());
        assertTrue(assessment.getRecommendations().isEmpty());
    }
    
    @Test
    void testAddKeyRiskFactor() {
        // Add key risk factors
        assessment.addKeyRiskFactor("Knee Valgus", 8.5);
        assessment.addKeyRiskFactor("Hamstring Weakness", 7.2);
        
        // Test that factors were added properly
        Map<String, Double> factors = assessment.getKeyRiskFactors();
        assertEquals(2, factors.size());
        assertEquals(8.5, factors.get("Knee Valgus"));
        assertEquals(7.2, factors.get("Hamstring Weakness"));
    }
    
    @Test
    void testAddRecommendation() {
        // Create and add recommendations
        Recommendation rec1 = new Recommendation("Biomechanical", 1, 
                "Improve landing technique", "Knee Valgus");
        Recommendation rec2 = new Recommendation("Training", 2, 
                "Strengthen hamstrings", "Hamstring Weakness");
        
        assessment.addRecommendation(rec1);
        assessment.addRecommendation(rec2);
        
        // Test that recommendations were added
        List<Recommendation> recommendations = assessment.getRecommendations();
        assertEquals(2, recommendations.size());
        assertEquals("Biomechanical", recommendations.get(0).getCategory());
        assertEquals("Training", recommendations.get(1).getCategory());
    }
    
    @Test
    void testSetRecommendations() {
        // Create a list of recommendations
        List<Recommendation> recommendationList = new ArrayList<>();
        recommendationList.add(new Recommendation("Biomechanical", 1, 
                "Improve landing technique", "Knee Valgus"));
        recommendationList.add(new Recommendation("Training", 2, 
                "Strengthen hamstrings", "Hamstring Weakness"));
        
        // Set the list of recommendations
        assessment.setRecommendations(recommendationList);
        
        // Test that the list was set correctly
        assertEquals(2, assessment.getRecommendations().size());
        assertEquals("Biomechanical", assessment.getRecommendations().get(0).getCategory());
    }
    
    @Test
    void testToString() {
        // Add some risk factors and recommendations
        assessment.addKeyRiskFactor("Knee Valgus", 8.5);
        assessment.addRecommendation(new Recommendation("Biomechanical", 1, 
                "Improve landing technique", "Knee Valgus"));
        
        // Get the string representation
        String result = assessment.toString();
        
        // Test that the string contains expected information
        assertTrue(result.contains("Test Athlete"));
        assertTrue(result.contains("High"));
        assertTrue(result.contains("7.5"));
        assertTrue(result.contains("Knee Valgus"));
        assertTrue(result.contains("Biomechanical"));
        assertTrue(result.contains("Improve landing technique"));
    }
}
