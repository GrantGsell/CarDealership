package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Grant
 */
public class BodyStyle {
    // Fields
    private int styleId;
    private String nameStyle;
    private String description;

    
    // Setters
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }
    
    public void setNameStyle(String nameStyle) {
        this.nameStyle = nameStyle;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    // Getters
    public int getStyleId() {
        return styleId;
    }

    public String getNameStyle() {
        return nameStyle;
    }   

    public String getDescription() {
        return description;
    }

    
    // HashCode override
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.styleId;
        hash = 29 * hash + Objects.hashCode(this.nameStyle);
        hash = 29 * hash + Objects.hashCode(this.description);
        return hash;
    }

    
    // Equal Override
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
        final BodyStyle other = (BodyStyle) obj;
        if (this.styleId != other.styleId) {
            return false;
        }
        if (!Objects.equals(this.nameStyle, other.nameStyle)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }
    
    
}
