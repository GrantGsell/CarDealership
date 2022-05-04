package cardealership.dao;

import cardealership.dto.Make;
import java.util.List;

/**
 *
 * @author Grant
 */
public interface MakeDao {
    /**
     * Obtains the Make id associated with the given name from the DB.
     *
     * @param name the name of the Make ID.
     * @return an int denoting the Id number associated with the given name.
     */
    int getMakeId(String name);
    
    
    /**
     * Obtains a List of all Make data from the Make table in
     * the DB.
     *
     * @return a List of Make objects.
     */
    List<Make> getAllMakes();
    
    
    /**
     * Extracts all Make names from the 'Make' table in the DB.
     *
     * @return A list of all type names
     */
    List<String> getAllMakeNames(); 
}
