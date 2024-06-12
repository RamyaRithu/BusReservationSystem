package com.project.busticketbooking.service;

import com.project.busticketbooking.model.Feedback;
import com.project.busticketbooking.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;


    public void save(Feedback feedback)
    {
        feedbackRepository.save(feedback);
    }


}
