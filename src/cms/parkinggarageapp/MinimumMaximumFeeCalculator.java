package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class MinimumMaximumFeeCalculator implements FeeCalculator {
    private double feeOwed;
    private final double ZERO = 0;
    private double minimumTimeParked = 3.0;
    private double maximumTimeParked = 24.0;
    private double timeNeededToReachMaxFee = 16.0;
    private Validator doubleRange;
    private double minimumCharge = 2.0;
    private double maximumCharge = 10.0;
    private double hourlyChargeAfterMinTime = 0.5;
    private static int minMaxFeeCalcNumber = 0;
    private int minMaxFeeCalcId;
    

    public MinimumMaximumFeeCalculator(Validator doubleRange){
        minMaxFeeCalcNumber++;
        setMinMaxFeeCalcId(minMaxFeeCalcNumber);
        setDoubleRangeValidator(doubleRange);
    }
    
    @Override
    public final double calculateFeeOwed(double hoursParked)throws IllegalArgumentException{        
        feeOwed = 0.0;
        
        //validation of hours parked
        Object[] inputItems = {new Double(hoursParked), new Double(ZERO), new Double(maximumTimeParked)};
        if (!doubleRange.isValid(inputItems)){
            throw new IllegalArgumentException("Hours parked must be greater than 0 and less than 24 hours.");
        } else {
            double adjustedTimeParked = Math.ceil(hoursParked);
            
            if(adjustedTimeParked > 0 && adjustedTimeParked < minimumTimeParked){
                feeOwed = minimumCharge;
            } else if (adjustedTimeParked > minimumTimeParked && adjustedTimeParked < timeNeededToReachMaxFee){
                feeOwed = (hourlyChargeAfterMinTime * adjustedTimeParked) + minimumCharge;
            } else if (adjustedTimeParked > timeNeededToReachMaxFee && adjustedTimeParked <= maximumTimeParked){
                feeOwed = maximumCharge;
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

    public final int getMinMaxFeeCalcId() {
        return minMaxFeeCalcId;
    }

    public final void setMinMaxFeeCalcId(int minMaxFeeCalcId) throws IllegalArgumentException {
        if (minMaxFeeCalcId < 0){
            throw new IllegalArgumentException("The unique identification number for this Fee Calculator object must be greater than 0.");
        } else {
            this.minMaxFeeCalcId = minMaxFeeCalcId;
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.minMaxFeeCalcId;
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
        final MinimumMaximumFeeCalculator other = (MinimumMaximumFeeCalculator) obj;
        if (this.minMaxFeeCalcId != other.minMaxFeeCalcId) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString() {
        return "MinimumMaximumFeeCalculator{" + "feeOwed=" + feeOwed + ", ZERO=" + ZERO + ", minimumTimeParked=" + minimumTimeParked + ", maximumTimeParked=" + maximumTimeParked + ", timeNeededToReachMaxFee=" + timeNeededToReachMaxFee + ", minimumCharge=" + minimumCharge + ", maximumCharge=" + maximumCharge + ", hourlyChargeAfterMinTime=" + hourlyChargeAfterMinTime + '}';
    }
    
    
}
