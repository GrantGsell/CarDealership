/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dao.SalesDao;
import cardealership.dao.UserDao;
import cardealership.dao.VehicleDao;
import cardealership.dto.QuickSearch;
import cardealership.dto.Sales;
import cardealership.dto.Status;
import cardealership.dto.Vehicle;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    UserDao userDao;

    @GetMapping("sales/index")
    public String getSalesIndexPage() {
        return "sales/index";
    }

    @PostMapping("sales/search")
    @ResponseBody
    public List<Vehicle> createProduct(@RequestBody QuickSearch search) {

        return vehicleDao.getAllVehicles().stream()
                .filter(vehicle -> vehicle.getStatus().getStatusId() == 2) // check if vehicle is available
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
                .collect(Collectors.toList());
    }

    @GetMapping("sales/purchase/{vin}")
    public String getPurchaseDetailPage(@PathVariable String vin, Model model) {
        model.addAttribute("purchaseTypes", salesDao.getAllPurchaseType());
        model.addAttribute("states", salesDao.getAllState());
        model.addAttribute("vehicle", vehicleDao.getVehicleByVIN(vin));

        return "sales/purchase";
    }

    @PostMapping("sales/purchase/{vin}")
    @Transactional
    public String purchaseVehicle(@PathVariable String vin, Sales sales, HttpServletRequest request) {

        int stateId = Integer.parseInt(request.getParameter("stateId"));
        int purchaseTypeId = Integer.parseInt(request.getParameter("purchaseTypeId"));

        // TODO: currently logged in userId
        int userId = 2;

        sales.setState(salesDao.getStateById(stateId));
        sales.setPurchaseType(salesDao.getPurchaseTypeById(purchaseTypeId));
        sales.setUser(userDao.getUserById(userId));

        // Vehicle status update as sold.
        Vehicle vehicle = vehicleDao.getVehicleByVIN(vin);
        Status status = vehicle.getStatus();

        status.setStatusId(1);  // sold
        vehicle.setStatus(status);
        sales.setVehicle(vehicle);

        // update database
        vehicleDao.updateVehicle(vehicle);
        salesDao.createSales(sales);

        return "redirect:/sales/index";
    }
}
