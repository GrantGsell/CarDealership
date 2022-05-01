package cardealership.dto;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Grant
 */
public class Make {
    // Fields
    int makeId;
    String nameMake;
    LocalDate date;
    int userId;
    
    
    // Setters
    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }
    
    public void setNameMake(String nameMake) {
        this.nameMake = nameMake;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    // Getters
    public int getMakeId() {
        return makeId;
    }

    public String getNameMake() {
        return nameMake;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    
    // HashCode Override
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.makeId;
        hash = 29 * hash + Objects.hashCode(this.nameMake);
        hash = 29 * hash + Objects.hashCode(this.date);
        hash = 29 * hash + this.userId;
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
        final Make other = (Make) obj;
        if (this.makeId != other.makeId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.nameMake, other.nameMake)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

 
}
