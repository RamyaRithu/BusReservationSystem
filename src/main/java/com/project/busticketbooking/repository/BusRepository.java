package com.project.busticketbooking.repository;

import com.project.busticketbooking.model.Bus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByFromDestinationAndToDestination(String from, String to);
}
