package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Grant
 */
public class Color {
    // Fields
    private int colorId;
    private String nameColor;
    
    
    // Setters
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
    
    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }    

    
    // Getters
    public int getColorId() {
        return colorId;
    }

    public String getNameColor() {
        return nameColor;
    }


    // HashCode Override
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.colorId;
        hash = 29 * hash + Objects.hashCode(this.nameColor);
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
        final Color other = (Color) obj;
        if (this.colorId != other.colorId) {
            return false;
        }
        return Objects.equals(this.nameColor, other.nameColor);
    }
    
    
    
    
}
