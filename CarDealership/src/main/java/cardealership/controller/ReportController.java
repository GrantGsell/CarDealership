/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dao.SalesDao;
import cardealership.dao.UserDao;
import cardealership.dto.SalesReport;
import cardealership.dto.SalesReportSearchForm;
import cardealership.dto.User;
import java.util.List;
import java.util.stream.Collectors;
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
public class ReportController {

    @Autowired
    UserDao userDao;

    @Autowired
    SalesDao salesDao;

    @GetMapping("reports/index")
    public String getReportsPage() {
        return "reports/index";
    }

    @GetMapping("reports/inventory")
    public String getInventoryReportsPage(Model model) {
        model.addAttribute("newVehicles", salesDao.getInventoryReportForNewVehicles());
        model.addAttribute("usedVehicles", salesDao.getInventoryReportForUsedVehicles());

        return "reports/inventory";
    }

    @GetMapping("reports/sales")
    public String getSalesReportsPage(Model model) {
        List<User> salesUsers = userDao.getAllUsers()
                .stream()
                .filter(user -> user.getRole().getUserRoleId() == 2)
                .collect(Collectors.toList());

        model.addAttribute("users", salesUsers);

        return "reports/sales";
    }

    @PostMapping("report/sales")
    @ResponseBody
    public List<SalesReport> searchSalesReport(@RequestBody SalesReportSearchForm searchForm) {
        return salesDao.getAllSalesReport(searchForm);
    }
}
