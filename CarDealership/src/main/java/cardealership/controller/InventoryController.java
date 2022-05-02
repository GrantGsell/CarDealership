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
public class InventoryController {

    @GetMapping("inventory/new")
    public String GetNewInventoryPage() {
        return "inventory/new";
    }

    @GetMapping("inventory/used")
    public String GetUsedInventoryPage() {
        return "inventory/used";
    }
}
