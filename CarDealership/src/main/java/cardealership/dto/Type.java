package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Grant
 */
public class Type {
    // Fields
    int typeId;
    String nameType;
    
    
    // Setters
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    
    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
    
    
    // Getters
    public int getTypeId() {
        return typeId;
    }

    public String getNameType() {
        return nameType;
    }

    
    // HaashCode Override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.typeId;
        hash = 29 * hash + Objects.hashCode(this.nameType);
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
        final Type other = (Type) obj;
        if (this.typeId != other.typeId) {
            return false;
        }
        return Objects.equals(this.nameType, other.nameType);
    }

    
    
}
