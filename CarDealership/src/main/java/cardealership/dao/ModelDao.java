package cardealership.dao;

import cardealership.dto.Model;
import java.util.List;

/**
 *
 * @author Grant
 */
public interface ModelDao {
    /**
     * Obtains the Model id associated with the given name from the DB.
     *
     * @param name the name of the Model ID.
     * @return an int denoting the Id number associated with the given name.
     */
    int getModelId(String name);
    
    
    /**
     * Obtains a List of all Model data from the Model table in
     * the DB.
     *
     * @return a List of Model objects.
     */
    List<Model> getAllModels();
    
    
    /**
     * Extracts all Model names from the 'Model' table in the DB.
     *
     * @return A list of all type names
     */
    List<String> getAllModelNames();    
}
