package model;

//stores biomechanical measurements for an athlete related to ACL injury risk assessment.
public class BiomechanicalData {
    // Landing mechanics
    private double kneeValgusAngle; // degrees
    private double hipAdductionAngle; // degrees
    private double kneeFlexionAngle; // degrees
    
    // Jump measurements
    private double verticalJumpHeight; // cm
    private double landingForce; // N/kg (Newtons per kg of body weight)
    private double landingAsymmetry; // percentage difference between legs
    
    // Neuromuscular control
    private double hamstringsToQuadsRatio; // H:Q ratio
    private int singleLegBalanceTime; // seconds
    private double proprioceptionScore; // 0-10 scale
    
    // Anatomical factors
    private double qAngle; // degrees
    private double jointLaxity; // scale 0-5
    private double intercondylarNotchWidth; // mm (if available from imaging)
    
    public BiomechanicalData() {
        this.kneeValgusAngle = 0.0;
        this.hipAdductionAngle = 0.0;
        this.kneeFlexionAngle = 0.0;
        this.verticalJumpHeight = 0.0;
        this.landingForce = 0.0;
        this.landingAsymmetry = 0.0;
        this.hamstringsToQuadsRatio = 0.0;
        this.singleLegBalanceTime = 0;
        this.proprioceptionScore = 0.0;
        this.qAngle = 0.0;
        this.jointLaxity = 0.0;
        this.intercondylarNotchWidth = 0.0;
    }
    
    
     // kneeValgusAngle Knee valgus angle in degrees
     // hipAdductionAngle Hip adduction angle in degrees
     // kneeFlexionAngle Knee flexion angle in degrees
     // verticalJumpHeight Vertical jump height in cm
     // landingForce Force during landing in N/kg
     // landingAsymmetry Percentage difference between legs
     // hamstringsToQuadsRatio H:Q strength ratio
     // singleLegBalanceTime Balance time in seconds
     // proprioceptionScore Proprioception on scale 0-10
     // qAngle Q-angle in degrees
     // jointLaxity Joint laxity on scale 0-5
     // intercondylarNotchWidth Notch width in mm
    
    public BiomechanicalData(double kneeValgusAngle, double hipAdductionAngle, 
                            double kneeFlexionAngle, double verticalJumpHeight, double landingForce,
                            double landingAsymmetry, double hamstringsToQuadsRatio,
                            int singleLegBalanceTime, double proprioceptionScore,
                            double qAngle, double jointLaxity, double intercondylarNotchWidth) {
        this.kneeValgusAngle = kneeValgusAngle;
        this.hipAdductionAngle = hipAdductionAngle;
        this.kneeFlexionAngle = kneeFlexionAngle;
        this.verticalJumpHeight = verticalJumpHeight;
        this.landingForce = landingForce;
        this.landingAsymmetry = landingAsymmetry;
        this.hamstringsToQuadsRatio = hamstringsToQuadsRatio;
        this.singleLegBalanceTime = singleLegBalanceTime;
        this.proprioceptionScore = proprioceptionScore;
        this.qAngle = qAngle;
        this.jointLaxity = jointLaxity;
        this.intercondylarNotchWidth = intercondylarNotchWidth;
    }
    
    
    public double getKneeValgusAngle() {
        return kneeValgusAngle;
    }
    
    public double getHipAdductionAngle() {
        return hipAdductionAngle;
    }
    
    
    public double getKneeFlexionAngle() {
        return kneeFlexionAngle;
    }
    
    
    public double getVerticalJumpHeight() {
        return verticalJumpHeight;
    }


    public double getLandingForce() {
        return landingForce;
    }
    
    

    public double getLandingAsymmetry() {
        return landingAsymmetry;
    }
    
    

    public double getHamstringsToQuadsRatio() {
        return hamstringsToQuadsRatio;
    }
    

    public int getSingleLegBalanceTime() {
        return singleLegBalanceTime;
    }
    

    public double getProprioceptionScore() {
        return proprioceptionScore;
    }
    


    public double getQAngle() {
        return qAngle;
    }
    

    public double getJointLaxity() {
        return jointLaxity;
    }
    
 
    public double getIntercondylarNotchWidth() {
        return intercondylarNotchWidth;
    }
    
}

