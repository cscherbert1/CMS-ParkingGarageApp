package cms.parkinggarageapp;

/**
 *
 * @author cscherbert1
 */
public class ConsoleReceiptOutput implements OutputStrategy {
      private Validator nullOrEmptyString;
      private static int consoleReceiptOutputNumber = 0;
      private int consoleReceiptOutputId;
    
    public ConsoleReceiptOutput(Validator nullOrEmptyString){
        consoleReceiptOutputNumber++;
        setConsoleReceiptOutputId(consoleReceiptOutputNumber);
        setNullOrEmptyStringValidator(nullOrEmptyString);
    }

    @Override
    public final void generateReceipt(String receiptData) throws IllegalArgumentException {
        Object[] inputItems = {new String(receiptData)};
        if (!nullOrEmptyString.isValid(inputItems)){
            throw new IllegalArgumentException("Receipt Data must be provided in order to generate receipt. receiptData cannot be null.");
        } else {
            System.out.println(receiptData);   
        }

    }

    private void setNullOrEmptyStringValidator(Validator nullOrEmptyString) throws IllegalArgumentException {
        if (nullOrEmptyString == null) {
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.nullOrEmptyString = nullOrEmptyString;
        }
    }

    public final int getConsoleReceiptOutputId() {
        return consoleReceiptOutputId;
    }

    public final void setConsoleReceiptOutputId(int consoleReceiptOutputId) throws IllegalArgumentException{
        if(consoleReceiptOutputId < 0){
            throw new IllegalArgumentException("The unique identification number for this object must be greater than 0.");
        } else {
            this.consoleReceiptOutputId = consoleReceiptOutputId;   
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.consoleReceiptOutputId;
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
        final ConsoleReceiptOutput other = (ConsoleReceiptOutput) obj;
        if (this.consoleReceiptOutputId != other.consoleReceiptOutputId) {
            return false;
        }
        return true;
    }

    
    @Override
    public final String toString() {
        return "ConsoleReceiptOutput: outputs receipt objects to the console.";
    }
    
    
    
}
