package cardealership.dao;

import cardealership.dto.Color;
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
public class InteriorDaoDb implements InteriorDao{
    // Create JdbcTemplate object
    @Autowired
    JdbcTemplate jdbc;
    
    
    /**
     * Obtains the Interior id associated with the given name from the DB.
     * 
     * @param name the name of the Interior ID.
     * @return an int denoting the Id number associated with the given name.
     */
    @Override
    public int getInteriorId(String name) {
        // Create sql statement 
        final String sql = "SELECT * FROM interiorColor WHERE nameInteriorColor = ?;";
        
        // Execute statement and obtain singualr Interior (Color) object
        Color color = jdbc.queryForObject(sql, new InteriorMapper(), name);
        
        // Return the id of the Interior object
        return color.getColorId();
    }

    
    /**
     * Obtains a List of all Interior (Color) data from the Interior table in 
     * the DB.
     * 
     * @return a List of Interior objects.
     */
    @Override
    public List<Color> getAllInteriors() {
        // Create sql statement
        final String sql = "SELECT * FROM interiorColor;";
        
        // Return a list of all of the Interior objects
        return jdbc.query(sql, new InteriorMapper());
    }

    
    /**
     * Extracts all Interior (Color) names from the 'Interior' table in the DB.
     * 
     * @return A list of all type names
     */
    @Override
    public List<String> getAllInteriorNames() {
        // Create a List to hold all of the names
        List<String> results = new ArrayList<>();
        
        // Obtain a list of all transmission objects
        List<Color> allInteriors = getAllInteriors();
        
        // Loop through all transmission objects, add names to results
        for(Color color : allInteriors)
            results.add(color.getNameColor());
        
        return results;
    }
    
    
    /**
     * Transforms sql data into Interior (Color) Objects
     */
    public static final class InteriorMapper implements RowMapper<Color>{

        @Override
        public Color mapRow(ResultSet rs, int i) throws SQLException {
            // Create a Interior object
            Color interior = new Color();
            
            // Populate Interior fields
            interior.setNameColor(rs.getString("nameInteriorColor"));
            interior.setColorId(rs.getInt("interiorColorId"));
            
            return interior;
        }
        
    }
}
