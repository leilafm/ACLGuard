package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiskAssessment {
    // Core risk information
    private double overallRiskScore;
    private String riskCategory;
    
    // Component risk scores
    private double demographicRiskScore;
    private double biomechanicalRiskScore;
    private double medicalHistoryRiskScore;
    
    // Key risk factors identified
    private Map<String, Double> keyRiskFactors;
    
    // Recommendations
    private List<Recommendation> recommendations;
    
    // Reference to the athlete
    private String athleteName;
    
  
    
    // overallRiskScore Overall numerical risk score (0-10)
    // riskCategory Risk category (Low, Moderate, High, Very High)
    // demographicRiskScore Risk score for demographic factors
    // biomechanicalRiskScore Risk score for biomechanical factors
    // medicalHistoryRiskScore Risk score for medical history

    public RiskAssessment(String athleteName, double overallRiskScore, 
                         String riskCategory, double demographicRiskScore, 
                         double biomechanicalRiskScore, double medicalHistoryRiskScore) {
        this.athleteName = athleteName;
        this.overallRiskScore = overallRiskScore;
        this.riskCategory = riskCategory;
        this.demographicRiskScore = demographicRiskScore;
        this.biomechanicalRiskScore = biomechanicalRiskScore;
        this.medicalHistoryRiskScore = medicalHistoryRiskScore;
        this.keyRiskFactors = new HashMap<>();
        this.recommendations = new ArrayList<>();
    }
    
    /**
     * Adds a key risk factor identified during assessment.
     * 
     * @param factorName Name of the risk factor
     * @param factorScore Score representing severity of this factor (0-10)
     */
    public void addKeyRiskFactor(String factorName, double factorScore) {
        keyRiskFactors.put(factorName, factorScore);
    }
    
    /**
     * Adds a recommendation to the assessment.
     * 
     * @param recommendation Recommendation object to add
     */
    public void addRecommendation(Recommendation recommendation) {
        recommendations.add(recommendation);
    }
    
    
    // Sets multiple recommendations at once.
    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
    
    
    // Creates a summary of the assessment results.
     
    @Override
    public String toString() {
        StringBuilder summary = new StringBuilder();
        summary.append("ACL Risk Assessment for ").append(athleteName).append("\n\n");
        summary.append("Overall Risk: ").append(riskCategory)
               .append(" (").append(String.format("%.1f", overallRiskScore)).append("/10)\n\n");
        
        summary.append("Risk Component Scores:\n");
        summary.append("- Demographic: ").append(String.format("%.1f", demographicRiskScore)).append("/10\n");
        summary.append("- Biomechanical: ").append(String.format("%.1f", biomechanicalRiskScore)).append("/10\n");
        summary.append("- Medical History: ").append(String.format("%.1f", medicalHistoryRiskScore)).append("/10\n\n");
        
        summary.append("Key Risk Factors:\n");
        for (Map.Entry<String, Double> factor : keyRiskFactors.entrySet()) {
            summary.append("- ").append(factor.getKey()).append(": ")
                   .append(String.format("%.1f", factor.getValue())).append("/10\n");
        }
        
        summary.append("\nRecommendations:\n");
        for (Recommendation rec : recommendations) {
            summary.append("- ").append(rec.toString()).append("\n");
        }
        
        return summary.toString();
    }
    
    // Getters
    
    public double getOverallRiskScore() {
        return overallRiskScore;
    }
    
    public String getRiskCategory() {
        return riskCategory;
    }
    
    public double getDemographicRiskScore() {
        return demographicRiskScore;
    }
    
    public double getBiomechanicalRiskScore() {
        return biomechanicalRiskScore;
    }
    
    public double getMedicalHistoryRiskScore() {
        return medicalHistoryRiskScore;
    }
    
    public Map<String, Double> getKeyRiskFactors() {
        return keyRiskFactors;
    }
    
    public List<Recommendation> getRecommendations() {
        return recommendations;
    }
    
    public String getAthleteName() {
        return athleteName;
    }
}