package cms.parkinggarageapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cscherbert1
 */
public class MinimumNoMaximumFeeCalculatorTest {
    private MinimumNoMaximumFeeCalculator instance;
    
    public MinimumNoMaximumFeeCalculatorTest() {
    }
    
    @Before
    public void setUp() {
        instance = new MinimumNoMaximumFeeCalculator(new InclusiveNumericDoubleRangeValidator());
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
    public void calculateFeeOwedReturnsMinChargeOf1AndAHalf(){
        double expected = 1.5;
        double result = instance.calculateFeeOwed(1.0);
        assertEquals(expected, result, 0.0010);
    }
    
}
