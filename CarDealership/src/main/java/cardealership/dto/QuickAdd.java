package cardealership.dto;

import cardealership.dao.BodyStyleDao;
import cardealership.dao.ColorDao;
import cardealership.dao.InteriorDao;
import cardealership.dao.MakeDao;
import cardealership.dao.ModelDao;
import cardealership.dao.TransmissionDao;
import cardealership.dao.TypeDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Grant
 */
public class QuickAdd {
    // Fields
    String make;
    String model;
    String type;
    String bodyStyle;
    int year;
    String transmission;
    String color;
    String interior;
    int mileage;
    String vin;
    int msrp;
    int salePrice;
    String description;
    String picture;
    boolean isFeatured;
            
    
    // Getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public int getYear() {
        return year;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getColor() {
        return color;
    }

    public String getInterior() {
        return interior;
    }

    public int getMileage() {
        return mileage;
    }

    public String getVin() {
        return vin;
    }

    public int getMsrp() {
        return msrp;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public boolean getIsFeatured() {
        return isFeatured;
    }   
    
    
    // Setters
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setMsrp(int msrp) {
        this.msrp = msrp;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }
    
    
    
    // HashCode override
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.make);
        hash = 71 * hash + Objects.hashCode(this.model);
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.bodyStyle);
        hash = 71 * hash + this.year;
        hash = 71 * hash + Objects.hashCode(this.transmission);
        hash = 71 * hash + Objects.hashCode(this.color);
        hash = 71 * hash + Objects.hashCode(this.interior);
        hash = 71 * hash + this.mileage;
        hash = 71 * hash + Objects.hashCode(this.vin);
        hash = 71 * hash + this.msrp;
        hash = 71 * hash + this.salePrice;
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + Objects.hashCode(this.picture);
        return hash;
    }

    
    // Equals override
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
        final QuickAdd other = (QuickAdd) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if (this.msrp != other.msrp) {
            return false;
        }
        if (this.salePrice != other.salePrice) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
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
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.picture, other.picture);
    }
    
    
    /**
     * 
     */
    public Vehicle toVehicle(Vehicle vehicle, TypeDao typeDao,BodyStyleDao bodyStyleDao, 
            TransmissionDao transmissionDao, ColorDao colorDao, 
            InteriorDao interiorDao, ModelDao modelDao, MakeDao makeDao){
        
        // Populate Vehicle Object Fields
        vehicle.setVin(this.getVin());
        vehicle.setMileage(this.getMileage());
        vehicle.setSalePrice(this.getSalePrice());
        vehicle.setMsrp(this.getMsrp());
        vehicle.setCarYear(this.getYear());
        vehicle.setCarDescription(this.getDescription());
        vehicle.setPictureUrl(this.getPicture());
        vehicle.setUserId(1);
        vehicle.setIsFeatured(false);
        
        // Create, populate a Model Object
        Model modelObj = new Model();
        modelObj.setMakeId(makeDao.getMakeId(make));
        modelObj.setModelId(modelDao.getModelId(model));
        modelObj.setNameModel(model);
        
        // Create, populate a BodyStyle Object
        BodyStyle style = new BodyStyle();
        style.setStyleId(bodyStyleDao.getBodyStyleId(bodyStyle));
        style.setNameStyle(bodyStyle);
        
        // Create, populate a Transmission Object
        Transmission transmissionObj = new Transmission();
        transmissionObj.setTransmissionId(transmissionDao.getTransmissionId(transmission));
        transmissionObj.setTransmissionName(transmission);
        
        // Create, populate a Color Object
        Color colorObj = new Color();
        colorObj.setColorId(colorDao.getColorId(color));
        colorObj.setNameColor(color);
        
        // Create, populate a color object fo the interior
        Color interiorObj = new Color();
        interiorObj.setColorId(interiorDao.getInteriorId(interior));
        interiorObj.setNameColor(interior);
        
        // Create, populate a Type Object
        Type typeObj = new Type();
        typeObj.setTypeId(typeDao.getTypeIdByName(type));
        typeObj.setNameType(type);
        
        // Create, populate a Status Object
        Status status = new Status();
        status.setStatusId(2);
        status.setNameStatus("Available");
        
        // Create, populate a Make Object   
        String str = LocalDate.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(str, formatter);
        Make makeObj = new Make();
        makeObj.setMakeId(makeDao.getMakeId(make));
        makeObj.setUserId(1);
        makeObj.setDate(dateTime);
        makeObj.setNameMake(make);        
        
        // Populate Vehicle Object, Object Fields
        vehicle.setModel(modelObj);
        vehicle.setStyle(style);
        vehicle.setTransmission(transmissionObj);
        vehicle.setColor(colorObj);
        vehicle.setType(typeObj);
        vehicle.setStatus(status);
        vehicle.setMake(makeObj);
        vehicle.setInterior(interiorObj);
        
        return vehicle;
    }
    
    
}
