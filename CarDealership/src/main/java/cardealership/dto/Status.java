package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Grant
 */
public class Status {
    // Fields
    private int statusId;
    private String nameStatus;
    
    
    // Setters
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }
    
    
    // Getters
    public int getStatusId() {
        return statusId;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    
    // HashCode Override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.statusId;
        hash = 53 * hash + Objects.hashCode(this.nameStatus);
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
        final Status other = (Status) obj;
        if (this.statusId != other.statusId) {
            return false;
        }
        return Objects.equals(this.nameStatus, other.nameStatus);
    }
}