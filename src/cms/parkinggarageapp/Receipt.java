package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class Receipt {
    private double hoursParked;
    private double minutesParked;
    private FeeCalculator calculator;
    private String businessName;
    private int transactionNumber;
    private Validator nullOrEmptyString;
    private Validator nullObj;
    private Validator doubleRange;
    private Validator intLarger;

    
    public Receipt(double[] hoursMinsArray, ReceiptFormatter formatter, String businessName, FeeCalculator calculator, int transactionNumber,
            Validator nullOrEmptyString, Validator nullObject, Validator doubleRange, Validator intLarger){
        
        setNullOrEmptyStringValidator(nullOrEmptyString);
        setNullObjValidator(nullObject);
        setDoubleRangeValidator(doubleRange);
        setIntGreaterThanValValidator(intLarger);
        setHoursMinsParked(hoursMinsArray); 
        setBusinessName(businessName);
        setFeeCalculator(calculator);
        setTransactionNumber(transactionNumber);

    }
    
    public final String getReceiptData(int transactionNumber, ReceiptFormatter formatter) throws IllegalArgumentException{
        if (transactionNumber < 0) {
            throw new IllegalArgumentException("Transaction Number cannot be less than 0");
        } else if (formatter == null) {
            throw new IllegalArgumentException("A Receipt Formatter must be provided. formatter cannot be null.");
        } else {
            
            return formatter.getFormattedData(this);
        }
    }
    
    public final String getColumnHeadings() {
        String columnHeading = "";
        columnHeading += "Time Parked                            Fee Owed\n";
        columnHeading += "----------------------------------------------------------\n";
        return columnHeading;
    }


    public final double getHoursParked() {
        return hoursParked;
    }
    
    public final void setHoursParked(double hoursParked)throws IllegalArgumentException{
        if(hoursParked < 0 || hoursParked > 24){
            throw new IllegalArgumentException("Hours Parked cannot be less than 0 or greater than 24 hours.");
        } else {
            this.hoursParked = hoursParked;
        }
    }

    public final void setHoursMinsParked(double[] hoursMinsArray) throws IllegalArgumentException {
        if (hoursMinsArray == null) {
            throw new IllegalArgumentException("Input for time parked was not provided. It cannot be null.");
        }
        setHoursParked(hoursMinsArray[0]);
        setMinutesParked(hoursMinsArray[1]);

    }
    
    public final double getFeeOwed(){
        
        return calculator.calculateFeeOwed(hoursParked);
    }

    public final void setFeeCalculator(FeeCalculator calculator) throws IllegalArgumentException  {
        if (calculator == null){
            throw new IllegalArgumentException("A fee calcualtor is required. calculator cannot be null.");
        }
        this.calculator = calculator;
    }

    public final String getBusinessName() {
        return businessName;
    }

    public final void setBusinessName(String businessName) {
        if(businessName == null || businessName.isEmpty()){
            throw new IllegalArgumentException("A Business name is required. businessName cannot be null or empty.");
        } else {
            this.businessName = businessName;    
        }

    }

    public final int getTransactionNumber() {
        return transactionNumber;
    }

    public final void setTransactionNumber(int transactionNumber) throws IllegalArgumentException{
        if (transactionNumber < 0){
            throw new IllegalArgumentException("Number of transactions cannot be less than 0.");
        } else {
            this.transactionNumber = transactionNumber;            
        }

    }

    public final double getMinutesParked() {
        return minutesParked;
    }

    public final void setMinutesParked(double minutesParked) throws IllegalArgumentException{
        if (minutesParked <0 ){
            throw new IllegalArgumentException("minutesParked cannot be less than 0.");
        } else {
            this.minutesParked = minutesParked;   
        }
       
    }
    
        
    private void setNullObjValidator(Validator nullObj) throws IllegalArgumentException {
        if (nullObj == null) {
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.nullObj = nullObj;
        }
    }
    
    private void setNullOrEmptyStringValidator(Validator nullOrEmptyString) throws IllegalArgumentException{
        if (nullOrEmptyString == null){
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.nullOrEmptyString = nullOrEmptyString;
        }
    }
    
    private void setIntGreaterThanValValidator(Validator intLarger) throws IllegalArgumentException{
        if (intLarger == null){
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.intLarger = intLarger;
        }
    }
    
    private void setDoubleRangeValidator(Validator doubleRange) throws IllegalArgumentException{
        if (doubleRange == null){
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.doubleRange = doubleRange;
        }
    } 

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.transactionNumber;
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
        final Receipt other = (Receipt) obj;
        if (this.transactionNumber != other.transactionNumber) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "Receipt{" + "hoursParked=" + hoursParked + ", businessName=" + businessName + ", transactionNumber=" + transactionNumber + '}';
    }
    
    
} 
