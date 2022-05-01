package cardealership.dto;

/**
 *
 * @author Grant
 */
public class Model {
    // Fields 
    int modelId;
    int nameModel;
    int makeId;

    // Setters
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
    
    public void setNameModel(int nameModel) {
        this.nameModel = nameModel;
    }
    
    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }
    
    
    // Getters
    public int getModelId() {
        return modelId;
    }

    public int getNameModel() {
        return nameModel;
    }    

    public int getMakeId() {
        return makeId;
    }

    
    // HashCode Override
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.modelId;
        hash = 71 * hash + this.nameModel;
        hash = 71 * hash + this.makeId;
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
