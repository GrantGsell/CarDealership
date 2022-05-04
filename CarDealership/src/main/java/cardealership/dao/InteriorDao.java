package cardealership.dao;

import cardealership.dto.Color;
import java.util.List;

/**
 *
 * @author Grant
 */
public interface InteriorDao {
    /**
     * Obtains the Interior id associated with the given name from the DB.
     * 
     * @param name the name of the Interior ID.
     * @return an int denoting the Id number associated with the given name.
     */
    int getInteriorId(String name);
    
    
    /**
     * Obtains a List of all Interior (Color) data from the Interior table in 
     * the DB.
     * 
     * @return a List of Interior objects.
     */
    List<Color> getAllInteriors();
    
    
    /**
     * Extracts all Interior names from the 'Interior' table in the DB.
     * 
     * @return A list of all type names
     */
    List<String> getAllInteriorNames();
}
