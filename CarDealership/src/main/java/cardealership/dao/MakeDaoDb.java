package cardealership.dao;

import cardealership.dto.Make;
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
public class MakeDaoDb implements MakeDao {
    // Create JdbcTemplate object
    @Autowired
    JdbcTemplate jdbc;
    
    /**
     * Obtains the Make id associated with the given name from the DB.
     * 
     * @param name the name of the Make ID.
     * @return an int denoting the Id number associated with the given name.
     */
    @Override
    public int getMakeId(String name) {
        // Create sql statement 
        final String sql = "SELECT * FROM make WHERE nameMake = ?;";
        
        // Execute statement and obtain singualr Make object
        Make make = jdbc.queryForObject(sql, new MakeMapper(), name);
        
        // Return the id of the Make object
        return make.getMakeId();
    }

    
    /**
     * Obtains a List of all Make data from the Make table in 
     * the DB.
     * 
     * @return a List of Make objects.
     */
    @Override
    public List<Make> getAllMakes() {
        // Create sql statement
        final String sql = "SELECT * FROM make;";
        
        // Return a list of all of the Make objects
        return jdbc.query(sql, new MakeMapper());
    }

    
    /**
     * Extracts all Make names from the 'Make' table in the DB.
     * 
     * @return A list of all type names
     */
    @Override
    public List<String> getAllMakeNames() {
        // Create a List to hold all of the names
        List<String> results = new ArrayList<>();
        
        // Obtain a list of all transmission objects
        List<Make> allMakes = getAllMakes();
        
        // Loop through all transmission objects, add names to results
        for(Make make : allMakes)
            results.add(make.getNameMake());
        
        return results;
    }
    
    
    /**
     * Transforms sql data into Make Objects
     */
    public static final class MakeMapper implements RowMapper<Make>{
        
        @Override
        public Make mapRow(ResultSet rs, int i) throws SQLException{
            // Create Make object
            Make make = new Make();
            
            // Populate Make fields
            make.setDate(rs.getDate("dateAdded").toLocalDate());
            make.setMakeId(rs.getInt("makeId"));
            make.setNameMake(rs.getString("nameMake"));
            make.setUserId(rs.getInt("userId"));
            
            return make;
        }
    }
}
