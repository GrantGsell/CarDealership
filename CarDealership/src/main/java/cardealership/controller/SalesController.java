/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dao.SalesDao;
import cardealership.dto.QuickSearch;
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
    SalesDao salesDao;

    @GetMapping("sales/index")
    public String getSalesIndexPage() {
        return "sales/index";
    }

    @PostMapping("sales/search")
    @ResponseBody
    public String createProduct(@RequestBody QuickSearch search) {
        // custom logic
        return "This is a test";
    }

    @GetMapping("sales/purchase/{vin}")
    public String purchaseDetailPage(@PathVariable String vin, Model model) {
        model.addAttribute("purchaseTypes", salesDao.getAllPurchaseType());
        model.addAttribute("states", salesDao.getAllState());

        return "sales/purchase";
    }
}
