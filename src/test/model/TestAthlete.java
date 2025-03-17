package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class TestAthlete {
    Athlete testAthlete;
    List<Boolean> history;
    double bmi;
    BiomechanicalData biomech;
    
    @BeforeEach
    void runBefore() {
        history = new ArrayList<Boolean>();
        history.add(false);
        history.add(true);
        history.add(false);

        testAthlete = new Athlete("ATHL1", 22, true, "Soccer", 165.1, 54.4, history);
    }

    @Test
    void testConstructor() {
        assertEquals("ATHL1", testAthlete.getName());
        assertEquals(22, testAthlete.getAge());
        assertTrue(testAthlete.getGender());
        assertEquals("Soccer", testAthlete.getSport());
        assertEquals(165.1, testAthlete.getHeight());
        assertEquals(54.4, testAthlete.getWeight());
        assertFalse(testAthlete.hasFamilyHistory());
        assertFalse(testAthlete.hasPrevInjuryL());
        assertTrue(testAthlete.hasPrevInjuryR());
        double height = testAthlete.getHeight();
        double weight = testAthlete.getWeight();
        bmi = weight / ((height * height) / 10000);
        assertEquals(bmi, testAthlete.calculateBMI(height, weight));
        assertEquals(bmi, testAthlete.getBMI());
        assertEquals(history, testAthlete.getHistory());
        assertNotNull(testAthlete.getBioMechData());
    }
}
