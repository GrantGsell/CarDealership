package cardealership.dao;

import cardealership.dto.Color;
import java.util.List;

/**
 *
 * @author Grant
 */
public interface ColorDao {
    /**
     * Obtains the Color id associated with the given name from the DB.
     * 
     * @param name the name of the Color ID.
     * @return an int denoting the Id number associated with the given name.
     */
    int getColorId(String name);
    
    
    /**
     * Obtains a List of all Color data from the Color table in 
     * the DB.
     * 
     * @return a List of Color objects.
     */
    List<Color> getAllColors();
    
    
    /**
     * Extracts all Color names from the 'Color' table in the DB.
     * 
     * @return A list of all type names
     */
    List<String> getAllColorNames();
}
