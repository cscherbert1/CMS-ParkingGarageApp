package cms.parkinggarageapp;

import javax.swing.JOptionPane;

/**
 *
 * @author cscherbert1
 */
public class GuiReceiptOutput implements OutputStrategy {

    private Validator nullOrEmptyString;
    private static int guiReceiptOutputNumber = 0;
    private int guiReceiptOutputId;

    public GuiReceiptOutput(Validator nullOrEmptyString) {
        guiReceiptOutputNumber++;
        setGuiReceiptOutputId(guiReceiptOutputNumber);
        setNullOrEmptyStringValidator(nullOrEmptyString);
    }

    @Override
    public final void generateReceipt(String receiptData) throws IllegalArgumentException {
        Object[] inputItems = {new String(receiptData)};
        if (!nullOrEmptyString.isValid(inputItems)) {
            throw new IllegalArgumentException("Receipt Data must be provided in order to generate receipt. receiptData cannot be null.");
        } else {
            JOptionPane.showMessageDialog(null, receiptData);
        }
    }

    private void setNullOrEmptyStringValidator(Validator nullOrEmptyString) throws IllegalArgumentException {
        if (nullOrEmptyString == null) {
            throw new IllegalArgumentException("Validators cannot be null.");
        } else {
            this.nullOrEmptyString = nullOrEmptyString;
        }
    }

    public final int getGuiReceiptOutputId() {
        return guiReceiptOutputId;
    }

    public final void setGuiReceiptOutputId(int guiReceiptOutputId) throws IllegalArgumentException{
        if(guiReceiptOutputId < 0){
            throw new IllegalArgumentException("The unique identification number for this object must be greater than 0.");
        } else {
            this.guiReceiptOutputId = guiReceiptOutputId;
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.guiReceiptOutputId;
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
        final GuiReceiptOutput other = (GuiReceiptOutput) obj;
        if (this.guiReceiptOutputId != other.guiReceiptOutputId) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "GuiReceiptOutput: Responsible for outputting receipt objects to GUIs";
    }
    
    

}
