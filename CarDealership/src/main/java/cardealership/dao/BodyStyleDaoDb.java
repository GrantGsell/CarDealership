package cardealership.dao;

import cardealership.dto.BodyStyle;
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
public class BodyStyleDaoDb implements BodyStyleDao{
    // Create JDBC template object
    @Autowired
    JdbcTemplate jdbc;
    
    /**
     * Obtains a List of all bodystyle data from the bodystyle table in the DB.
     * 
     * @return a List of BodyStyle objects.
     */
    @Override
    public List<BodyStyle> getAllBodyStyles() {
        // Create sql statement
        final String sql = "SELECT * FROM bodystyle;";
        
        // Return a list of all of the BodyStyle objects
        return jdbc.query(sql, new BodyStyleMapper());
    }

    
    /**
     * Obtains a List of the names of all bodystyle entries in the 
     * bodystyle table of the DB.
     * 
     * @return a List of strings comprised of all bodystyle names.
     */
    @Override
    public List<String> getAllBodyStyleNames() {
        // Create a List to hold all of the names
        List<String> results = new ArrayList<>();
        
        // Obtain a list of all bodystyle objects
        List<BodyStyle> allBodyStyles = getAllBodyStyles();
        
        // Loop through all bodystyle objects, add names to results
        for(BodyStyle bodyStyle : allBodyStyles)
            results.add(bodyStyle.getNameStyle());
        
        return results;
    }

    
    /**
     * Obtains the id associated with the given name from the DB.
     * 
     * @param name the name of the ID.
     * @return an int denoting the Id number associated with the given name.
     */
    @Override
    public int getBodyStyleId(String name) {
        // Create sql statement 
        final String sql = "SELECT * FROM bodystyle WHERE nameBodyStyle = ?;";
        
        // Execute statement and obtain singualr BodyStyle object
        BodyStyle bodyStyle = jdbc.queryForObject(sql, new BodyStyleMapper());
        
        // Return the id of the BodyStyle object
        return bodyStyle.getStyleId();
    }
    
    
    /**
     * Transforms sql data into BodyStyle objects.
     */
    public static final class BodyStyleMapper implements RowMapper<BodyStyle>{

        @Override
        public BodyStyle mapRow(ResultSet rs, int i) throws SQLException {
            // Create a BodyStyle object 
            BodyStyle bodyStyle = new BodyStyle();
            
            // Populate BodyStyle fields
            bodyStyle.setNameStyle(rs.getString("nameStyle"));
            bodyStyle.setStyleId(rs.getInt("styleId"));
            
            return bodyStyle;
        }
    
}
    
}
