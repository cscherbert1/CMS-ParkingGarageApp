package cms.parkinggarageapp;

import java.time.LocalDateTime;

/**
 *
 * @author cscherbert1
 */
public class CheckInTerminal {
    
    private int checkInTerminalId = 0;
    private double ZERO = 0;
    private double maximumTimeParked = 24.0;
    private Validator doubleRange;
    private Validator nullObj;
    
    
    public CheckInTerminal(Validator doubleRange, Validator nullObj){
        checkInTerminalId++;
        setDoubleRangeValidator(doubleRange);
        setNullObjValidator(nullObj);
    }
    
    public final Ticket generateTicket(long checkInModifier, Business business)throws IllegalArgumentException{
        
//        Object[] inputDoubleItems = {new Double(hoursParked), new Double(ZERO), new Double(maximumTimeParked)};
        Object[] inputObjItems = {business};
        if (!nullObj.isValid(inputObjItems)){
            throw new IllegalArgumentException("A required object was found to be null. Ensure all input is configured properly.");
        } else if (checkInModifier < 0) {
            throw new IllegalArgumentException("The checkInModifier must be greater than 0.");
        }else {
            LocalDateTime checkInTime = LocalDateTime.now().minusMinutes(checkInModifier);
            Ticket ticket = new Ticket(checkInTime, business);
            return ticket;
        }
        
    }
    
    private void setDoubleRangeValidator(Validator doubleRange) throws IllegalArgumentException{
        if (doubleRange == null){
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.doubleRange = doubleRange;
        }
    } 
    
    private void setNullObjValidator(Validator nullObj) throws IllegalArgumentException {
        if (nullObj == null) {
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.nullObj = nullObj;
        }
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.checkInTerminalId;
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
        final CheckInTerminal other = (CheckInTerminal) obj;
        if (this.checkInTerminalId != other.checkInTerminalId) {
            return false;
        }
        return true;
    }

    
    @Override
    public final String toString() {
        return "CheckInTerminal: Distributes tickets for each vehicle that checks in.";
    }
    
    
    
}
