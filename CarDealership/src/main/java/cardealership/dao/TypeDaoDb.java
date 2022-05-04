package cardealership.dao;

import cardealership.dto.Type;
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
public class TypeDaoDb implements TypeDao {
    // Create JDBC template object
    @Autowired
    JdbcTemplate jdbc;
    
    
    /**
     * Extracts a list of all TypeDao data stored in the database, as TypeDao objects.
     * 
     * @return A list of all TypeDao objects containing all of the TypeDao table data
     * in the database.
     */
    @Override
    public List<Type> getAllTypes() {
        // Create sql statement
        final String sql = "SELECT * FROM type;";        
        
        // Execute the statement and return list of all Type objects
        return jdbc.query(sql, new TypeMapper());
    }

    
    /**
     * Extracts all type names from the 'Type' table in the DB.
     * 
     * @return A list of all type names
     */
    @Override
    public List<String> getAllTypeNames() {
        // Create list to hold all type names
        List<String> results = new ArrayList<>();
        
        // Execute the statement and return list of all Type objects
        List<Type> allTypeData = getAllTypes();
        
        // Read all of the Type object names into the results list
        for(Type type : allTypeData)
            results.add(type.getNameType());
        
        return results;
    }

    
    /**
     * Get the type name associated with the given type id.
     * 
     * @return a int denoting the id associated with the given name.
     */
    public int getTypeIdByName(String name){
        // Create sql database statement
        final String sql = "SELECT * FROM type WHERE nameType = ?";
        
        // Execute statement and return the object
        Type type = jdbc.queryForObject(sql, new TypeMapper(), name);
        
        // Return the type Id
        return type.getTypeId();
    }
    
    
    /**
     * Transforms sql data into Type objects.
     */
    public static final class TypeMapper implements RowMapper<Type>{

        @Override
        public Type mapRow(ResultSet rs, int i) throws SQLException {
            // Create a new Type object
            Type type = new Type();
            
            // Populate type object field
            type.setTypeId(rs.getInt("typeId"));
            type.setNameType(rs.getString("nameType"));
            
            return type;
        }
        
    }
}
