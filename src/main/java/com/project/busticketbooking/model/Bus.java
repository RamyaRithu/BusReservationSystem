package com.project.busticketbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String busNumber;
    private String fromDestination;
    private String toDestination;
    private String departureTime;
    private int availableSeats;
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;



}