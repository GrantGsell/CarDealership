/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.controller;

import cardealership.dao.ContactDao;
import cardealership.dao.SpecialDao;
import cardealership.dao.VehicleDao;
import cardealership.dto.Contact;
import cardealership.dto.Special;
import cardealership.dto.Vehicle;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Grant
 */
@Controller
public class HomeController {

    @Autowired
    ContactDao contactDao;

    @Autowired
    SpecialDao specialDao;
    
    @Autowired
    VehicleDao vehicleDao;

    // Handling Get Request for multiple value
    @RequestMapping(value = {"/", "home", "home/index"}, method = RequestMethod.GET)
    public String getHomePage(Model model) {
        List<Special> specials = specialDao.getAllSpecial();

        if (specials.size() > 5) {
            model.addAttribute("specials", specials.subList(0, 5));
        } else {
            model.addAttribute("specials", specials);
        }
        
        //Obtain a list of all vehicles and filter to get only featured vehicles
        List<Vehicle> featuredVehicles = vehicleDao.getAllVehicles().stream()
                .filter(vehicle ->{
                    return vehicle.getIsFeatured();
                })
                .collect(Collectors.toList());
        
        // Add the list of featured vehicles to the model object
        model.addAttribute("featuredVehicles", featuredVehicles);
        
        return "home/index";
    }

    @GetMapping("home/contact")
    public String getContactPage() {
        return "home/contact";
    }

    @PostMapping("addContact")
    public String addContact(HttpServletRequest request) {
        String contactName = request.getParameter("Name");
        String email = request.getParameter("email");
        String phone = request.getParameter("Phone");
        String message = request.getParameter("message");

        Contact contact = new Contact();
        Contact.setContactName(contactName);
        Contact.setemail(email);
        Contact.setphone(phone);
        contact.setMessage(message);
/*
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(contact);

        if (violations.isEmpty()) {
            ContactDao.addContact(contact);
        }
*/
        return "redirect:/contact";
    }

    @GetMapping("home/specials")
    public String getSpecialsPage(Model model) {
        model.addAttribute("specials", specialDao.getAllSpecial());
        return "home/specials";
    }

    @PostMapping("addSpecial")
    public String addSpecial(HttpServletRequest request) {
        String title = request.getParameter("title");
        String description = request.getParameter("specialDescription");

        Special special = new Special();
        Special.settitle(title);
        /*
        Special.setspecialDescription(specialDescription);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(special);

        if (violations.isEmpty()) {
            SpecialDao.addSpecial(special);
        }
*/
        return "redirect:/special";
    }
}
