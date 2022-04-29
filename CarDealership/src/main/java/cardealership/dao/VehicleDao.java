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
    public Vehicle create(Vehicle vehicle);

    public Vehicle getVehicleByVIN(String vin);

    public List<Vehicle> getAllVehicles();

    public Vehicle deleteByVIN(String vin);

    public boolean updateVehicle(Vehicle vehicle);

    // TODO: add more logic.
    public List<Vehicle> getAllVehiclesByMake(int makeId);
}
