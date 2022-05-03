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
public class ReportController {

    @GetMapping("reports/index")
    public String getReportsPage() {
        return "reports/index";
    }

    @GetMapping("reports/inventory")
    public String getInventoryReportsPage() {
        return "reports/inventory";
    }

    @GetMapping("reports/sales")
    public String getSalesReportsPage() {
        return "reports/sales";
    }
}
