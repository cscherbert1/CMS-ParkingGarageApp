package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class IntGreaterThanValValidator implements Validator{
    
    private static int intGreaterThanValNumber = 0;
    private int intGreaterThanValId;
    
    public IntGreaterThanValValidator(){
        intGreaterThanValNumber++;
        setIntGreaterThanValId(intGreaterThanValNumber);
    }

    @Override
    public final boolean isValid(Object[] inputItems) {
        int input = (Integer)inputItems[0];
        int value = (Integer)inputItems[1];
        
        if(input < value){
            return false;
        } else {
            return true;
        }              
    }

    public final int getIntGreaterThanValId() {
        return intGreaterThanValId;
    }

    public final void setIntGreaterThanValId(int intGreaterThanValId) throws IllegalArgumentException{
        if(intGreaterThanValId < 0){
            throw new IllegalArgumentException("The unique identifier for this Validator object cannot be less than 0.");
        } else {
            this.intGreaterThanValId = intGreaterThanValId;
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.intGreaterThanValId;
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
        final IntGreaterThanValValidator other = (IntGreaterThanValValidator) obj;
        if (this.intGreaterThanValId != other.intGreaterThanValId) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "IntGreaterThanValValidator: A Validator object";
    }
    
    
    
}
