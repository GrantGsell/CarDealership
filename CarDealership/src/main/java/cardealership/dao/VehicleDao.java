package cardealership.dao;

import cardealership.dto.Vehicle;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Grant
 */
public interface VehicleDao {
    Vehicle create(Vehicle vehicle);

    Vehicle getVehicleByVIN(String vin);

    List<Vehicle> getAllVehicles();

    Vehicle deleteByVIN(String vin);

    boolean updateVehicle(Vehicle vehicle);

    // TODO: add more logic.
    List<Vehicle> getAllVehiclesByMake(int makeId);
}
