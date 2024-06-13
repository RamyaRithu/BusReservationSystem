package com.project.busticketbooking.controller;


import com.project.busticketbooking.model.Bus;
import com.project.busticketbooking.model.Reservation;
import com.project.busticketbooking.model.User;
import com.project.busticketbooking.repository.UserRepository;
import com.project.busticketbooking.service.BusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BusController {
    @Autowired
    private BusService busService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/buses")
    public String listBuses(Model model) {
        List<Bus> buses = busService.getAllBuses();
        model.addAttribute("buses", buses);
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "busList";
    }
    @GetMapping("/searchBus")
    public String searchBuses(Model model) {
        List<Bus> buses = busService.getAllBuses();
        model.addAttribute("buses", buses);
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "search";
    }



    @GetMapping("/search")
    public String searchBuses(@RequestParam String from, @RequestParam String to,  Model model) {
        List<Bus> buses = busService.searchBuses(from, to );
        model.addAttribute("buses", buses);
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "searchResults";
    }

    @GetMapping("/addBus")
    public String showAddBusForm(Model model) {
        model.addAttribute("bus", new Bus());
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "addBus";
    }

    @PostMapping("/addBus")
    public String addBus(@ModelAttribute Bus bus) {
        busService.saveBus(bus);
        return "redirect:/buses";
    }
    /*@PostMapping("/addBus")
    public String addBus(@ModelAttribute("bus") @Valid Bus bus, BindingResult result, @RequestParam("date") String dateString) {
        // Validate form input
        if (result.hasErrors()) {
            return "error-page"; // Handle validation errors
        }

        // Convert String date to Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "error-page"; // Handle parsing exception
        }

        // Set the Date object in the Bus entity
        bus.setDate(date);

        // Save the bus object to the database
        busService.saveBus(bus);

        // Redirect to a confirmation page or another appropriate page
        return "redirect:/buses";
    }*/


    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable Long id, Model model) {
        busService.deleteBus(id);
        return "redirect:/buses";
    }

    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findByEmail(user.getUsername());
        return users.getFirstName();
    }
}