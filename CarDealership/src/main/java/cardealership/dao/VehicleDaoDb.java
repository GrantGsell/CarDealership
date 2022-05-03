package cardealership.dao;

import cardealership.dto.BodyStyle;
import cardealership.dto.Color;
import cardealership.dto.Make;
import cardealership.dto.Model;
import cardealership.dto.Status;
import cardealership.dto.Transmission;
import cardealership.dto.Type;
import cardealership.dto.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grant
 */
@Repository
public class VehicleDaoDb implements VehicleDao{
    // Jdbc template
    @Autowired
    JdbcTemplate jdbc;
    
    
    /**
     * Takes a vehicle object and stores it into the cardealership database.
     * 
     * @param vehicle, the vehicle to be input into the database.
     * @return 
     */
    @Override
    public Vehicle create(Vehicle vehicle) {
        try{
            // Create a sql statement
            final String sql = "INSERT INTO vehicle(vin, mileage, salePrice, msrp, "
                    + "carYear, carDescription, pictureUrl, modelId, styleId, "
                    + "transmissionId, colorId, typeId, statusId, userId, interiorColorId, isFeatured) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            // Execute the statement
            jdbc.update(sql, vehicle.getVin(), vehicle.getMileage(),
                    vehicle.getSalePrice(), vehicle.getMsrp(), vehicle.getCarYear(),
                    vehicle.getCarDescription(), vehicle.getPictureUrl(),
                    vehicle.getModel().getModelId(), vehicle.getStyle().getStyleId(),
                    vehicle.getTransmission().getTransmissionId(),
                    vehicle.getColor().getColorId(), vehicle.getType().getTypeId(),
                    vehicle.getStatus().getStatusId(), vehicle.getUserId(), 
                    vehicle.getInterior().getColorId(), vehicle.getIsFeatured());
        }catch(DataAccessException ex){
            return null;
        }
        // Return the newly created vehicle object
        return getVehicleByVIN(vehicle.getVin());
    }

    
    /**
     * Gets a vehicle and its associated data from the cardealership database.
     * 
     * @param vin the Vehicle Identification Number for the vehicle that the 
     *     user wants to obtain.
     * @return a Vehicle object that is associated with the given vin number.
     */
    @Override
    public Vehicle getVehicleByVIN(String vin) {
        try{
            // Create SQL statement
            final String sql = "SELECT * FROM vehicle "
                    + "INNER JOIN bodystyle USING(styleId) "
                    + "INNER JOIN model USING(modelId) "
                    + "INNER JOIN transmission USING(transmissionId) "
                    + "INNER JOIN type USING(typeId) "
                    + "INNER JOIN color USING(colorID) "
                    + "INNER JOIN status USING(statusId) "
                    + "INNER JOIN make USING(makeId) "
                    + "INNER JOIN interiorColor USING (interiorColorId) "
                    + "WHERE vin = ?;";
            
            // Execute the query
            return jdbc.queryForObject(sql, new VehicleMapper(), vin);
        }catch(DataAccessException ex){
            return null;
        }
    }

    
    /**
     * Obtains a List of all Vehicles in the car dealership database.
     * 
     * @return a List containing all vehicles in the car dealership database.
     */
    @Override
    public List<Vehicle> getAllVehicles() {
        // Create sql statement
        final String sql = "SELECT * FROM vehicle "
                + "INNER JOIN bodystyle USING(styleId) "
                + "INNER JOIN model USING(modelId) "
                + "INNER JOIN transmission USING(transmissionId) "
                + "INNER JOIN type USING(typeId) "
                + "INNER JOIN color USING(colorID) "
                + "INNER JOIN status USING(statusId) "
                + "INNER JOIN make USING(makeId) "
                + "INNER JOIN interiorColor USING (interiorColorId);";
        
        // Return the entire list
        List<Vehicle> test =  jdbc.query(sql, new VehicleMapper());
        return test;
    }

    
    /**
     * Deletes a vehicle associated with the given vin number.
     * 
     * @param vin the Vehicle Identification Number for the vehicle that the 
     *     user wants to obtain.
     * @return the vehicle object which is deleted from the database.
     */
    @Override
    public Vehicle deleteByVIN(String vin) {
        // Create sql statement to delete salesInfo table data first
        final String DELETE_SALESINFO = "DELETE FROM salesInfo WHERE vin = ?;";
        
        // Execute statement to delete salesInfo
        jdbc.update(DELETE_SALESINFO, vin);
        
        // Create sql statement to delete vehicle table data
        final String DELETE_VEHICLE = "DELETE FROM vehicle WHERE vin = ?;";
        
        // Execute statement to delete vehicle data
        jdbc.update(DELETE_VEHICLE, vin);
        
        // 'return' the deleted vehicle object, should be null
        return getVehicleByVIN(vin);
    }

    
    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        try{
            // Create a sql statement
            final String sql = "UPDATE vehicle SET vin = ?, mileage = ?, "
                    + "salePrice = ?, msrp = ?, carYear = ?, carDescription = ?, "
                    + "pictureUrl = ?, modelId = ?, styleId = ?, transmissionId = ?, "
                    + "colorId = ?, typeId = ?, statusId = ?, userId = ?, "
                    + "interiorColorId = ?, isFeatured = ? "
                    + "WHERE vin = ?";
            
            // Execute the statement
            jdbc.update(sql, vehicle.getVin(), vehicle.getMileage(),
                    vehicle.getSalePrice(), vehicle.getMsrp(), vehicle.getCarYear(),
                    vehicle.getCarDescription(), vehicle.getPictureUrl(),
                    vehicle.getModel().getModelId(), vehicle.getStyle().getStyleId(),
                    vehicle.getTransmission().getTransmissionId(),
                    vehicle.getColor().getColorId(), vehicle.getType().getTypeId(),
                    vehicle.getStatus().getStatusId(), vehicle.getUserId(), 
                    vehicle.getInterior().getColorId(), vehicle.getIsFeatured(),
                    vehicle.getVin());
        }catch(DataAccessException ex){
            return false;
        }
        // If reached object was updated successfully
        return true;
    }

    @Override
    public List<Vehicle> getAllVehiclesByMake(int makeId) {
        final String sql = "SELECT * FROM vehicle "
                + "INNER JOIN bodystyle USING(styleId) "
                + "INNER JOIN model USING(modelId) "
                + "INNER JOIN transmission USING(transmissionId) "
                + "INNER JOIN type USING(typeId) "
                + "INNER JOIN color USING(colorID) "
                + "INNER JOIN status USING(statusId) "
                + "INNER JOIN make USING(makeId) "
                + "INNER JOIN interiorColor USING (interiorColorId) "
                + "WHERE makeId = ?;";
        
        // Execute sql statement
        return jdbc.query(sql, new VehicleMapper(), makeId);
    }
    
    
    /**
     * 
     */
    public static final class VehicleMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet rs, int i) throws SQLException {
            // Create a new Vehicle object
            Vehicle vehicle = new Vehicle();
            
            // Set all non-object fields
            vehicle.setVin(rs.getString("vin"));
            vehicle.setMileage(rs.getInt("mileage"));
            vehicle.setSalePrice(rs.getInt("salePrice"));
            vehicle.setMsrp(rs.getInt("msrp"));
            vehicle.setCarYear(rs.getInt("carYear"));
            vehicle.setCarDescription(rs.getString("carDescription"));
            vehicle.setPictureUrl(rs.getString("pictureUrl"));
            vehicle.setUserId(rs.getInt("userId"));
            vehicle.setIsFeatured(rs.getBoolean("isFeatured"));
            
            // Create, populate Model Object
            Model model = new Model();
            model.setMakeId(rs.getInt("makeId"));
            model.setNameModel(rs.getString("nameModel"));
            model.setModelId(rs.getInt("modelId"));
            
            // Create, populate BodyStyle Object
            BodyStyle style = new BodyStyle();
            style.setNameStyle(rs.getString("nameStyle"));
            style.setStyleId(rs.getInt("styleId"));
            
            // Create, populate Transmission Object
            Transmission transmission = new Transmission();
            transmission.setTransmissionName(rs.getString("transmissionName"));
            transmission.setTransmissionId(rs.getInt("transmissionId"));
                        
            // Create, populate Color Object
            Color color = new Color();
            color.setNameColor(rs.getString("nameColor"));
            color.setColorId(rs.getInt("colorId"));
            
            // Create, populate Interior Color object
            Color interior = new Color();
            interior.setColorId(rs.getInt("interiorColorId"));
            interior.setNameColor(rs.getString("nameInteriorColor"));
            
            // Create, populate Type Object
            Type type = new Type();
            type.setNameType(rs.getString("nameType"));
            type.setTypeId(rs.getInt("typeId"));
                        
            // Create, populate Status Object
            Status status = new Status();
            status.setNameStatus(rs.getString("nameStatus"));
            status.setStatusId(rs.getInt("statusId"));
            
            // Create, populate Make Object
            Make make = new Make();
            make.setDate(rs.getDate("dateAdded").toLocalDate());
            make.setNameMake(rs.getString("nameMake"));
            make.setMakeId(rs.getInt("makeId"));
            make.setUserId(rs.getInt("userId"));
            
            // Set Vehicle object fields
            vehicle.setModel(model);
            vehicle.setStyle(style);
            vehicle.setTransmission(transmission);
            vehicle.setColor(color);
            vehicle.setType(type);
            vehicle.setStatus(status);
            vehicle.setMake(make);
            vehicle.setInterior(interior);
            
            return vehicle;
        }
        
    }
    
}
