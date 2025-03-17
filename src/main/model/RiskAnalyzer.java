package model;

import java.util.ArrayList;
import java.util.List;

// Analyzes athlete risk based on demographics, biomechanics, and medical assessments
public class RiskAnalyzer {
    // Constants for risk thresholds
    private static final double LOW_RISK_THRESHOLD = 3.0;
    private static final double MODERATE_RISK_THRESHOLD = 6.0;
    private static final double HIGH_RISK_THRESHOLD = 8.0;
    
    // Constants for high-risk sports
    private static final String[] HIGH_RISK_SPORTS = {
        "Basketball", "Soccer", "Football", "Handball", "Volleyball"
    };
    
    // Component weights for overall risk calculation
    private double demographicWeight;
    private double biomechanicalWeight;
    private double medicalWeight;
    
    /**
     * Default constructor with standard risk weighting.
     */
    public RiskAnalyzer() {
        // Default weights (biomechanical factors highest)
        this.demographicWeight = 0.25;
        this.biomechanicalWeight = 0.5;
        this.medicalWeight = 0.25;
    }
    

    //  Constructor with custom risk weighting.
    //  demographicWeight Weight for demographic factors (should sum to 1.0 with other weights)
    //  biomechanicalWeight Weight for biomechanical factors
    //  medicalWeight Weight for medical history factors
    public RiskAnalyzer(double demographicWeight, double biomechanicalWeight, double medicalWeight) {
        // Validate weights sum to approximately 1.0
        double sum = demographicWeight + biomechanicalWeight + medicalWeight;
        if (sum < 0.99 || sum > 1.01) {
            throw new IllegalArgumentException("Weights must sum to 1.0");
        }
        
        this.demographicWeight = demographicWeight;
        this.biomechanicalWeight = biomechanicalWeight;
        this.medicalWeight = medicalWeight;
    }
    
    
    // Main analysis method that evaluates ACL injury risk for an athlete.
    // athlete The athlete to analyze
    // return RiskAssessment object containing comprehensive risk evaluation
    
    public RiskAssessment analyzeRisk(Athlete athlete) {
        // Calculate component risk scores
        double demographicRisk = assessDemographicRisk(athlete);
        double biomechanicalRisk = assessBiomechanicalRisk(athlete.getBioMechData());
        double medicalRisk = assessMedicalHistoryRisk(athlete);
        
        // Calculate overall risk score
        double overallRisk = calculateOverallRisk(demographicRisk, biomechanicalRisk, medicalRisk);
        
        // Determine risk category
        String riskCategory = categorizeRisk(overallRisk);
        
        // Create assessment
        RiskAssessment assessment = new RiskAssessment(
            athlete.getName(),
            overallRisk,
            riskCategory,
            demographicRisk,
            biomechanicalRisk,
            medicalRisk
        );
        
        // Add key risk factors
        addKeyRiskFactors(assessment, athlete);
        
        // Generate recommendations
        List<Recommendation> recommendations = generateRecommendations(assessment, athlete);
        assessment.setRecommendations(recommendations);
        
        return assessment;
    }
    
 
    // Evaluates demographic risk factors (age, gender, sport, etc.)
    // athlete is The athlete to analyze
    // returns Risk score for demographic factors (0-10)
    private double assessDemographicRisk(Athlete athlete) {
        double riskScore = 0.0;
        
        // Gender risk (females have higher ACL injury risk)
        if (athlete.getGender()) {  // True = Female in your implementation
            riskScore += 2.0;
        }
        
        // Age risk (adolescents and young adults at higher risk)
        int age = athlete.getAge();
        if (age >= 14 && age <= 25) {
            riskScore += 1.5;
        }
        
        // Sport risk (certain sports have higher ACL injury rates)
        String sport = athlete.getSport();
        for (String highRiskSport : HIGH_RISK_SPORTS) {
            if (sport.equalsIgnoreCase(highRiskSport)) {
                riskScore += 2.0;
                break;
            }
        }
        
        // BMI risk (very low or very high BMI can be risk factors)
        double bmi = athlete.getBMI();
        if (bmi < 18.5 || bmi > 30.0) {
            riskScore += 1.0;
        }
        
        // Normalize to 0-10 scale
        return Math.min(10.0, riskScore);
    }
    
    
    // Evaluates biomechanical risk factors (landing mechanics, muscle imbalances, etc.)
    // data is Biomechanical measurements for the athlete
    // returns Risk score for biomechanical factors (0-10)
    private double assessBiomechanicalRisk(BiomechanicalData data) {
        double riskScore = 0.0;
        
        // Knee valgus risk (higher angles = higher risk)
        double kneeValgusAngle = data.getKneeValgusAngle();
        if (kneeValgusAngle > 15.0) {
            riskScore += 2.0;
        } else if (kneeValgusAngle > 10.0) {
            riskScore += 1.0;
        }
        
        // Hip adduction risk
        double hipAdductionAngle = data.getHipAdductionAngle();
        if (hipAdductionAngle > 20.0) {
            riskScore += 1.5;
        } else if (hipAdductionAngle > 15.0) {
            riskScore += 0.75;
        }
        
        // Knee flexion risk (lower angles = higher risk)
        double kneeFlexionAngle = data.getKneeFlexionAngle();
        if (kneeFlexionAngle < 45.0) {
            riskScore += 1.5;
        } else if (kneeFlexionAngle < 60.0) {
            riskScore += 0.75;
        }
        
        // Hamstrings to quadriceps strength ratio risk (lower ratio = higher risk)
        double hqRatio = data.getHamstringsToQuadsRatio();
        if (hqRatio < 0.5) {
            riskScore += 2.0;
        } else if (hqRatio < 0.6) {
            riskScore += 1.0;
        }
        
        // Landing asymmetry risk
        double asymmetry = data.getLandingAsymmetry();
        if (asymmetry > 20.0) {
            riskScore += 1.5;
        } else if (asymmetry > 10.0) {
            riskScore += 0.75;
        }
        
        // Q-angle risk
        double qAngle = data.getQAngle();
        if (qAngle > 20.0) {
            riskScore += 1.5;
        } else if (qAngle > 15.0) {
            riskScore += 0.75;
        }
        
        // Normalize to 0-10 scale
        return Math.min(10.0, riskScore);
    }
    

    // Evaluates medical history risk factors (previous injuries, family history)
    // athlete is the athlete to analyze
    // returns Risk score for medical history (0-10)
    private double assessMedicalHistoryRisk(Athlete athlete) {
        double riskScore = 0.0;
        
        // Previous ACL injury (highest risk factor)
        if (athlete.hasPrevInjuryR() || athlete.hasPrevInjuryL()) {
            riskScore += 6.0;  // Very high risk factor
        }
        
        // Family history of ACL injury
        if (athlete.hasFamilyHistory()) {
            riskScore += 2.0;
        }
        
        // Normalize to 0-10 scale
        return Math.min(10.0, riskScore);
    }
    

    //  Calculates overall risk score by combining component scores with appropriate weights.
    // demographicRisk Demographic risk score
    // biomechanicalRisk is Biomechanical risk score
    // medicalRisk os Medical history risk score
    // returns Overall risk score (0-10)
    private double calculateOverallRisk(double demographicRisk, double biomechanicalRisk, double medicalRisk) {
        return (demographicRisk * demographicWeight) + 
               (biomechanicalRisk * biomechanicalWeight) + 
               (medicalRisk * medicalWeight);
    }
    
    // Categorizes numerical risk score into a risk category.
    // riskScore is Overall risk score
    // returns Risk category (Low, Moderate, High, Very High)
    private String categorizeRisk(double riskScore) {
        if (riskScore <= LOW_RISK_THRESHOLD) {
            return "Low";
        } else if (riskScore <= MODERATE_RISK_THRESHOLD) {
            return "Moderate";
        } else if (riskScore <= HIGH_RISK_THRESHOLD) {
            return "High";
        } else {
            return "Very High";
        }
    }
    
    // Adds key risk factors to the assessment.
    // assessment The risk assessment to update
    // athlete The athlete being analyzed
    private void addKeyRiskFactors(RiskAssessment assessment, Athlete athlete) {
        BiomechanicalData data = athlete.getBioMechData();
        
        // Check for previous injury (highest risk factor)
        if (athlete.hasPrevInjuryR() || athlete.hasPrevInjuryL()) {
            assessment.addKeyRiskFactor("Previous ACL Injury", 9.0);
        }
        
        // Check knee valgus angle
        double kneeValgusAngle = data.getKneeValgusAngle();
        if (kneeValgusAngle > 10.0) {
            double score = Math.min(10.0, 5.0 + (kneeValgusAngle - 10.0) / 2.0);
            assessment.addKeyRiskFactor("Excessive Knee Valgus", score);
        }
        
        // Check hip adduction angle
        double hipAdductionAngle = data.getHipAdductionAngle();
        if (hipAdductionAngle > 15.0) {
            double score = Math.min(10.0, 5.0 + (hipAdductionAngle - 15.0) / 3.0);
            assessment.addKeyRiskFactor("Excessive Hip Adduction", score);
        }
        
        // Check knee flexion angle (lower = higher risk)
        double kneeFlexionAngle = data.getKneeFlexionAngle();
        if (kneeFlexionAngle < 60.0) {
            double score = Math.min(10.0, 5.0 + (60.0 - kneeFlexionAngle) / 4.0);
            assessment.addKeyRiskFactor("Insufficient Knee Flexion", score);
        }
        
        // Check hamstrings to quadriceps ratio
        double hqRatio = data.getHamstringsToQuadsRatio();
        if (hqRatio < 0.6) {
            double score = Math.min(10.0, 5.0 + (0.6 - hqRatio) * 20.0);
            assessment.addKeyRiskFactor("Hamstring-Quadriceps Imbalance", score);
        }
        
        // Check landing asymmetry
        double asymmetry = data.getLandingAsymmetry();
        if (asymmetry > 10.0) {
            double score = Math.min(10.0, 5.0 + (asymmetry - 10.0) / 4.0);
            assessment.addKeyRiskFactor("Landing Asymmetry", score);
        }
        
        // Check Q-angle
        double qAngle = data.getQAngle();
        if (qAngle > 15.0) {
            double score = Math.min(10.0, 5.0 + (qAngle - 15.0) / 3.0);
            assessment.addKeyRiskFactor("Elevated Q-Angle", score);
        }
        
        // Check if female (demographic risk factor)
        if (athlete.getGender()) {
            assessment.addKeyRiskFactor("Female Gender", 7.0);
        }
        
        // Check if high-risk sport
        String sport = athlete.getSport();
        for (String highRiskSport : HIGH_RISK_SPORTS) {
            if (sport.equalsIgnoreCase(highRiskSport)) {
                assessment.addKeyRiskFactor("High-Risk Sport Participation", 7.0);
                break;
            }
        }
    }
    

    // Generates personalized recommendations based on identified risk factors.
    // assessment The risk assessment containing risk factors
    // The athlete being analyzed
    // returns List of recommendations
    private List<Recommendation> generateRecommendations(RiskAssessment assessment, Athlete athlete) {
        List<Recommendation> recommendations = new ArrayList<>();
        BiomechanicalData data = athlete.getBioMechData();
        
        // Previous injury recommendations
        if (athlete.hasPrevInjuryR() || athlete.hasPrevInjuryL()) {
            recommendations.add(new Recommendation(
                "Medical", 
                1, 
                "Consult with sports medicine physician and physical therapist for comprehensive ACL prevention program",
                "Previous ACL Injury"
            ));
        }
        
        // Knee valgus recommendations
        if (data.getKneeValgusAngle() > 10.0) {
            recommendations.add(new Recommendation(
                "Biomechanical", 
                1, 
                "Implement jump-landing training to reduce knee valgus during landing",
                "Excessive Knee Valgus"
            ));
        }
        
        // Hip adduction recommendations
        if (data.getHipAdductionAngle() > 15.0) {
            recommendations.add(new Recommendation(
                "Training", 
                2, 
                "Strengthen hip abductors (glute medius) with side planks and lateral band walks",
                "Excessive Hip Adduction"
            ));
        }
        
        // Knee flexion recommendations
        if (data.getKneeFlexionAngle() < 60.0) {
            recommendations.add(new Recommendation(
                "Biomechanical", 
                1, 
                "Practice soft landing techniques with increased knee bend",
                "Insufficient Knee Flexion"
            ));
        }
        
        // Hamstrings to quadriceps ratio recommendations
        if (data.getHamstringsToQuadsRatio() < 0.6) {
            recommendations.add(new Recommendation(
                "Training", 
                1, 
                "Implement hamstring strengthening program (Nordic hamstring curls, deadlifts)",
                "Hamstring-Quadriceps Imbalance"
            ));
        }
        
        // Landing asymmetry recommendations
        if (data.getLandingAsymmetry() > 10.0) {
            recommendations.add(new Recommendation(
                "Biomechanical", 
                2, 
                "Implement single-leg landing exercises with focus on symmetrical loading",
                "Landing Asymmetry"
            ));
        }
        
        // General recommendations based on risk level
        if (assessment.getRiskCategory().equals("High") || assessment.getRiskCategory().equals("Very High")) {
            recommendations.add(new Recommendation(
                "Educational", 
                1, 
                "Complete neuromuscular training program designed specifically for ACL injury prevention",
                "Overall High Risk"
            ));
        }
        
        if (recommendations.isEmpty()) {
            recommendations.add(new Recommendation(
                "General", 
                3, 
                "Continue current training regimen with regular monitoring of biomechanical factors",
                "Maintenance of Low Risk Profile"
            ));
        }
        
        return recommendations;
    }
}
