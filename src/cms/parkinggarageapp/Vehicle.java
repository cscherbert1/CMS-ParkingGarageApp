package cms.parkinggarageapp;

import java.time.LocalDateTime;

/**
 *
 * @author cscherbert1
 */
public class Vehicle {
    
    private int vehicleId;
    private Ticket ticket;
    private CheckInTerminal checkIn;
    private CheckOutTerminal checkOut;
    private Validator nullObj;
    private Validator dblLarger;
    
    public Vehicle(int vehicleId, CheckInTerminal checkIn, CheckOutTerminal checkOut, Validator nullObj, Validator dblLarger){
        setNullObjValidator(nullObj);
        setDblLargerValidator(dblLarger);
        setVehicleId(vehicleId);
        setCheckIn(checkIn);
        setCheckOut(checkOut);
        
    }
    
    public final void checkInVehicle(long checkInModifier, Business business) throws IllegalArgumentException{
        Object[] inputObjItems = {business};
        if (!nullObj.isValid(inputObjItems)){
            throw new IllegalArgumentException("A required object was found to be null. Ensure all input is configured properly.");
        } else if (checkInModifier < 0){ 
            throw new IllegalArgumentException("The checkInModifier must be greater than 0.");
        }else {
            setTicket(checkIn.generateTicket(checkInModifier, business));  
        }
    }
    
    public final void checkOutVehicle(FeeCalculator calculator) throws IllegalArgumentException{
        Object[] inputItems = {calculator};
        if(!nullObj.isValid(inputItems)){
            throw new IllegalArgumentException("A calculator is required. calculator cannot be null.");
        } else {
            checkOut.processTicket(ticket, calculator);
            checkOut.generateReceipts();
            checkOut.generateBusinessReport();
        }

    }

    public final Ticket getTicket() {
        return ticket;
    }

    public final void setTicket(Ticket ticket) {
        Object[] inputItems = {ticket};
        if(!nullObj.isValid(inputItems)){
            throw new IllegalArgumentException("A ticket is required. ticket cannot be null.");
        } else {
            this.ticket = ticket;
        }

    }

    public final int getVehicleId() {
        return vehicleId;
    }

    public final void setVehicleId(int vehicleId) throws IllegalArgumentException{
        if(vehicleId < 0){
            throw new IllegalArgumentException("The unique identification number for Vehicles must be greater than 0.");
        } else {
            this.vehicleId = vehicleId;
        }
        
    }

    public final CheckInTerminal getCheckIn() {
        return checkIn;
    }

    public final void setCheckIn(CheckInTerminal checkIn) {
        Object[] inputItems = {checkIn};
        if (!nullObj.isValid(inputItems)) {
            throw new IllegalArgumentException("A Check In Terminal is required. checkIn cannot be null.");
        } else {
            this.checkIn = checkIn;
        }

    }

    public final CheckOutTerminal getCheckOut() {
        return checkOut;
    }

    public final void setCheckOut(CheckOutTerminal checkOut) {
        Object[] inputItems = {checkOut};
        if (!nullObj.isValid(inputItems)) {
            throw new IllegalArgumentException("A Check Out Terminal is required. checkOut cannot be null.");
        } else {
            this.checkOut = checkOut;
        }
    }

    private void setNullObjValidator(Validator nullObj) throws IllegalArgumentException{
        if (nullObj == null){
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
           this.nullObj = nullObj; 
        }

    }
    
    private void setDblLargerValidator(Validator dblLarger) throws IllegalArgumentException{
        if (dblLarger == null){
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.dblLarger = dblLarger;
        }
    } 

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.vehicleId;
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
        final Vehicle other = (Vehicle) obj;
        if (this.vehicleId != other.vehicleId) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "Vehicle: Holds a Ticket.{" + "ticket=" + ticket + '}';
    }
    
    
}
