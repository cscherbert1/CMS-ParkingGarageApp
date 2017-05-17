package cms.parkinggarageapp;

import java.time.LocalDateTime;

/**
 *
 * @author cscherbert1
 */
public class Ticket {
    
//    private double hoursParked;
    private static int ticketNumber = 0;
    private int ticketId;
    private LocalDateTime checkInTime;
    private Business business;
    
    public Ticket(LocalDateTime checkInTime, Business business){
        ticketNumber++;
        setTicketId(ticketNumber);
//        setHoursParked(hoursParked);
        setCheckInTime(checkInTime);
        setBusiness(business);
    }
    
    public final String getBusinessName(){
        return business.getBusinessName();
    }

    public final LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public final void setCheckInTime(LocalDateTime checkInTime) throws MandatoryLocalDateTimeException{
        if(checkInTime == null){
            throw new MandatoryLocalDateTimeException();
        } 
        this.checkInTime = checkInTime;
    }
    
    public final Business getBusiness() {
        return business;
    }

    public final void setBusiness(Business business) throws IllegalArgumentException {
        if (business == null){
           throw new IllegalArgumentException("A business must be used to create a Ticket. business cannot be null."); 
        }
        this.business = business;
    } 

    public final int getTicketId() {
        return ticketId;
    }

    public final void setTicketId(int ticketId) throws IllegalArgumentException{
        if(ticketId < 0){
            throw new IllegalArgumentException("The unique identification number ticketId cannot be 0.");
        } else {
            this.ticketId = ticketId;   
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.ticketId;
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
        final Ticket other = (Ticket) obj;
        if (this.ticketId != other.ticketId) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString() {
        return "Ticket{" + "checkInTime=" + checkInTime + ", business=" + business + '}';
    }

}
