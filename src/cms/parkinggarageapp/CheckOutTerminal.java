package cms.parkinggarageapp;

import date_timeutilities.Date_TimeUtilities;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author cscherbert1
 */
public class CheckOutTerminal {

    private int checkOutTerminalId = 0;
    private Receipt receipt;
    private OutputStrategy guiOutput;
    private OutputStrategy consoleOutput;
    private int transactionNumber;
    private ReceiptFormatter formatter;
    private FileService fileService;
    private BusinessReport report;
    private Validator nullObj;
    private Validator nullOrEmptyString;
    private Validator doubleRange;
    private Validator intLarger;
    private final double MINUTESPERHOUR = 60.00;
    private ExceptionOutput exOut = new ExceptionOutput();

    public CheckOutTerminal(OutputStrategy guiOutput, OutputStrategy consoleOutput,
            ReceiptFormatter formatter, Validator nullObj, Validator nullOrEmptyString,
            Validator intLarger, Validator doubleRange) {
        checkOutTerminalId++;
        setValidators(nullObj, nullOrEmptyString, intLarger, doubleRange);
        setGuiOutput(guiOutput);
        setConsoleOutput(consoleOutput);
        setTransactionNumber(0);
        setFormatter(formatter);
        transactionNumber = 0;
        configureNewBusinessReport();
    }

    public final void processTicket(Ticket ticket, FeeCalculator calculator){
        Object[] inputTicket = {ticket};
        Object[] inputCalc = {calculator};
        try {
            if (!nullObj.isValid(inputTicket)) {
                handleExceptions(new IllegalArgumentException("A ticket object must be provided. It cannot be null"));
            } else if (!nullObj.isValid(inputCalc)) {
                handleExceptions(new IllegalArgumentException("A fee calculator is required. calculator cannot be null."));
            } else {
                //if this is the first ticket to be passed, get the business name for the report
                if (transactionNumber == 0) {
                    provideBusinessNameToReport(ticket.getBusinessName());
                }
                transactionNumber++;

                double totalMinutesParked = calculateMinutesParked(ticket.getCheckInTime(), LocalDateTime.now());
                double hoursParked = totalMinutesParked/MINUTESPERHOUR;

                //create new receipt and pass in hours parked,etc.
                receipt = new Receipt(calculateHoursMins(totalMinutesParked), formatter, ticket.getBusinessName(), calculator, transactionNumber,
                        nullOrEmptyString, nullObj, doubleRange, intLarger);

                //use the information on the ticket to also update the totals for the business report
                report.updateTotalHoursParked(hoursParked);
                report.updateTotalFeesCharged(receipt.getFeeOwed());
                // update the file with the new totals
                try {
                    fileService.writeTextFile(report.getTotalHoursParked(), report.getTotalFeesCharged());
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        } catch (IllegalArgumentException iae) {
            handleExceptions(iae);
        }

    }
    
    private final double[] calculateHoursMins(double totalMinutesParked) {
        if(totalMinutesParked < 0){
            handleExceptions(new IllegalArgumentException("totalMinutesParked must be greater than 0."));
        }
            double hoursParked = Math.floor((totalMinutesParked / MINUTESPERHOUR));
            double minutesParked = (totalMinutesParked % MINUTESPERHOUR);

            double[] hoursMinsArray = {hoursParked, minutesParked};
            return hoursMinsArray;
    }

    public final double calculateMinutesParked(LocalDateTime checkInTime, LocalDateTime checkOutTime) {       
        Date_TimeUtilities dtu = new Date_TimeUtilities();
        double totalMinutesParked = (double) dtu.getChronoUnitsBetweenTwoDates(checkInTime, checkOutTime, MINUTES);
        return totalMinutesParked;
    }

    public final void generateReceipts() {
        //output receipt data to LED (GUI)
        guiOutput.generateReceipt(receipt.getReceiptData(transactionNumber, formatter));
        //output receipt data to printed receipt (Console)
        consoleOutput.generateReceipt(receipt.getReceiptData(transactionNumber, formatter));
    }

    public final void generateBusinessReport() {
        consoleOutput.generateReceipt(report.getFormattedBusinessReport());
    }

    public final void provideBusinessNameToReport(String businessName){
        Object[] inputBusinessName = {new String(businessName)};
        if (!nullOrEmptyString.isValid(inputBusinessName)) {
            handleExceptions(new IllegalArgumentException("A businessName must be provided. businessName cannot be null."));
        } else {
            report.setBusinessName(businessName);
        }
    }

    private void setValidators(Validator nullObj, Validator nullOrEmptyString,
            Validator intLarger, Validator doubleRange) {
        setNullObjValidator(nullObj);
        setNullOrEmptyStringValidator(nullOrEmptyString);
        setDoubleRangeValidator(doubleRange);
        setIntGreaterThanValValidator(intLarger);
    }

    private void configureNewBusinessReport() {
        report = new BusinessReport();
        fileService = new FileService();
        try {
            List fileItems = fileService.readTextFile();
            report.updateTotalHoursParked(Double.parseDouble((String) fileItems.get(0)));
            report.updateTotalFeesCharged(Double.parseDouble((String) fileItems.get(1)));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

    }

    public final void setGuiOutput(OutputStrategy guiOutput){
        Object[] inputGuiOutput = {guiOutput};
        if (!nullObj.isValid(inputGuiOutput)) {
            handleExceptions(new IllegalArgumentException("An Output Strategy is necessary. guiOutput cannot be null. "));
        } else {
            this.guiOutput = guiOutput;
        }
    }

    public final void setConsoleOutput(OutputStrategy consoleOutput){
        Object[] inputConsoleOutput = {consoleOutput};
        if (!nullObj.isValid(inputConsoleOutput)) {
            handleExceptions(new IllegalArgumentException("An Output Strategy is necessary. consoleOutput cannot be null."));
        } else {
            this.consoleOutput = consoleOutput;
        }
    }

    public final int getTransactionNumber() {
        return transactionNumber;
    }

    public final void setTransactionNumber(int transactionNumber){
        Object[] inputIntItems = {new Integer(transactionNumber), new Integer(0)};
        if (!intLarger.isValid(inputIntItems)) {
            handleExceptions(new IllegalArgumentException("transactionNumber cannot be less than 0."));
        } else {
            this.transactionNumber = transactionNumber;
        }
    }

    public final void setFormatter(ReceiptFormatter formatter) {
        Object[] inputFormatterItems = {formatter};
        if (!nullObj.isValid(inputFormatterItems)) {
            handleExceptions(new IllegalArgumentException("A Receipt Formatter is necessary. formatter cannot be null. "));
        } else {
            this.formatter = formatter;
        }
    }

    private void setNullObjValidator(Validator nullObj) {
        if (nullObj == null) {
            handleExceptions(new IllegalArgumentException("Validators cannot be null."));
        } else {
            this.nullObj = nullObj;
        }
    }

    private void setNullOrEmptyStringValidator(Validator nullOrEmptyString) {
        if (nullOrEmptyString == null) {
            handleExceptions(new IllegalArgumentException("Validators cannot be null."));
        } else {
            this.nullOrEmptyString = nullOrEmptyString;
        }
    }

    private final void setDoubleRangeValidator(Validator doubleRange){
        if (doubleRange == null) {
            handleExceptions(new IllegalArgumentException("Validators cannot be null."));
        } else {
            this.doubleRange = doubleRange;
        }
    }

    private final void setIntGreaterThanValValidator(Validator intLarger){
        if (intLarger == null) {
            handleExceptions (new IllegalArgumentException("Validators cannot be null."));
        } else {
            this.intLarger = intLarger;
        }
    }
        
    public final void handleExceptions(Exception ex){
        exOut.outputExceptionsToConsole(ex);
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.checkOutTerminalId;
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
        final CheckOutTerminal other = (CheckOutTerminal) obj;
        if (this.checkOutTerminalId != other.checkOutTerminalId) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "CheckOutTerminal: Checks out each vehicle. Generates receipts and business reports. checkOutTerminalId: " + checkOutTerminalId;
    }

}
