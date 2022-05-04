/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dao.SpecialDao;
import cardealership.dao.UserDao;
import cardealership.dao.VehicleDao;
import cardealership.dto.Special;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    SpecialDao specialDao;

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
    public String getUsersPage(Model model) {
        model.addAttribute("users", userDao.getAllUsers());
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

    @GetMapping("admin/edituser/{id}")
    public String getEditUserPage(@PathVariable int id, HttpServletRequest request) {

        return "admin/edituser";
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
    public String getAdminSpecialsPage(Model model) {
        model.addAttribute("specials", specialDao.getAllSpecial());
        return "admin/specials";
    }

    @PostMapping("admin/specials")
    public String addSpecial(Special special, HttpServletRequest request) {
        // TODO: userId should set with login user's id
        int userId = 2;

        special.setUser(userDao.getUserById(userId));
        specialDao.createSpecial(special);

        return "redirect:/admin/specials";
    }

    @GetMapping("admin/specials/delete/{id}")
    public String deleteSpecial(@PathVariable int id, HttpServletRequest request) {
        specialDao.deleteSpecialById(id);

        return "redirect:/admin/specials";
    }
}
