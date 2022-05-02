/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dao.UserDao;
import cardealership.dao.VehicleDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    // vehicles
    @GetMapping("admin/vehicles")
    public String getAdminVehiclesPage() {
        return "admin/vehicles";
    }

    @GetMapping("admin/addvehicle")
    public String getAddVehiclePage(Model model) {
        return "admin/addvehicle";
    }

    @PostMapping("admin/addvehicle")
    public String addVehicle(HttpServletRequest request) {
        // TODO: Store database for adding vehicle

        return "admin/vehicles";
    }

    // users
    @GetMapping("admin/users")
    public String getAdminUsersPage() {
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

}
