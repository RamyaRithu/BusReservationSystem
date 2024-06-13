package com.project.busticketbooking.controller;


import com.project.busticketbooking.model.User;
import com.project.busticketbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    //handler for route login
    @GetMapping("/login")
    public  String login(){

        return "login";
    }
    @GetMapping("/home")
    public String home(Model model) {
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "home";
    }

    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findByEmail(user.getUsername());
        return users.getFirstName();
    }

}
