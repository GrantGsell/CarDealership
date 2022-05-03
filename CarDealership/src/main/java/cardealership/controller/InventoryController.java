package cardealership.controller;

import cardealership.dao.VehicleDao;
import cardealership.dto.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Grant
 */
@Controller
public class InventoryController {

    @Autowired
    VehicleDao vehicleDao;
    
    @GetMapping("inventory/new")
    public String getNewInventoryPage(Model model) {
        List<Vehicle> numbers = vehicleDao.getAllVehicles();
        
        model.addAttribute("numberList", numbers);
        return "inventory/new";
    }

    @GetMapping("inventory/used")
    public String getUsedInventoryPage(Model model) {
        List<Vehicle> numbers = vehicleDao.getAllVehicles();
        
        model.addAttribute("numberList", numbers);
        
        return "inventory/used";
    }
}
