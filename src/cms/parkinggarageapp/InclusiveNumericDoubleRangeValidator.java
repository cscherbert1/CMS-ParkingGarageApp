package cms.parkinggarageapp;

/**
 *A Validator object used to ensure the input (double) is between a minimum and maximum inclusively. Implements the <code>Validator</code> interface. 
 * 
 * @author Collin Scherbert
 */
public class InclusiveNumericDoubleRangeValidator implements Validator{
    
    private static int startingRangeValId = 0;
    private int doubleRangeValId;
    
    
    /**
     * Constructor: 
     * Creates an InclusiveNumericDoubleRangeValidator object. The unique identification number associated with these objects is automatically incremented. 
     */
    public InclusiveNumericDoubleRangeValidator(){
        startingRangeValId++;
        setDoubleRangeValId(startingRangeValId);
        
    }
    

    /**
     * 
      * @param inputItems- An array of objects. In order to work correctly, the order each item is placed in the array must be consistent with documentation see below: 
     * <ul>
     * <li> inputItems[0]: The input number being validated
     * <li> inputItems[1]: The minimum value (lower bound) of the range input is being compared to.
     * <li> inputItems[2]: The maximum value (upper bound) of the range input is being compared to. 
     * </ul>
     * @return True or False (boolean). True is returned if input is between the upper and lower bounds. False is returned if input is outside of these bounds. 
     */
    @Override
    public final boolean isValid(Object[] inputItems) {

        double input = (Double)inputItems[0];
        double min = (Double)inputItems[1];
        double max = (Double)inputItems[2];
        
        if(input < min || input > max) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * Returns the unique identifier (doubleRangeValId) as an int.
     * 
     * @return doubleRangeValId (int).
     */
    public final int getDoubleRangeValId() {
        return doubleRangeValId;
    }  

    /**
     * Allows the unique identification number to be changed. WARNING: Only use this method to set a custom identification number if you are absolutely certain that
     * these numbers will remain unique for every instance of this Validator object that is created. 
     * @param doubleRangeValId (int)
     * @throws IllegalArgumentException 
     */
    public final void setDoubleRangeValId(int doubleRangeValId) throws IllegalArgumentException{
        if(doubleRangeValId < 0){
            throw new IllegalArgumentException("The unique identifier for this Validator object cannot be less than 0.");
        } else {
            this.doubleRangeValId = doubleRangeValId;
        }
      
    }

    /**
     * A String description of this type of Validator object.
     * @return a string description of this type of Validator. 
     */
    @Override
    public final String toString() {
        return "InclusiveNumericDoubleRangeValidator: IdNumber: " + getDoubleRangeValId() + " A Validator object that determines if the input (double) is between an upper and lower bound.";
    }

    /**
     * Returns a hashcode value for this Validator from its encoded form.
     * @return hash (int).
     */
    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.doubleRangeValId;
        return hash;
    }

    /**
     * Compares this Validator for equality with the specified object.
     * @param obj (Object)
     * @return True or False (boolean)
     */
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
        final InclusiveNumericDoubleRangeValidator other = (InclusiveNumericDoubleRangeValidator) obj;
        if (this.doubleRangeValId != other.doubleRangeValId) {
            return false;
        }
        return true;
    }
    
    

}
    
    
    
    

