package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Grant
 */
public class Vehicle {
    // Fields
    private String vin;
    private int mileage;
    private int salePrice;
    private int msrp;
    private int carYear;
    private String carDescription;
    private String pictureUrl;
    private Model model;
    private BodyStyle style;
    private Transmission transmission;
    private Color color;
    private Color interior;
    private Type type;
    private Status status;
    private Make make;
    private int userId;
    private boolean isFeatured;

    // Setters
    public void setVin(String vin) {
        this.vin = vin;
    }
    
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    
    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
    
    public void setMsrp(int msrp) {
        this.msrp = msrp;
    }
    
    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }
    
    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    
    public void setModel(Model model){
        this.model = model;
    }
    
    public void setStyle(BodyStyle style) {
        this.style = style;
    }
    
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMake(Make make) {
        this.make = make;
    }
    
    public void setInterior(Color interior) {
        this.interior = interior;
    }
    
    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }
    
    
    
    // Getters
    public String getVin() {
        return vin;
    }

    public int getMileage() {
        return mileage;
    }  

    public int getSalePrice() {
        return salePrice;
    }
    
    public int getMsrp() {
        return msrp;
    }

    public int getCarYear() {
        return carYear;
    }

    public String getCarDescription() {
        return carDescription;
    }   

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Model getModel() {
        return model;
    }   

    public BodyStyle getStyle() {
        return style;
    }   

    public Transmission getTransmission() {
        return transmission;
    }    

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }
     
    public Make getMake() {
        return make;
    }

    public Color getInterior() {
        return interior;
    }

    public boolean getIsFeatured() {
        return isFeatured;
    }

        
    // HashCode Override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.vin);
        hash = 79 * hash + this.mileage;
        hash = 79 * hash + this.salePrice;
        hash = 79 * hash + this.msrp;
        hash = 79 * hash + this.carYear;
        hash = 79 * hash + Objects.hashCode(this.carDescription);
        hash = 79 * hash + Objects.hashCode(this.pictureUrl);
        hash = 79 * hash + Objects.hashCode(this.model);
        hash = 79 * hash + Objects.hashCode(this.style);
        hash = 79 * hash + Objects.hashCode(this.transmission);
        hash = 79 * hash + Objects.hashCode(this.color);
        hash = 79 * hash + Objects.hashCode(this.interior);
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.make);
        hash = 79 * hash + this.userId;
        hash = 79 * hash + (this.isFeatured ? 1 : 0);
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
        final Vehicle other = (Vehicle) obj;
        if (this.mileage != other.mileage) {
            return false;
        }
        if (this.salePrice != other.salePrice) {
            return false;
        }
        if (this.msrp != other.msrp) {
            return false;
        }
        if (this.carYear != other.carYear) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.isFeatured != other.isFeatured) {
            return false;
        }
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        if (!Objects.equals(this.carDescription, other.carDescription)) {
            return false;
        }
        if (!Objects.equals(this.pictureUrl, other.pictureUrl)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.style, other.style)) {
            return false;
        }
        if (!Objects.equals(this.transmission, other.transmission)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.interior, other.interior)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return Objects.equals(this.make, other.make);
    }
    
    
    
}
