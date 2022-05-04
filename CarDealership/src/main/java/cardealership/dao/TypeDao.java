package cardealership.dao;

import cardealership.dto.Type;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grant
 */
public interface TypeDao {
    
    /**
     * Extracts a list of all TypeDao data stored in the database, as TypeDao objects.
     * 
     * @return A list of all TypeDao objects containing all of the TypeDao table data
     * in the database.
     */
    List<Type> getAllTypes();
    
    
    /**
     * Extracts all type names from the 'TypeDao' table in the DB.
     * 
     * @return A list of all type names
     */
    List<String> getAllTypeNames();
    
    
    /**
     * Get the type name associated with the given type id.
     * 
     * @return a int denoting the id associated with the given name.
     * @param name, the name of the id you want to find.
     */
    int getTypeIdByName(String name);
}
