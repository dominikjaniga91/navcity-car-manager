package pl.com.navcity.controller;

import pl.com.navcity.model.Driver;
import pl.com.navcity.model.Route;
import pl.com.navcity.service.DriverServiceImpl;
import pl.com.navcity.service.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class DriverController {


    @Autowired
    DriverServiceImpl driverService;

    @Autowired
    RouteServiceImpl routeService;

    @GetMapping("/driversList")
    public String showCarList(Model model){

        model.addAttribute("listOfDrivers", driverService.getAllDrivers());
        return "drivers";
    }

    @GetMapping(path="/addDriverForm")
    public String prepareAddDriverForm(@RequestParam(value = "driverId", required = false) Integer driverId, Model model){

        if(driverId != null){
            Driver driver = driverService.getDriverById(driverId);
            model.addAttribute("listOfRoutes", driver.getRouteList());
            model.addAttribute("driver", driver);
            return "updateDriver";
        } else{
            model.addAttribute("driver", new Driver());
            return "addDriver";
        }

    }

    @PostMapping("/addDriver")
    public String createOrUpdateCar(@Valid Driver driver, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("validation", "data is invalid");
            System.out.println("data is invalid");
            return "addCar";
        }
        driverService.saveDriver(driver);
        return "mainPanel";
    }

    @PostMapping("/updateDriver")
    public String  updateCar(@Valid Driver driver,
                             BindingResult bindingResult,
                             @RequestParam("driverId") Integer driverId){

        if(bindingResult.hasErrors()){
            return "updateDriver";
        }

        driverService.updateDriver(driverId, driver);
        return "redirect:/driversList";
    }

    @GetMapping(path="/deleteDriver")
    public String deleteCarFromDatabase(@RequestParam("driverId") Integer driverId){

        driverService.deleteDriverById(driverId);

        return "redirect:/driversList";
    }

}
