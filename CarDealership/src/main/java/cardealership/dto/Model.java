package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Grant
 */
public class Model {
    // Fields 
    private int modelId;
    private String nameModel;
    private int makeId;

    // Setters
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
    
    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }
    
    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }
    
    
    // Getters
    public int getModelId() {
        return modelId;
    }

    public String getNameModel() {
        return nameModel;
    }    

    public int getMakeId() {
        return makeId;
    }

    
    // HashCode Override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.modelId;
        hash = 89 * hash + Objects.hashCode(this.nameModel);
        hash = 89 * hash + this.makeId;
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
        final Model other = (Model) obj;
        if (this.modelId != other.modelId) {
            return false;
        }
        if (this.nameModel != other.nameModel) {
            return false;
        }
        return this.makeId == other.makeId;
    }
    
}
