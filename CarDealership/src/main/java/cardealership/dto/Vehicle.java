package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Grant
 */
public class Vehicle {
    // Fields
    int vin;
    int mileage;
    int salePrice;
    int msrp;
    int carYear;
    String carDescription;
    String pictureUrl;
    int modelId;
    int styleId;
    int transmissionId;
    int colorId;
    int typeId;
    int statusId;
    int userId;

    // Setters
    public void setVin(int vin) {
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
    
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
    
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }
    
    public void setTransmissionId(int transmissionId) {
        this.transmissionId = transmissionId;
    }
    
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
    
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
    // Getters
    public int getVin() {
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

    public int getModelId() {
        return modelId;
    }   

    public int getStyleId() {
        return styleId;
    }   

    public int getTransmissionId() {
        return transmissionId;
    }    

    public int getColorId() {
        return colorId;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getUserId() {
        return userId;
    }

    
    // HashCode Override
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.vin;
        hash = 17 * hash + this.mileage;
        hash = 17 * hash + this.salePrice;
        hash = 17 * hash + this.msrp;
        hash = 17 * hash + this.carYear;
        hash = 17 * hash + Objects.hashCode(this.carDescription);
        hash = 17 * hash + Objects.hashCode(this.pictureUrl);
        hash = 17 * hash + this.modelId;
        hash = 17 * hash + this.styleId;
        hash = 17 * hash + this.transmissionId;
        hash = 17 * hash + this.colorId;
        hash = 17 * hash + this.typeId;
        hash = 17 * hash + this.statusId;
        hash = 17 * hash + this.userId;
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
        if (this.vin != other.vin) {
            return false;
        }
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
        if (this.modelId != other.modelId) {
            return false;
        }
        if (this.styleId != other.styleId) {
            return false;
        }
        if (this.transmissionId != other.transmissionId) {
            return false;
        }
        if (this.colorId != other.colorId) {
            return false;
        }
        if (this.typeId != other.typeId) {
            return false;
        }
        if (this.statusId != other.statusId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.carDescription, other.carDescription)) {
            return false;
        }
        return Objects.equals(this.pictureUrl, other.pictureUrl);
    }
    
    
    
}
