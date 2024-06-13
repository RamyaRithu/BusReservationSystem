package com.project.busticketbooking.controller;

import com.project.busticketbooking.model.Feedback;
import com.project.busticketbooking.model.User;
import com.project.busticketbooking.repository.UserRepository;
import com.project.busticketbooking.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/showFeedbackForm")
    public  String showFeedbackForm(Model model){
        //create an  object
        Feedback feedback=new Feedback();
        model.addAttribute("feedback",feedback);
        String user= returnUsername();
        model.addAttribute("userDetails", user);

        //resolve thymleaf template
        return  "feedback";
    }


    @PostMapping("/saveFeedback")
    public  String saveFeedback(@ModelAttribute("feedback")Feedback feedback){
        feedbackService.save(feedback);

        //redirect to home page
        return  "redirect:/showFeedbackForm?success";

    }
    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findByEmail(user.getUsername());
        return users.getFirstName();
    }


}
