package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class DetailedReceiptFormat implements ReceiptFormatter {
    private Validator nullObj;
    private static int detailedReceiptFormatNumber = 0;
    private int detailtedReceiptFormatId;
    
    public DetailedReceiptFormat(Validator nullObj){
        detailedReceiptFormatNumber++;
        setDetailtedReceiptFormatId(detailedReceiptFormatNumber);
        setNullOjbValidator(nullObj);
    }
    
    @Override
    public final String getFormattedData(Receipt receipt) throws IllegalArgumentException{
         Object[] inputItems = {receipt};
        if (!nullObj.isValid(inputItems)){
            throw new IllegalArgumentException ("A receipt must be created before data can be retrieved. receipt cannot be null.");
        } else {

            String data = "";
            data += "Thank you for visiting: " + receipt.getBusinessName() + "\n";
            data += "Receipt Number: " + receipt.getTransactionNumber() + "\n\n";

            data += receipt.getColumnHeadings();

            data += " " + receipt.getHoursParked() + " hours, " + receipt.getMinutesParked() + " minutes.              " + receipt.getFeeOwed();
            data += "\n";

            return data;
        }
    }
    
     private void setNullOjbValidator(Validator nullObj) throws IllegalArgumentException {
        if (nullObj == null) {
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.nullObj = nullObj;
        }
    }

    public final int getDetailtedReceiptFormatId() {
        return detailtedReceiptFormatId;
    }

    public final void setDetailtedReceiptFormatId(int detailtedReceiptFormatId) throws IllegalArgumentException{
        if(detailtedReceiptFormatId< 0){
            throw new IllegalArgumentException("The unique identification number for this object must be greater than 0.");
        } else {
            this.detailtedReceiptFormatId = detailtedReceiptFormatId;
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.detailtedReceiptFormatId;
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
        final DetailedReceiptFormat other = (DetailedReceiptFormat) obj;
        if (this.detailtedReceiptFormatId != other.detailtedReceiptFormatId) {
            return false;
        }
        return true;
    }
     
    @Override
    public final String toString() {
        return "DetailedReceiptFormat: returns formatted receipt data for output.";
    }
     
     
}
