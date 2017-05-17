package cms.parkinggarageapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cscherbert1
 */
public class TicketTest {
    
    private Ticket instance;
    
    public TicketTest() {
    }
    
    @Before
    public void setUp() {
        Business garage = new Business();
        garage.setBusinessName("Hello");
        garage.setBusinessId(1001);
        LocalDateTime now = LocalDateTime.now();
        instance = new Ticket(now.minusMinutes(120), garage);
    }
    
    @After
    public void tearDown() {
        instance = null;
    }
    
    @Test (expected = MandatoryLocalDateTimeException.class)
    public void setCheckInTimeThrowsCustomerExceptionIfCheckInTimeIsNull(){
        Business garage = new Business();
        garage.setBusinessName("Hello");
        garage.setBusinessId(1001);
        instance = new Ticket(null, garage);
        
    }
    
    @Test
    public void setCheckInTimeWorksAsDesignedWithValidInput(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before = now.minusHours(23);
        instance.setCheckInTime(now);
        instance.setCheckInTime(before);
        
    }

    
}
