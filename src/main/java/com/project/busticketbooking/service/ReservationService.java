package com.project.busticketbooking.service;


import com.project.busticketbooking.model.Reservation;
import com.project.busticketbooking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public void bookTicket(Reservation reservation) {
        reservationRepository.save(reservation);
    }
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }
}