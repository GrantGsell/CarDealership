package cardealership.dao;

import cardealership.dto.Transmission;
import java.util.List;

/**
 *
 * @author Grant
 */
public interface TransmissionDao {
    /**
     * Obtains the Transmission id associated with the given name from the DB.
     * 
     * @param name the name of the Transmission ID.
     * @return an int denoting the Id number associated with the given name.
     */
    int getTransmissionId(String name);
    
    
    /**
     * Obtains a List of all Transmission data from the Transmission table in 
     * the DB.
     * 
     * @return a List of Transmission objects.
     */
    List<Transmission> getAllTransmissions();
    
    
    /**
     * Extracts all Transmission names from the 'Transmission' table in the DB.
     * 
     * @return A list of all type names
     */
    List<String> getAllTransmissionNames();
}
