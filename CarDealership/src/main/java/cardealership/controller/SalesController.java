/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dto.QuickSearch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Grant
 */
@Controller
public class SalesController {

    @GetMapping("sales/index")
    public String GetSalesIndexPage() {
        return "sales/index";
    }

    @PostMapping("/sales/search")
    @ResponseBody
    public String createProduct(@RequestBody QuickSearch search) {
        // custom logic
        return "This is a test";
    }
}
