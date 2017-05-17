package cms.parkinggarageapp;

import java.util.Objects;

/**
 *
 * @author cscherbert1
 */
public class BusinessReport {
    
    private int businessReportId = 0;
    private double totalHoursParked;
    private double totalFeesCharged;
    private String businessName;
    
    public BusinessReport(){
        businessReportId++;
        totalHoursParked = 0;
        totalFeesCharged = 0;
    }
    
    public final String getFormattedBusinessReport(){
        String report = "";
        report += "Report for: " + getBusinessName() + "\n";
        report += "Total Hours Parked:        Total Fees Charged: \n";
        report += "-------------------------------------------------- \n";
        
        report += "   " + getTotalHoursParked() + "                        " + getTotalFeesCharged(); 
        return report;
    }

    public final double getTotalHoursParked() {
        return totalHoursParked;
    }

    public final void setTotalHoursParked(double totalHoursParked) throws IllegalArgumentException{
        if (totalHoursParked < 0){
            throw new IllegalArgumentException("Invalid number submitted. totalHoursParked cannot be less than 0.");
        } else {
            this.totalHoursParked = totalHoursParked;            
        }

    }
    
    public final void updateTotalHoursParked(double hours)throws IllegalArgumentException{
        if(hours < 0){
            throw new IllegalArgumentException("Cannot update total hours parked. hours cannot be less than 0.");
        } else {
            totalHoursParked += hours;            
        }

    }

    public final double getTotalFeesCharged() {
        return totalFeesCharged;
    }

    public final void setTotalFeesCharged(double totalFeesCharged)throws IllegalArgumentException {
        if (totalFeesCharged < 0){
            throw new IllegalArgumentException("Invalid number submitted. totalFeesCharged cannot be less than 0.");
        } else {
            this.totalFeesCharged = totalFeesCharged;
        }

    }
    
    public final void updateTotalFeesCharged(double fee)throws IllegalArgumentException{
        if (fee < 0){
            throw new IllegalArgumentException("Cannot update total fees charged. fee  cannot be less than 0.");
        } else {
            totalFeesCharged += fee;
        }
    }

    public final String getBusinessName() {
        return businessName;
    }

    public final void setBusinessName(String businessName) throws IllegalArgumentException{
        if (businessName == null || businessName.isEmpty()){
            throw new IllegalArgumentException("A business name must be provided. businessName cannot be null.");
        } else {
            this.businessName = businessName;           
        }

    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.businessReportId;
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
        final BusinessReport other = (BusinessReport) obj;
        if (this.businessReportId != other.businessReportId) {
            return false;
        }
        return true;
    }
    

    @Override
    public final String toString() {
        return "BusinessReport{" + "totalHoursParked=" + totalHoursParked + ", totalFeesCharged=" + totalFeesCharged + ", businessName=" + businessName + '}';
    }
    
    
 
}
