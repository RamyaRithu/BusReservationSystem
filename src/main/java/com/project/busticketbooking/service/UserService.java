package com.project.busticketbooking.service;



import com.project.busticketbooking.dto.UserRegistrationDto;
import com.project.busticketbooking.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

//configuration
public interface UserService  extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
