package com.project.busticketbooking.controller;




import com.project.busticketbooking.dto.UserRegistrationDto;
import com.project.busticketbooking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    //inject Userservice into registration Controller

    private UserService userService;

    //constructor injection
    public RegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    //create ->presenting the form and saving the data to registration form
    @GetMapping
    public  String showRegistrationForm(Model model){
        model.addAttribute("user",new UserRegistrationDto());
        return "registration";
    }

    @PostMapping
    public  String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto){
        System.out.println("password "+registrationDto.getPassword() );
            //call save to register the user
           userService.save(registrationDto);
           //return registration page with success message
            return  "redirect:/registration?success";
    }


}
