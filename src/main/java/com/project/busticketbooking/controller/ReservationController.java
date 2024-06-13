package com.project.busticketbooking.controller;

import com.project.busticketbooking.model.Bus;
import com.project.busticketbooking.model.Reservation;
import com.project.busticketbooking.model.User;

import com.project.busticketbooking.repository.UserRepository;
import com.project.busticketbooking.service.BusService;
import com.project.busticketbooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;


@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusService busService;


    @GetMapping("/book")
    public String showBookingPage(@RequestParam Long busId, @RequestParam Long userId, Model model) {
        model.addAttribute("busId", busId);
        model.addAttribute("userId", userId);
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "book";
    }
    /*@GetMapping("/confirmation/{id}")
    public String showConfirmation(@PathVariable("id") Long reservationId, Model model) {
        Optional<Reservation> reservation = reservationService.getReservationById(reservationId);
        model.addAttribute("reservation", reservation);
        return "confirmation";
    }*/

    @PostMapping("/book")
    public String bookTicket(@RequestParam Long userId, @RequestParam Long busId, Model model) {
        Reservation reservation = new Reservation();
        reservation.setUser(new User());
        reservation.getUser().setId(userId);
        reservation.setBus(new Bus());
        reservation.getBus().setId(busId);

        reservation.setBookingTime(LocalDateTime.now());
        reservationService.bookTicket(reservation);
        model.addAttribute("reservation", reservation);
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "confirmation";
    }
    @GetMapping("/confirmation/{id}")
    public String showConfirmation(@PathVariable("id") Long reservationId, Model model) {
        Optional<Reservation> optionalReservation = reservationService.getReservationById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            System.out.println("Reservation ID: " + reservation.getId());
            System.out.println("Bus Number: " + reservation.getBus().getBusNumber());
            System.out.println("From: " + reservation.getBus().getFromDestination());
            System.out.println("To: " + reservation.getBus().getToDestination());
            System.out.println("Departure Time: " + reservation.getBus().getDepartureTime());

            model.addAttribute("reservation", reservation);
            String user= returnUsername();
            model.addAttribute("userDetails", user);
        } else {
            // Handle the case where the reservation is not found
            System.out.println("Reservation not found with ID: " + reservationId);
            model.addAttribute("errorMessage", "Reservation not found");
            return "error"; // return to an error page
        }

        return "confirmation";
    }
    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findByEmail(user.getUsername());
        return users.getFirstName();
    }

}