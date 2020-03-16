package pl.com.navcity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.navcity.model.reports.DriverReports;
import pl.com.navcity.model.reports.CarReports;
import pl.com.navcity.model.reports.RouteReports;


@Controller
public class ReportsController {

    @GetMapping(path="/showReportsPage")
    public String showReports(Model model){
        model.addAttribute("reportsCarList", CarReports.values());
        model.addAttribute("reportsDriverList", DriverReports.values());
        model.addAttribute("reportsRouteList", RouteReports.values());
        return "reports";
    }



    @GetMapping(path="/showCarReports")
    public String selectRaport(@RequestParam("reportCar") String reportCar,Model model){
        model.addAttribute("reportsCarList", CarReports.values());
        model.addAttribute("reportsDriverList", DriverReports.values());
        model.addAttribute("reportsRouteList", RouteReports.values());
        model.addAttribute("listOfCars", CarReports.valueOf(reportCar).myReport());
        return "reports";
    }

    @GetMapping(path="/showDriverReports")
    public String selectDriverRaport(@RequestParam("reportDriver") String reportDriver,Model model){
        model.addAttribute("reportsCarList", CarReports.values());
        model.addAttribute("reportsDriverList", DriverReports.values());
        model.addAttribute("reportsRouteList", RouteReports.values());
        model.addAttribute("listOfDrivers", DriverReports.valueOf(reportDriver).myReport());
        return "reports";
    }

    @GetMapping(path="/showRouteReports")
    public String selectRouteRaport(@RequestParam("reportRoute") String reportRoute,Model model){
        model.addAttribute("reportsCarList", CarReports.values());
        model.addAttribute("reportsDriverList", DriverReports.values());
        model.addAttribute("reportsRouteList", RouteReports.values());
        model.addAttribute("listOfRoutes", RouteReports.valueOf(reportRoute).myReport());
        return "reports";
    }

}
