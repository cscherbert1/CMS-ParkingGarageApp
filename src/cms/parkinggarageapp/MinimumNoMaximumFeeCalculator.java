package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */

public class MinimumNoMaximumFeeCalculator implements FeeCalculator{
    private double feeOwed;
    private final double ZERO = 0;
    private final double minimumTimeParked = 2.0;
    private final double maximumTimeParked = 24.0;
    private final double minimumCharge = 1.5;
    private final double hourlyChargeAfterMinTime = 0.75;
    private Validator doubleRange;
    private static int minNoMaxFeeCalcNumber = 0;
    private int minNoMaxFeeCalcId;
    

    public MinimumNoMaximumFeeCalculator(Validator doubleRange){
        minNoMaxFeeCalcNumber++;
        setMinNoMaxFeeCalcId(minNoMaxFeeCalcNumber);
        setDoubleRangeValidator(doubleRange);
    }
    
    @Override
    public final double calculateFeeOwed(double hoursParked) throws IllegalArgumentException {
        feeOwed = 0.0;
        
        //validation of hours parked
         Object[] inputItems = {new Double(hoursParked), new Double(ZERO), new Double(maximumTimeParked)};
         if(!doubleRange.isValid(inputItems)){
            throw new IllegalArgumentException("Hours parked must be greater than 0 and less than 24 hours.");
        } else {
            double adjustedTimeParked = Math.ceil(hoursParked);
            
            if(adjustedTimeParked > 0 && adjustedTimeParked <= minimumTimeParked){
                feeOwed = minimumCharge;
            } else if (adjustedTimeParked > minimumTimeParked && adjustedTimeParked <= maximumTimeParked){
                feeOwed = (hourlyChargeAfterMinTime * adjustedTimeParked) + minimumCharge;
            }
        }
        return feeOwed;
    }
    
    private void setDoubleRangeValidator(Validator doubleRange) throws IllegalArgumentException{
        if (doubleRange == null){
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.doubleRange = doubleRange;
        }
    } 

    public final int getMinNoMaxFeeCalcId() {
        return minNoMaxFeeCalcId;
    }

    public final void setMinNoMaxFeeCalcId(int minNoMaxFeeCalcId) throws IllegalArgumentException{
        if(minNoMaxFeeCalcId < 0){
            throw new IllegalArgumentException("The unique identification number for this Fee Calculator must be greater than 0.");
        } else {
            this.minNoMaxFeeCalcId = minNoMaxFeeCalcId;
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.minNoMaxFeeCalcId;
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MinimumNoMaximumFeeCalculator other = (MinimumNoMaximumFeeCalculator) obj;
        if (this.minNoMaxFeeCalcId != other.minNoMaxFeeCalcId) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString() {
        return "MinimumNoMaximumFeeCalculator{" + "feeOwed=" + feeOwed + ", ZERO=" + ZERO + ", minimumTimeParked=" + minimumTimeParked + ", maximumTimeParked=" + maximumTimeParked + ", minimumCharge=" + minimumCharge + ", hourlyChargeAfterMinTime=" + hourlyChargeAfterMinTime + '}';
    }
    
    
}
