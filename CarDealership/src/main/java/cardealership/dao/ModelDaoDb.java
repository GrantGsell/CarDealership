package cardealership.dao;

import cardealership.dto.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grant
 */
@Repository
public class ModelDaoDb implements ModelDao{
   // Create JdbcTemplate object
    @Autowired
    JdbcTemplate jdbc;
    
    
    /**
     * Obtains the Transmission id associated with the given name from the DB.
     * 
     * @param name the name of the Model ID.
     * @return an int denoting the Id number associated with the given name.
     */
    @Override
    public int getModelId(String name) {
        // Create sql statement 
        final String sql = "SELECT * FROM model WHERE nameModel = ?;";
        
        // Execute statement and obtain singualr Model object
        Model model = jdbc.queryForObject(sql, new ModelMapper());
        
        // Return the id of the Model object
        return model.getModelId();
    }

    
    /**
     * Obtains a List of all Model data from the Model table in 
     * the DB.
     * 
     * @return a List of Model objects.
     */
    @Override
    public List<Model> getAllModels() {
        // Create sql statement
        final String sql = "SELECT * FROM model;";
        
        // Return a list of all of the Model objects
        return jdbc.query(sql, new ModelMapper());
    }

    
    /**
     * Extracts all Model names from the 'Model' table in the DB.
     * 
     * @return A list of all type names
     */
    @Override
    public List<String> getAllModelNames() {
        // Create a List to hold all of the names
        List<String> results = new ArrayList<>();
        
        // Obtain a list of all transmission objects
        List<Model> allModels = getAllModels();
        
        // Loop through all transmission objects, add names to results
        for(Model model : allModels)
            results.add(model.getNameModel());
        
        return results;
    }
    
    
    /**
     * Transforms sql data into Model Objects
     */
    public static final class ModelMapper implements RowMapper<Model>{

        @Override
        public Model mapRow(ResultSet rs, int i) throws SQLException {
            // Create a Model object
            Model model = new Model();
                        
            // Populate the Model fields
            model.setModelId(rs.getInt("modelId"));
            model.setMakeId(rs.getInt("makeId"));
            model.setNameModel(rs.getString("nameModel"));
                       
            return model;
        }
        
    }
}
