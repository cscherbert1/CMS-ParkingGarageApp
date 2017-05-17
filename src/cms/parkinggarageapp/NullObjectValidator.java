package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class NullObjectValidator implements Validator{
    
    private static int nullObjValNumber = 0;
    private int nullObjValId;
    
    public NullObjectValidator(){
        nullObjValNumber++;
        setNullObjValId(nullObjValNumber);
        
    }

    @Override
    public final boolean isValid(Object[] inputItems) {
        Object input = (Object)inputItems[0];
        if(input == null){
            return false;
        } else {
            return true;
        }  
    }

    public final int getNullObjValId() {
        return nullObjValId;
    }

    public final void setNullObjValId(int nullObjValId) throws IllegalArgumentException {
        if(nullObjValId < 0){
            throw new IllegalArgumentException("The unique identification number for this Validator must be greater than 0.");            
        } else {
            this.nullObjValId = nullObjValId;
        } 
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.nullObjValId;
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
        final NullObjectValidator other = (NullObjectValidator) obj;
        if (this.nullObjValId != other.nullObjValId) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "NullObjectValidator: A Validator that determines whether passed in objects are null or not.";
    }
    
    
    
}
