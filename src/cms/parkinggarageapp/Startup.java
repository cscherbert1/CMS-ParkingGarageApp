package cms.parkinggarageapp;


import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author cscherbert1
 */
public class Startup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Validator Strategy Objects
         */
        Validator nullOrEmptyString = new NullOrEmptyStringValidator();
        Validator nullObject = new NullObjectValidator();
        Validator doubleRange = new InclusiveNumericDoubleRangeValidator();
        Validator intLarger = new IntGreaterThanValValidator();
        Validator dblLarger = new DoubleGreaterThanValValidator();
        
        /*
         * Other Configuration
         *
         * Create Business with factory.
         */
        
        Business garage = ExternalSpecFactory.getBusinessInstance();
        garage.setBusinessName("Happy Days Garage");
        garage.setBusinessId(1001);
        
        
        OutputStrategy guiOutput = new GuiReceiptOutput(nullOrEmptyString);
        OutputStrategy consoleOutput = new ConsoleReceiptOutput(nullOrEmptyString);
        ReceiptFormatter formatter = new DetailedReceiptFormat(nullObject);
/*
 * 
 * Use the FeeCalculator Objects to determine which strategy is used to calculate the vehicle's fee based on how long they have parked. 
 * Change the object being used in the checkOut.processTicket() line for the desired vehicle.
 * 
 */
        FeeCalculator minMax = new MinimumMaximumFeeCalculator(doubleRange);
        FeeCalculator minNoMax = new MinimumNoMaximumFeeCalculator(doubleRange);


        CheckInTerminal checkIn = new CheckInTerminal(doubleRange, nullObject);
        CheckOutTerminal checkOut = new CheckOutTerminal(guiOutput, consoleOutput, formatter, nullObject, nullOrEmptyString, intLarger, doubleRange);
        
        //create date/time obj for now, then subtract minutes from it to simulate longer times parked for each vehicle. 
        LocalDateTime now = LocalDateTime.now();
        
        /*
         * Input
         * 
         * Vehicle 1 
         * 
         * To demonstrate flexibility between the Fee Calculator strategy objects, change between the two fee calculators that can be passed into checkOutVehicle
         */
        Vehicle vehicle1 = new Vehicle(1, checkIn, checkOut, nullObject, dblLarger);
        //when checking out, subtracting minutes should yield time parked of 2 hours
        //checkInModifier is the value used to create more realistic duration for time parked. 
        long checkInModifier1 = 120;
        vehicle1.checkInVehicle(checkInModifier1, garage);
        vehicle1.checkOutVehicle(minNoMax);
        
        /*
         * Vehicle 2
         */
        Vehicle vehicle2 = new Vehicle(2, checkIn, checkOut, nullObject, dblLarger);
         //when checking out, subtracting minutes should yield time parked of 10.25 hours
        long checkInModifier2 = 615;
        vehicle2.checkInVehicle(checkInModifier2, garage);
        vehicle2.checkOutVehicle(minNoMax);
     
        /*
         * Vehicle 3
         */
        Vehicle vehicle3 = new Vehicle(3, checkIn, checkOut, nullObject, dblLarger);
        //when checking out, subtracting minutes should yield time parked of 23.5 hours
        long checkInModifier3 = 1410;
        vehicle3.checkInVehicle(checkInModifier3, garage);
        vehicle3.checkOutVehicle(minNoMax);
        


    }
    
}
