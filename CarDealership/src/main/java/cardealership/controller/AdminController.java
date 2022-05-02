/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Jeonghoon
 */
@Controller
public class AdminController {

    @GetMapping("admin/vehicles")
    public String GetAdminVehiclesPage() {
        return "admin/vehicles";
    }
}
