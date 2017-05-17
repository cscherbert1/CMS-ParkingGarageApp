package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class MandatoryLocalDateTimeException extends IllegalArgumentException{
    
    //custom message. we want this to be used every time. 
    private final static String msg = "A LocalDateTime object is required. Method argument expecting this type of object cannot be null.";
    private static int mandatoryDateTimeExceptionNumber = 0;
    private int mandatoryDateTimeExceptionId;
    /*
        must have every constructor from the superclass exception
    */
    public MandatoryLocalDateTimeException() {
        //msg must be static in order for it to belong to the class, and not the instance. if not static, the following line will fail. 
        super(msg);
        mandatoryDateTimeExceptionNumber++;
        setMandatoryDateTimeExceptionId(mandatoryDateTimeExceptionNumber);
        
    }

    public MandatoryLocalDateTimeException(String s) {
        //dont allow programmers to set their own message. Always use ours. 
        super(msg);
    }

    public MandatoryLocalDateTimeException(String message, Throwable cause) {
        super(msg, cause);
    }

    public MandatoryLocalDateTimeException(Throwable cause) {
        super(cause);
    }

    public final int getMandatoryDateTimeExceptionId() {
        return mandatoryDateTimeExceptionId;
    }

    public final void setMandatoryDateTimeExceptionId(int mandatoryDateTimeExceptionId) throws IllegalArgumentException {
        this.mandatoryDateTimeExceptionId = mandatoryDateTimeExceptionId;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.mandatoryDateTimeExceptionId;
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
        final MandatoryLocalDateTimeException other = (MandatoryLocalDateTimeException) obj;
        if (this.mandatoryDateTimeExceptionId != other.mandatoryDateTimeExceptionId) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "MandatoryLocalDateTimeException{" + "mandatoryDateTimeExceptionId=" + mandatoryDateTimeExceptionId + '}';
    }
 
}
