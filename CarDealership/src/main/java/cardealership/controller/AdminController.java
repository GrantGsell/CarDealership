package cardealership.controller;

import cardealership.dao.BodyStyleDao;
import cardealership.dao.ColorDao;
import cardealership.dao.InteriorDao;
import cardealership.dao.MakeDao;
import cardealership.dao.ModelDao;
import cardealership.dao.TransmissionDao;
import cardealership.dao.TypeDao;
import cardealership.dao.UserDao;
import cardealership.dao.VehicleDao;
import cardealership.dto.QuickAdd;
import cardealership.dto.QuickSearch;
import cardealership.dto.Vehicle;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jeonghoon
 */
@Controller
public class AdminController {

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    UserDao userDao;
    
    @Autowired
    TypeDao typeDao;
    
    @Autowired
    BodyStyleDao bodyStyleDao;
    
    @Autowired
    TransmissionDao transmissionDao;
    
    @Autowired
    ColorDao colorDao;
    
    @Autowired
    InteriorDao interiorDao;
    
    @Autowired
    ModelDao modelDao;
    
    @Autowired
    MakeDao makeDao;

    // vehicles
    @GetMapping("admin/vehicles")
    public String getAdminVehiclesPage() {
        return "admin/vehicles";
    }
    
    @PostMapping("admin/vehicles")
    @ResponseBody
    public List<Vehicle> getAdminVehicles(@RequestBody QuickSearch search){
        List<Vehicle> test = vehicleDao.getAllVehicles().stream()
                .filter(vehicle -> {
                    if (!search.getKeyword().isBlank()) {
                        return vehicle.getMake().getNameMake().contains(search.getKeyword())
                                || vehicle.getModel().getNameModel().contains(search.getKeyword())
                                || Integer.toString(vehicle.getCarYear()).equals(search.getKeyword());
                    }
                    return true;
                })
                .filter(vehicle -> {
                    if (search.getMinYear() > 0) {
                        if (search.getMaxYear() > 0) {
                            return vehicle.getCarYear() >= search.getMinYear()
                                    && vehicle.getCarYear() <= search.getMaxYear();
                        } else {
                            return vehicle.getCarYear() >= search.getMinYear();
                        }
                    } else {
                        if (search.getMaxYear() > 0) {
                            return vehicle.getCarYear() <= search.getMaxYear();
                        }
                    }
                    // No Minimum year and No Maximum year
                    return true;
                })
                .filter(vehicle -> {
                    if (search.getMinPrice() != null) {
                        if (search.getMaxPrice() != null) {
                            return vehicle.getSalePrice() >= search.getMinPrice().intValue()
                                    && vehicle.getSalePrice() <= search.getMaxPrice().intValue();
                        } else {
                            return vehicle.getSalePrice() >= search.getMinPrice().intValue();
                        }
                    } else {
                        if (search.getMaxPrice() != null) {
                            return vehicle.getSalePrice() <= search.getMaxPrice().intValue();
                        }
                    }
                    // No Minimum Price and No Maximum Price
                    return true;
                })
                .filter(vehicle -> {
                    if(search.getType().equals("used"))
                        return vehicle.getType().getTypeId() == 2;
                    else if(search.getType().equals("new"))
                        return vehicle.getType().getTypeId() == 1;
                    else
                        return true;
                })
                .collect(Collectors.toList());
        
        return test;
    }

    @GetMapping("admin/addvehicle")
    public String getAddVehiclePage(Model model) {
        // Create a list of all vehicle type names
        List<String> typeNames = typeDao.getAllTypeNames();
        
        // Create a list of all vehicle bodystyles
        List<String> bodyStyles = bodyStyleDao.getAllBodyStyleNames();
        
        // Create a list of all vehicle transmissions
        List<String> transmissions = transmissionDao.getAllTransmissionNames();
        
        // Create a list of all vehicle colors
        List<String> colors = colorDao.getAllColorNames();
        
        // Create a list of all interior colors
        List<String> interiors = interiorDao.getAllInteriorNames();
        
        // Create a list of all vehicle models
        List<String> models = modelDao.getAllModelNames();
        
        // Create a list of all vehicle makes
        List<String> makes = makeDao.getAllMakeNames();
        
        //Add data to the model object
        model.addAttribute("typeNames",typeNames);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("transmissions", transmissions);
        model.addAttribute("colors", colors);
        model.addAttribute("interiors", interiors);
        model.addAttribute("models", models);
        model.addAttribute("makes", makes);
        
        return "admin/addvehicle";
    }

    @PostMapping("admin/addvehicle")
    @ResponseBody
    public String addVehicle(@RequestBody QuickAdd request) {
        // Create a Vehicle Object
        Vehicle vehicle = new Vehicle();
        
        // Populate the fields using QuickAdd toVehicle method
        request.toVehicle(vehicle, typeDao, bodyStyleDao, transmissionDao, 
                colorDao, interiorDao, modelDao, makeDao);
        
        // Write the vehicle data to the database
        vehicleDao.create(vehicle);
        
        return "admin/vehicles";
    }

    // users
    @GetMapping("admin/users")
    public String getUsersPage() {
        return "admin/users";
    }

    @GetMapping("admin/adduser")
    public String getAddUserPage(Model model) {
        model.addAttribute("userRoles", userDao.getAllUserRoles());
        return "admin/adduser";
    }

    @PostMapping("admin/adduser")
    public String addUser(HttpServletRequest request) {
        // TODO: Store database for adding vehicle

        return "admin/users";
    }

    @GetMapping("admin/make")
    public String getMakePage() {
        return "admin/make";
    }

    @GetMapping("admin/model")
    public String getModelPage() {
        return "admin/model";
    }

    @GetMapping("admin/specials")
    public String getAdminSpecialsPage() {
        return "admin/specials";
    }

}
