
package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public interface FeeCalculator {

    public abstract double calculateFeeOwed(double hoursParked);
    
}
