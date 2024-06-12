package com.project.busticketbooking.controller;


import com.project.busticketbooking.model.Bus;
import com.project.busticketbooking.model.Reservation;
import com.project.busticketbooking.service.BusService;
import com.project.busticketbooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BusController {
    @Autowired
    private BusService busService;


    @GetMapping("/buses")
    public String listBuses(Model model) {
        List<Bus> buses = busService.getAllBuses();
        model.addAttribute("buses", buses);
        return "busList";
    }
    @GetMapping("/searchBus")
    public String searchBuses(Model model) {
        List<Bus> buses = busService.getAllBuses();
        model.addAttribute("buses", buses);
        return "search";
    }



    @GetMapping("/search")
    public String searchBuses(@RequestParam String from, @RequestParam String to, Model model) {
        List<Bus> buses = busService.searchBuses(from, to);
        model.addAttribute("buses", buses);
        return "searchResults";
    }

    @GetMapping("/addBus")
    public String showAddBusForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "addBus";
    }

    @PostMapping("/addBus")
    public String addBus(@ModelAttribute Bus bus) {
        busService.saveBus(bus);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable Long id, Model model) {
        busService.deleteBus(id);
        return "redirect:/busList";
    }
}