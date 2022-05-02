/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dao.SpecialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Grant
 */
@Controller
public class HomeController {

//    @Autowired
//    SpecialDao specialDao;

    // Handling Get Request for multiple value
    @RequestMapping(value = {"home", "home/index"}, method = RequestMethod.GET)
    public String getHomePage() {
        return "home/index";
    }

    @GetMapping("home/contact")
    public String getContactPage() {
        return "home/contact";
    }

    @GetMapping("home/specials")
    public String getSpecialsPage(Model model) {
//        model.addAttribute("specials", specialDao.getAllSpecials());
        return "home/specials";
    }
}
