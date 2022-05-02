/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Grant
 */
@Controller
public class HomeController {

    // Handling Get Request for multiple value
    @RequestMapping(value = {"home", "home/index"}, method = RequestMethod.GET)
    public String getHomePage() {
        return "home/index";
    }

    @GetMapping("home/contact")
    public String GetContactPage() {
        return "home/contact";
    }

    @GetMapping("home/specials")
    public String GetSpecialsPage() {
        return "home/specials";
    }
}
