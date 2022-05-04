package cardealership.dao;

import cardealership.dto.Transmission;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grant
 */
@Repository
public class TransmissionDaoDb implements TransmissionDao {
    // Create JdbcTemplate object
    @Autowired
    JdbcTemplate jdbc;
    
    
    /**
     * Obtains the Transmission id associated with the given name from the DB.
     * 
     * @param name the name of the Transmission ID.
     * @return an int denoting the Id number associated with the given name.
     */
    @Override
    public int getTransmissionId(String name) {
        // Create sql statement 
        final String sql = "SELECT * FROM transmission WHERE transmissionName = ?;";
        
        // Execute statement and obtain singualr Transmission object
        Transmission transmission = jdbc.queryForObject(sql, new TransmissionMapper(), name);
        
        // Return the id of the Transmission object
        return transmission.getTransmissionId();
    }

    
    /**
     * Obtains a List of all Transmission data from the Transmission table in 
     * the DB.
     * 
     * @return a List of Transmission objects.
     */
    @Override
    public List<Transmission> getAllTransmissions() {
        // Create sql statement
        final String sql = "SELECT * FROM transmission;";
        
        // Return a list of all of the Transmission objects
        return jdbc.query(sql, new TransmissionMapper());
    }

    
    /**
     * Extracts all Transmission names from the 'Transmission' table in the DB.
     * 
     * @return A list of all type names
     */
    @Override
    public List<String> getAllTransmissionNames() {
        // Create a List to hold all of the names
        List<String> results = new ArrayList<>();
        
        // Obtain a list of all transmission objects
        List<Transmission> allTransmissions = getAllTransmissions();
        
        // Loop through all transmission objects, add names to results
        for(Transmission transmission : allTransmissions)
            results.add(transmission.getTransmissionName());
        
        return results;
    }
    
    
    /**
     * Transforms sql data into Transmission Objects
     */
    public static final class TransmissionMapper implements RowMapper<Transmission>{

        @Override
        public Transmission mapRow(ResultSet rs, int i) throws SQLException {
            // Create a Transmission object
            Transmission transmission = new Transmission();
            
            // Populate Transmission fields
            transmission.setTransmissionName(rs.getString("transmissionName"));
            transmission.setTransmissionId(rs.getInt("transmissionId"));
            
            return transmission;
        }
        
    }
}
