package cardealership.dao;

import cardealership.dto.BodyStyle;
import java.util.List;

/**
 *
 * @author Grant
 */
public interface BodyStyleDao {
    
    /**
     * Obtains a List of all bodystyle data from the bodystyle table in the DB.
     * 
     * @return a List of BodyStyle objects.
     */
    List<BodyStyle> getAllBodyStyles();
    
    
    /**
     * Obtains a List of the names of all bodystyle entries in the 
     * bodystyle table of the DB.
     * 
     * @return a List of strings comprised of all bodystyle names.
     */
    List<String> getAllBodyStyleNames();
    
    
    /**
     * Obtains the id associated with the given name from the DB.
     * 
     * @param name the name of the ID.
     * @return an int denoting the Id number associated with the given name.
     */
    int getBodyStyleId(String name);
}
