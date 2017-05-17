package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class NullOrEmptyStringValidator implements Validator{

    private static int nullEmptyStringNumber = 0;
    private int nullEmptyStringValId;
    
    public NullOrEmptyStringValidator(){
        nullEmptyStringNumber++;
        setNullEmptyStringValId(nullEmptyStringNumber);
        
    }
    @Override
    public final boolean isValid(Object[] inputItems) {
        String input = (String)inputItems[0];
        if(input == null || input.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public final int getNullEmptyStringValId() {
        return nullEmptyStringValId;
    }

    public final void setNullEmptyStringValId(int nullEmptyStringValId) throws IllegalArgumentException{
        if (nullEmptyStringValId < 0){
            throw new IllegalArgumentException("The unique identification number for this Validator object must be greater than 0.");
        } else {
            this.nullEmptyStringValId = nullEmptyStringValId;
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.nullEmptyStringValId;
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
        final NullOrEmptyStringValidator other = (NullOrEmptyStringValidator) obj;
        if (this.nullEmptyStringValId != other.nullEmptyStringValId) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString() {
        return "NullOrEmptyStringValidator: A Validator object";
    }
    
    
    
}
