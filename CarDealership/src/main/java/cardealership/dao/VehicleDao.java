package cardealership.dao;

import cardealership.dto.Vehicle;
import java.util.List;

/**
 *
 * @author Grant
 */
public interface VehicleDao {
    /**
     * Takes a vehicle object and stores it into the cardealership database.
     * 
     * @param vehicle, the vehicle to be input into the database.
     * @return 
     */
    Vehicle create(Vehicle vehicle);
    
    
    /**
     * Gets a vehicle and its associated data from the cardealership database.
     * 
     * @param vin the Vehicle Identification Number for the vehicle that the 
     *     user wants to obtain.
     * @return a Vehicle object that is associated with the given vin number.
     */
    Vehicle getVehicleByVIN(String vin);

    
    /**
     * Obtains a List of all Vehicles in the car dealership database.
     * 
     * @return a List containing all vehicles in the car dealership database.
     */
    List<Vehicle> getAllVehicles();

    
    /**
     * Deletes a vehicle associated with the given vin number.
     * 
     * @param vin the Vehicle Identification Number for the vehicle that the 
     *     user wants to obtain.
     * @return the vehicle object which is deleted from the database.
     */
    Vehicle deleteByVIN(String vin);

    
    /**
     * Update the vehicle object in the database.
     * 
     * @param vehicle
     * @return 
     */
    boolean updateVehicle(Vehicle vehicle);

    
    /**
     * Obtain all vehicles that have the given makeId.
     * 
     * @param makeId
     * @return 
     */
    List<Vehicle> getAllVehiclesByMake(int makeId);
}
