package cms.parkinggarageapp;

/**
 *This class maintains all information relevant to a specific business. 
 * 
 * @author cscherbert1
 */
public class Business {
    
    private int businessId;
    private String businessName;


    public final String getBusinessName() {
        return businessName;
    }

    public final void setBusinessName(String businessName) throws IllegalArgumentException{
        if (businessName == null || businessName.isEmpty()){
            throw new IllegalArgumentException("A Business Name must be provided. businessName cannot be null.");
        } else {
            this.businessName = businessName;            
        }

    }  

    public final int getBusinessId() {
        return businessId;
    }

    public final void setBusinessId(int businessId) throws IllegalArgumentException{
        if (businessId < 0){
            throw new IllegalArgumentException("The businessId must be greater than zero.");
        } else {
            this.businessId = businessId;
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.businessId;
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
        final Business other = (Business) obj;
        if (this.businessId != other.businessId) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "Business{" + "businessName=" + businessName + "businessId=" + businessId +'}';
    }
    
    
}
