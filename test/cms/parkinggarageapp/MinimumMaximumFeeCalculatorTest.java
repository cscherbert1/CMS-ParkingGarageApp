package cms.parkinggarageapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *This is a Test class for the MininumMaximumFeeCalculator strategy object.
 * 
 * @author cscherbert1
 */
public class MinimumMaximumFeeCalculatorTest {
    
    private MinimumMaximumFeeCalculator instance;
    
    public MinimumMaximumFeeCalculatorTest() {
    }
    
    @Before
    public void setUp() {
        instance = new MinimumMaximumFeeCalculator(new InclusiveNumericDoubleRangeValidator());
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    @Test (expected = IllegalArgumentException.class)
    public void calculateFeeOwedThrowsIaeIfHoursParkedLessThanZero(){
        Double[] badHoursParkedSmall = {-1.0, -0.0000000000001, -100000.0};
        for(Double badValue: badHoursParkedSmall){
            instance.calculateFeeOwed(badValue);
        }
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void calculateFeeOwedThrowsIaeIfHoursParkedGreaterThan24(){
        Double[] badHoursParkedLarge = {25.0, 24.00000001, 1000000.0};
        for (Double badValue : badHoursParkedLarge){
            instance.calculateFeeOwed(badValue);
        }
    }
    
    @Test
    public void calculateFeeOwedReturnsDoubleWhenValidInputPassedIn(){
        Double[] goodValues = {2.0, 10.2, 23.9999999999};
        for (Double goodValue : goodValues){
            try{
                instance.calculateFeeOwed(goodValue);
            } catch (IllegalArgumentException iae){
                fail ("Good values were not passed in. Hours Parked (double) should be between 0 and 24");
            }
        }
    }
    
    @Test
    public void calculateFeeOwedRetrunsMaxChargeOf10(){
        double expected = 10.0;
        double result = instance.calculateFeeOwed(24);
        assertEquals(expected, result, 0.0010);
    }
    
    @Test
    public void calculateFeeOwedReturnsMinChargeOf2(){
        double expected = 2.0;
        double result = instance.calculateFeeOwed(1.0);
        assertEquals(expected, result, 0.0010);
    }
    
}
