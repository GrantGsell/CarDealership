package cardealership.dao;

import cardealership.dto.BodyStyle;
import cardealership.dto.Color;
import cardealership.dto.Make;
import cardealership.dto.Model;
import cardealership.dto.Status;
import cardealership.dto.Transmission;
import cardealership.dto.Type;
import cardealership.dto.Vehicle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Grant
 */
@SpringBootTest
public class VehicleDaoDbTest {
    @Autowired
    VehicleDao vehicleDao;
    
    public VehicleDaoDbTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // Obtain a list of all vehicles in the test database
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
                
        // Delete all currently stored vehicles in the vehicles table
        for(Vehicle vehicle : vehicles)
            vehicleDao.deleteByVIN(vehicle.getVin());
    }
    
    @AfterEach
    public void tearDown() {
    }
    

    /**
     * Test of getVehicleByVIN method, of class VehicleDaoDb.
     */
    @Test
    public void testCreateGetVehicleByVIN() {
        // Generate a list of vehicels
        List<Vehicle> list = generateVehicleList();
        Vehicle vehicle = list.get(0);
        String vin = vehicle.getVin();
        
        // Add the Vehicle object to the database
        vehicleDao.create(vehicle);
        
        // Retrieve the same object from the database into a new Vehicle instance
        Vehicle returnVehicle = vehicleDao.getVehicleByVIN(vin);
        
        // Assert Equals, the the new Vehicle object is equivalent to the one written to the database
        assertEquals(vehicle, returnVehicle);
    }

    
    /**
     * Test of deleteByVIN method, of class VehicleDaoDb.
     * Test of GetAllVehicles method, of class VehicleDaoDb.
     */
    @Test
    public void testGellAllVehicles_AND_DeleteByVIN() {
        // Generate a list of Vehicle objects
        List<Vehicle> vehicles = generateVehicleList();
        
        // Add all three vehicle objects to the test database
        for(Vehicle vehicle : vehicles)
            vehicleDao.create(vehicle);
        
        // Assert Equals that the size of getAllVehicles reutrn list is 3
        assertEquals(vehicleDao.getAllVehicles().size(), 3);
        
        // Delete the vehicle0
        vehicleDao.deleteByVIN(vehicles.get(0).getVin());
        
        // Assert equals null when trying to retrieve vehicle0
        assertEquals(vehicleDao.getVehicleByVIN(vehicles.get(0).getVin()), null);
        
        // Assert that getAllVehicles has a size of 2
        assertEquals(vehicleDao.getAllVehicles().size(), 2);
        
        // Delete the vehicle1
        vehicleDao.deleteByVIN(vehicles.get(1).getVin());
        
        // Assert equals null when trying to retrieve vehicle1
        assertEquals(vehicleDao.getVehicleByVIN(vehicles.get(1).getVin()), null);
        
        // Assert that getAllVehicles has a size of 1
        assertEquals(vehicleDao.getAllVehicles().size(), 1);
        
        // Delete the vehicle2
        vehicleDao.deleteByVIN(vehicles.get(2).getVin());
        
        // Assert equals null when trying to retrieve vehicle2
        assertEquals(vehicleDao.getVehicleByVIN(vehicles.get(2).getVin()), null);
        
        // Assert that getAllVehicles has a size of 0
        assertEquals(vehicleDao.getAllVehicles().size(), 0);
    }

    
    /**
     * Test of updateVehicle method, of class VehicleDaoDb.
     */
    @Test
    public void testUpdateVehicle() {
        // Generate a list of Vehicles
        List<Vehicle> vehicles = generateVehicleList();
        
        // Add the first vehicle to the database
        vehicleDao.create(vehicles.get(0));
                        
        // Retrieve the first vehicle from the database, retrieved0
        Vehicle retrieved0 = vehicleDao.getVehicleByVIN(vehicles.get(0).getVin());
        
        // Assert equals: retrived, first vehicle from the list
        assertEquals(vehicles.get(0), retrieved0);
        
        // Change the mileage to 150,000 for the first vehicle in the list
        vehicles.get(0).setMileage(150000);
        
        // Call the update function for the first vehicle
        vehicleDao.updateVehicle(vehicles.get(0));
        
        // Retrieve the vehicle from the database again, retrieved1
        Vehicle retrieved1 = vehicleDao.getVehicleByVIN(vehicles.get(0).getVin());
        
        // Assert not equals: retrieved0, retrieved1
        assertFalse(retrieved0.equals(retrieved1));
        
        // Assert equals: retrieved1, first vehicle in list
        assertEquals(vehicles.get(0), retrieved1);
    }

    /**
     * Test of getAllVehiclesByMake method, of class VehicleDaoDb.
     */
    @Test
    public void testGetAllVehiclesByMake() {
        // Obtain a list of 3 Vehicle Objects
        List<Vehicle> vehicles = generateVehicleList();
        
        // Change the second vehicle in the list to match the first vehicles make
        int makeId0 = vehicles.get(0).getModel().getMakeId();
        vehicles.get(1).getModel().setMakeId(makeId0);
        
        // Add all three vehicles to the database
        for(Vehicle vehicle : vehicles)
            vehicleDao.create(vehicle);
        
        // Retrieve a list of all vehicles with the same make as vehicle0,1
        List<Vehicle> retrieved = vehicleDao.getAllVehiclesByMake(makeId0);
        
        // Assert equals: size of the retrieved list and 2
        assertEquals(retrieved.size(), 2);
        
        // Assert equals: vehicle list object 0, retrieved list 0
        assertEquals(vehicles.get(0), retrieved.get(0));
        
        // Assert equals: vehicle list object 1, retrieved list 1
        assertEquals(vehicles.get(1), retrieved.get(1));
    }
    
    
    /**
     * Creates a list of three vehicles objects
     */
    private List<Vehicle> generateVehicleList(){
        // Create vehicle 0
        // Create a Vehicle Object 
        Vehicle vehicle0 = new Vehicle();
        
        // Create vin variable
        String vin = "0987654321ABCDEFG";
        
        // Populate Vehicle Object Fields
        vehicle0.setVin(vin);
        vehicle0.setMileage(135000);
        vehicle0.setSalePrice(8000);
        vehicle0.setMsrp(8100);
        vehicle0.setCarYear(2002);
        vehicle0.setCarDescription("");
        vehicle0.setPictureUrl("");
        vehicle0.setUserId(1);
        
        // Create, populate a Model Object
        Model model = new Model();
        model.setMakeId(3);
        model.setModelId(2);
        model.setNameModel("530ia");
        
        // Create, populate a BodyStyle Object
        BodyStyle style = new BodyStyle();
        style.setStyleId(1);
        //style.setDescription("");
        style.setNameStyle("Car");
        
        // Create, populate a Transmission Object
        Transmission transmission = new Transmission();
        transmission.setTransmissionId(1);
        transmission.setTransmissionName("Automatic");
        
        // Create, populate a Color Object
        Color color = new Color();
        color.setColorId(3);
        color.setNameColor("Silver");
        
        // Create, populate a Type Object
        Type type = new Type();
        type.setTypeId(2);
        type.setNameType("Used");
        
        // Create, populate a Status Object
        Status status = new Status();
        status.setStatusId(2);
        status.setNameStatus("Available");
        
        // Create, populate a Make Object   
        String str = "2022-01-31";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(str, formatter);
        Make make = new Make();
        make.setMakeId(3);
        make.setUserId(1);
        make.setDate(dateTime);
        make.setNameMake("BMW");        
        
        // Populate Vehicle Object, Object Fields
        vehicle0.setModel(model);
        vehicle0.setStyle(style);
        vehicle0.setTransmission(transmission);
        vehicle0.setColor(color);
        vehicle0.setType(type);
        vehicle0.setStatus(status);
        vehicle0.setMake(make);
        
        
        // Create vehicle 1
        // Create a Vehicle Object 
        Vehicle vehicle1 = new Vehicle();
        
        // Create vin variable
        vin = "ABCDEFG0987654321";
        
        // Populate Vehicle Object Fields
        vehicle1.setVin(vin);
        vehicle1.setMileage(50000);
        vehicle1.setSalePrice(12000);
        vehicle1.setMsrp(18100);
        vehicle1.setCarYear(2022);
        vehicle1.setCarDescription("");
        vehicle1.setPictureUrl("");
        vehicle1.setUserId(1);
        
        // Create, populate a Model Object
        model = new Model();
        model.setMakeId(1);
        model.setModelId(1);
        model.setNameModel("Focus");
        
        // Create, populate a BodyStyle Object
        style = new BodyStyle();
        style.setStyleId(1);
        style.setNameStyle("Car");
        
        // Create, populate a Transmission Object
        transmission = new Transmission();
        transmission.setTransmissionId(1);
        transmission.setTransmissionName("Automatic");
        
        // Create, populate a Color Object
        color = new Color();
        color.setColorId(5);
        color.setNameColor("Red");
        
        // Create, populate a Type Object
        type = new Type();
        type.setTypeId(2);
        type.setNameType("Used");
        
        // Create, populate a Status Object
        status = new Status();
        status.setStatusId(2);
        status.setNameStatus("Available");
        
        // Create, populate a Make Object   
        str = "2021-12-09";
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateTime = LocalDate.parse(str, formatter);
        make = new Make();
        make.setMakeId(1);
        make.setUserId(1);
        make.setDate(dateTime);
        make.setNameMake("Ford");        
        
        // Populate Vehicle Object, Object Fields
        vehicle1.setModel(model);
        vehicle1.setStyle(style);
        vehicle1.setTransmission(transmission);
        vehicle1.setColor(color);
        vehicle1.setType(type);
        vehicle1.setStatus(status);
        vehicle1.setMake(make);
        
        // Create vehicle 2
        // Create a Vehicle Object 
        Vehicle vehicle2 = new Vehicle();
        
        // Create vin variable
        vin = "A1B2C3D4E5F6G7H89";
        
        // Populate Vehicle Object Fields
        vehicle2.setVin(vin);
        vehicle2.setMileage(50000);
        vehicle2.setSalePrice(12000);
        vehicle2.setMsrp(18100);
        vehicle2.setCarYear(2022);
        vehicle2.setCarDescription("");
        vehicle2.setPictureUrl("");
        vehicle2.setUserId(1);
        
        // Create, populate a Model Object
        model = new Model();
        model.setMakeId(1);
        model.setModelId(1);
        model.setNameModel("Focus");
        
        // Create, populate a BodyStyle Object
        style = new BodyStyle();
        style.setStyleId(1);
        style.setNameStyle("Car");
        
        // Create, populate a Transmission Object
        transmission = new Transmission();
        transmission.setTransmissionId(1);
        transmission.setTransmissionName("Automatic");
        
        // Create, populate a Color Object
        color = new Color();
        color.setColorId(4);
        color.setNameColor("Gold");
        
        // Create, populate a Type Object
        type = new Type();
        type.setTypeId(1);
        type.setNameType("New");
        
        // Create, populate a Status Object
        status = new Status();
        status.setStatusId(2);
        status.setNameStatus("Available");
        
        // Create, populate a Make Object   
        str = "2021-12-09";
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateTime = LocalDate.parse(str, formatter);
        make = new Make();
        make.setMakeId(1);
        make.setUserId(1);
        make.setDate(dateTime);
        make.setNameMake("Ford");        
        
        // Populate Vehicle Object, Object Fields
        vehicle2.setModel(model);
        vehicle2.setStyle(style);
        vehicle2.setTransmission(transmission);
        vehicle2.setColor(color);
        vehicle2.setType(type);
        vehicle2.setStatus(status);
        vehicle2.setMake(make);
        
        
        // Create a list of the three vehicle objects
        List<Vehicle> list = new ArrayList<>();
        list.add(vehicle0);
        list.add(vehicle1);
        list.add(vehicle2);
        return list;   
    }
}
