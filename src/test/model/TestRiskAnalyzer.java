package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRiskAnalyzer {
    public class RiskAnalyzerTest {
    
    private RiskAnalyzer riskAnalyzer;
    private Athlete lowRiskAthlete;
    private Athlete moderateRiskAthlete;
    private Athlete highRiskAthlete;
    
    @BeforeEach
    void setUp() {
        // Initialize the RiskAnalyzer
        riskAnalyzer = new RiskAnalyzer();
        
        // Create test athletes with different risk profiles
        setupLowRiskAthlete();
        setupModerateRiskAthlete();
        setupHighRiskAthlete();
    }
    
    /**
     * Helper method to create a low risk athlete
     */
    private void setupLowRiskAthlete() {
        // Male athlete (lower risk than female)
        // No previous injuries or family history
        // Low-risk sport (swimming)
        // Good biomechanical profile
        List<Boolean> history = new ArrayList<>();
        history.add(false); // No family history
        history.add(false); // No right knee injury
        history.add(false); // No left knee injury
        
        lowRiskAthlete = new Athlete("LowRisk", 25, false, "Swimming", 175.0, 70.0, history);
        
        // Set up ideal biomechanical values
        BiomechanicalData bioData = lowRiskAthlete.getBioMechData();
        bioData.setKneeValgusAngle(5.0); // Low valgus angle (good)
        bioData.setHipAdductionAngle(10.0); // Low adduction (good)
        bioData.setKneeFlexionAngle(80.0); // Good flexion
        bioData.setHamstringsToQuadsRatio(0.75); // Balanced ratio
        bioData.setLandingAsymmetry(5.0); // Low asymmetry
        bioData.setQAngle(10.0); // Low Q-angle
    }
    
    /**
     * Helper method to create a moderate risk athlete
     */
    private void setupModerateRiskAthlete() {
        // Female athlete (higher risk than male)
        // No previous injuries but has family history
        // Moderate-risk sport (volleyball)
        // Mixed biomechanical profile
        List<Boolean> history = new ArrayList<>();
        history.add(true); // Family history
        history.add(false); // No right knee injury
        history.add(false); // No left knee injury
        
        moderateRiskAthlete = new Athlete("ModerateRisk", 22, true, "Volleyball", 168.0, 62.0, history);
        
        // Set up moderate biomechanical values
        BiomechanicalData bioData = moderateRiskAthlete.getBioMechData();
        bioData.setKneeValgusAngle(12.0); // Moderate valgus angle
        bioData.setHipAdductionAngle(15.0); // Moderate adduction
        bioData.setKneeFlexionAngle(60.0); // Moderate flexion
        bioData.setHamstringsToQuadsRatio(0.6); // Slightly imbalanced
        bioData.setLandingAsymmetry(12.0); // Moderate asymmetry
        bioData.setQAngle(15.0); // Moderate Q-angle
    }
    
    /**
     * Helper method to create a high risk athlete
     */
    private void setupHighRiskAthlete() {
        // Female athlete (higher risk)
        // Previous ACL injury
        // High-risk sport (basketball)
        // Poor biomechanical profile
        List<Boolean> history = new ArrayList<>();
        history.add(true); // Family history
        history.add(true); // Previous right knee injury
        history.add(false); // No left knee injury
        
        highRiskAthlete = new Athlete("HighRisk", 18, true, "Basketball", 170.0, 65.0, history);
        
        // Set up poor biomechanical values
        BiomechanicalData bioData = highRiskAthlete.getBioMechData();
        bioData.setKneeValgusAngle(20.0); // High valgus angle (poor)
        bioData.setHipAdductionAngle(25.0); // High adduction (poor)
        bioData.setKneeFlexionAngle(40.0); // Poor flexion
        bioData.setHamstringsToQuadsRatio(0.45); // Imbalanced ratio
        bioData.setLandingAsymmetry(25.0); // High asymmetry
        bioData.setQAngle(22.0); // High Q-angle
    }
    
    @Test
    void testConstructor() {
        // Test that constructor initializes properly
        assertNotNull(riskAnalyzer);
        
        // Test parameterized constructor if you have one
        RiskAnalyzer customAnalyzer = new RiskAnalyzer(0.2, 0.5, 0.3);
        assertNotNull(customAnalyzer);
    }
    
    @Test
    void testAnalyzeRiskReturnsValidAssessment() {
        // Test that analyze method returns a non-null assessment
        RiskAssessment assessment = riskAnalyzer.analyzeRisk(lowRiskAthlete);
        assertNotNull(assessment);
        
        // Test that the assessment contains expected components
        assertNotNull(assessment.getOverallRiskScore());
        assertNotNull(assessment.getRiskCategory());
        assertNotNull(assessment.getRecommendations());
    }
    
    @Test
    void testRiskCategorization() {
        // Test that athletes with different risk profiles get appropriate categorizations
        RiskAssessment lowAssessment = riskAnalyzer.analyzeRisk(lowRiskAthlete);
        RiskAssessment moderateAssessment = riskAnalyzer.analyzeRisk(moderateRiskAthlete);
        RiskAssessment highAssessment = riskAnalyzer.analyzeRisk(highRiskAthlete);
        
        // Test risk categories are in expected order
        assertTrue(lowAssessment.getOverallRiskScore() < moderateAssessment.getOverallRiskScore());
        assertTrue(moderateAssessment.getOverallRiskScore() < highAssessment.getOverallRiskScore());
        
        assertEquals("Low", lowAssessment.getRiskCategory());
        assertEquals("Moderate", moderateAssessment.getRiskCategory());
        assertEquals("High", highAssessment.getRiskCategory());
    }
    
    @Test
    void testDemographicRiskFactors() {
        // Create athletes with the same biomechanical and medical history but different demographics
        List<Boolean> noHistory = new ArrayList<>();
        noHistory.add(false);
        noHistory.add(false);
        noHistory.add(false);
        
        // Only difference is gender
        Athlete maleAthlete = new Athlete("Male", 20, false, "Soccer", 180.0, 75.0, noHistory);
        Athlete femaleAthlete = new Athlete("Female", 20, true, "Soccer", 170.0, 65.0, noHistory);
        
        // Setup identical biomechanical data
        setupIdenticalBiomechanicalData(maleAthlete.getBioMechData());
        setupIdenticalBiomechanicalData(femaleAthlete.getBioMechData());
        
        // Female should have higher risk due to gender being a risk factor
        RiskAssessment maleAssessment = riskAnalyzer.analyzeRisk(maleAthlete);
        RiskAssessment femaleAssessment = riskAnalyzer.analyzeRisk(femaleAthlete);
        
        assertTrue(maleAssessment.getOverallRiskScore() < femaleAssessment.getOverallRiskScore());
    }
    
    @Test
    void testBiomechanicalRiskFactors() {
        // Create identical athletes but with different biomechanical profiles
        List<Boolean> noHistory = new ArrayList<>();
        noHistory.add(false);
        noHistory.add(false);
        noHistory.add(false);
        
        Athlete goodBiomechanicsAthlete = new Athlete("GoodBio", 20, true, "Soccer", 170.0, 65.0, noHistory);
        Athlete poorBiomechanicsAthlete = new Athlete("PoorBio", 20, true, "Soccer", 170.0, 65.0, noHistory);
        
        // Set different biomechanical profiles
        setupGoodBiomechanicalData(goodBiomechanicsAthlete.getBioMechData());
        setupPoorBiomechanicalData(poorBiomechanicsAthlete.getBioMechData());
        
        // Poor biomechanics should result in higher risk
        RiskAssessment goodAssessment = riskAnalyzer.analyzeRisk(goodBiomechanicsAthlete);
        RiskAssessment poorAssessment = riskAnalyzer.analyzeRisk(poorBiomechanicsAthlete);
        
        assertTrue(goodAssessment.getOverallRiskScore() < poorAssessment.getOverallRiskScore());
    }
    
    @Test
    void testMedicalHistoryRiskFactors() {
        // Create identical athletes but with different medical histories
        List<Boolean> noHistory = new ArrayList<>();
        noHistory.add(false);
        noHistory.add(false);
        noHistory.add(false);
        
        List<Boolean> previousInjuryHistory = new ArrayList<>();
        previousInjuryHistory.add(false);
        previousInjuryHistory.add(true);
        previousInjuryHistory.add(false);
        
        Athlete noInjuryAthlete = new Athlete("NoInjury", 20, true, "Soccer", 170.0, 65.0, noHistory);
        Athlete previousInjuryAthlete = new Athlete("PrevInjury", 20, true, "Soccer", 170.0, 65.0, previousInjuryHistory);
        
        // Setup identical biomechanical data
        setupIdenticalBiomechanicalData(noInjuryAthlete.getBioMechData());
        setupIdenticalBiomechanicalData(previousInjuryAthlete.getBioMechData());
        
        // Previous injury should result in higher risk
        RiskAssessment noInjuryAssessment = riskAnalyzer.analyzeRisk(noInjuryAthlete);
        RiskAssessment injuryAssessment = riskAnalyzer.analyzeRisk(previousInjuryAthlete);
        
        assertTrue(noInjuryAssessment.getOverallRiskScore() < injuryAssessment.getOverallRiskScore());
    }
    
    @Test
    void testGenerateRecommendations() {
        // Test that recommendations are generated for each risk factor
        RiskAssessment assessment = riskAnalyzer.analyzeRisk(highRiskAthlete);
        List<Recommendation> recommendations = assessment.getRecommendations();
        
        // Should have multiple recommendations
        assertTrue(recommendations.size() > 0);
        
        // Should have recommendations for biomechanical factors
        boolean hasBiomechanicalRecommendation = false;
        for (Recommendation rec : recommendations) {
            if (rec.getCategory().equals("Biomechanical")) {
                hasBiomechanicalRecommendation = true;
                break;
            }
        }
        assertTrue(hasBiomechanicalRecommendation);
    }
    
    /**
     * Helper method to set up identical biomechanical data for comparison tests
     */
    private void setupIdenticalBiomechanicalData(BiomechanicalData bioData) {
        bioData.setKneeValgusAngle(10.0);
        bioData.setHipAdductionAngle(15.0);
        bioData.setKneeFlexionAngle(60.0);
        bioData.setHamstringsToQuadsRatio(0.6);
        bioData.setLandingAsymmetry(10.0);
        bioData.setQAngle(15.0);
    }
    
    /**
     * Helper method to set up good biomechanical data
     */
    private void setupGoodBiomechanicalData(BiomechanicalData bioData) {
        bioData.setKneeValgusAngle(5.0);
        bioData.setHipAdductionAngle(10.0);
        bioData.setKneeFlexionAngle(80.0);
        bioData.setHamstringsToQuadsRatio(0.75);
        bioData.setLandingAsymmetry(5.0);
        bioData.setQAngle(10.0);
    }
    
    /**
     * Helper method to set up poor biomechanical data
     */
    private void setupPoorBiomechanicalData(BiomechanicalData bioData) {
        bioData.setKneeValgusAngle(25.0);
        bioData.setHipAdductionAngle(30.0);
        bioData.setKneeFlexionAngle(30.0);
        bioData.setHamstringsToQuadsRatio(0.4);
        bioData.setLandingAsymmetry(30.0);
        bioData.setQAngle(25.0);
    }
}
}
