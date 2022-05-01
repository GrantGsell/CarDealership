package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Grant
 */
public class Transmission {
    private int transmissionId;
    private String transmissionName;

    
    // Setters
    public void setTransmissionId(int transmissionId) {
        this.transmissionId = transmissionId;
    }
    
    public void setTransmissionName(String transmissionName) {
        this.transmissionName = transmissionName;
    }
  
    
    // Getters
    public int getTransmissionId() {
        return transmissionId;
    }

    public String getTransmissionName() {
        return transmissionName;
    }

    
    // HashCode Override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.transmissionId;
        hash = 83 * hash + Objects.hashCode(this.transmissionName);
        return hash;
    }

    
    // Equals Override
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transmission other = (Transmission) obj;
        if (this.transmissionId != other.transmissionId) {
            return false;
        }
        return Objects.equals(this.transmissionName, other.transmissionName);
    }

    
    
    
    
}
