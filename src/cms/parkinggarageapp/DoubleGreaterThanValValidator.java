package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class DoubleGreaterThanValValidator implements Validator {
    
    private static int greaterThanValNumber = 0;
    private int greaterThanValId;
    
    public DoubleGreaterThanValValidator(){
        greaterThanValNumber++;
        setGreaterThanValId(greaterThanValNumber);
    }

    @Override
    public final boolean isValid(Object[] inputItems) {
        double input = (Double)inputItems[0];
        double value = (Double)inputItems[1];
        
        if(input < value){
            return false;
        } else {
            return true;
        }              
    } 

    public final int getGreaterThanValId() {
        return greaterThanValId;
    }

    public final void setGreaterThanValId(int greaterThanValId) throws IllegalArgumentException{
        if(greaterThanValId < 0){
            throw new IllegalArgumentException("The unique identification number for this object must be greater than 0.");
        } else {
            this.greaterThanValId = greaterThanValId;            
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.greaterThanValId;
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
        final DoubleGreaterThanValValidator other = (DoubleGreaterThanValValidator) obj;
        if (this.greaterThanValId != other.greaterThanValId) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public final String toString() {
        return "DoubleGreaterThanValValidator: A Validator object";
    }
    
    
}
