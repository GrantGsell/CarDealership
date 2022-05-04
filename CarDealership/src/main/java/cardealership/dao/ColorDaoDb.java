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
public class ColorDaoDb implements ColorDao{
    // Create JdbcTemplate object
    @Autowired
    JdbcTemplate jdbc;
    
    
    /**
     * Obtains the Transmission id associated with the given name from the DB.
     * 
     * @param name the name of the Color ID.
     * @return an int denoting the Id number associated with the given name.
     */
    @Override
    public int getColorId(String name) {
        // Create sql statement 
        final String sql = "SELECT * FROM color WHERE nameColor = ?;";
        
        // Execute statement and obtain singualr Color object
        Color color = jdbc.queryForObject(sql, new ColorMapper(), name);
        
        // Return the id of the Color object
        return color.getColorId();
    }

    
    /**
     * Obtains a List of all Color data from the Color table in 
     * the DB.
     * 
     * @return a List of Color objects.
     */
    @Override
    public List<Color> getAllColors() {
        // Create sql statement
        final String sql = "SELECT * FROM color;";
        
        // Return a list of all of the Color objects
        return jdbc.query(sql, new ColorMapper());
    }

    
    /**
     * Extracts all Color names from the 'Color' table in the DB.
     * 
     * @return A list of all type names
     */
    @Override
    public List<String> getAllColorNames() {
        // Create a List to hold all of the names
        List<String> results = new ArrayList<>();
        
        // Obtain a list of all transmission objects
        List<Color> allColors = getAllColors();
        
        // Loop through all transmission objects, add names to results
        for(Color color : allColors)
            results.add(color.getNameColor());
        
        return results;
    }
    
    
    /**
     * Transforms sql data into Color Objects
     */
    public static final class ColorMapper implements RowMapper<Color>{

        @Override
        public Color mapRow(ResultSet rs, int i) throws SQLException {
            // Create a Color object
            Color color = new Color();
            
            // Populate Color fields
            color.setNameColor(rs.getString("nameColor"));
            color.setColorId(rs.getInt("colorId"));
            
            return color;
        }
        
    }
}
