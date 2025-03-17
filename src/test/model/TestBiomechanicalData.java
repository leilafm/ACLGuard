package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class TestBiomechanicalData {
    private BiomechanicalData bioData;
    private static final double DELTA = 0.001; // Delta for double comparison
    
    @Before
    public void setUp() {
        bioData = new BiomechanicalData();
    }
    
    @Test
    public void testDefaultConstructor() {
        assertEquals(0.0, bioData.getKneeValgusAngle(), DELTA);
        assertEquals(0.0, bioData.getHipAdductionAngle(), DELTA);
        assertEquals(0.0, bioData.getKneeFlexionAngle(), DELTA);
        assertEquals(0.0, bioData.getVerticalJumpHeight(), DELTA);
        assertEquals(0.0, bioData.getLandingForce(), DELTA);
        assertEquals(0.0, bioData.getLandingAsymmetry(), DELTA);
        assertEquals(0.0, bioData.getHamstringsToQuadsRatio(), DELTA);
        assertEquals(0, bioData.getSingleLegBalanceTime());
        assertEquals(0.0, bioData.getProprioceptionScore(), DELTA);
        assertEquals(0.0, bioData.getQAngle(), DELTA);
        assertEquals(0.0, bioData.getJointLaxity(), DELTA);
        assertEquals(0.0, bioData.getIntercondylarNotchWidth(), DELTA);
    }
    
    @Test
    public void testParameterizedConstructor() {
        // Initialize with all parameters
        bioData = new BiomechanicalData(
                15.5,   
                8.3,    
                45.2,   
                50.0,  
                2.5,    
                12.7,   
                0.65,   
                30,     
                7.5,    
                18.0,   
                2.3,    
                22.5   
        );
        
       
        assertEquals(15.5, bioData.getKneeValgusAngle(), DELTA);
        assertEquals(8.3, bioData.getHipAdductionAngle(), DELTA);
        assertEquals(45.2, bioData.getKneeFlexionAngle(), DELTA);
        assertEquals(50.0, bioData.getVerticalJumpHeight(), DELTA);
        assertEquals(2.5, bioData.getLandingForce(), DELTA);
        assertEquals(12.7, bioData.getLandingAsymmetry(), DELTA);
        assertEquals(0.65, bioData.getHamstringsToQuadsRatio(), DELTA);
        assertEquals(30, bioData.getSingleLegBalanceTime());
        assertEquals(7.5, bioData.getProprioceptionScore(), DELTA);
        assertEquals(18.0, bioData.getQAngle(), DELTA);
        assertEquals(2.3, bioData.getJointLaxity(), DELTA);
        assertEquals(22.5, bioData.getIntercondylarNotchWidth(), DELTA);
    }
}
