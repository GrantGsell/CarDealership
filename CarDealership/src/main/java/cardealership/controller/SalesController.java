/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dao.SalesDao;
import cardealership.dao.VehicleDao;
import cardealership.dto.QuickSearch;
import cardealership.dto.Vehicle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Grant
 */
@Controller
public class SalesController {

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    SalesDao salesDao;

    @GetMapping("sales/index")
    public String getSalesIndexPage() {
        return "sales/index";
    }

    @PostMapping("sales/search")
    @ResponseBody
    public List<Vehicle> createProduct(@RequestBody QuickSearch search) {
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        // custom logic
        return vehicles;
    }

    @GetMapping("sales/purchase/{vin}")
    public String purchaseDetailPage(@PathVariable String vin, Model model) {
        model.addAttribute("purchaseTypes", salesDao.getAllPurchaseType());
        model.addAttribute("states", salesDao.getAllState());

        return "sales/purchase";
    }
}
