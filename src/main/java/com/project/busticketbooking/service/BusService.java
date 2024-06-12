package com.project.busticketbooking.service;

import com.project.busticketbooking.model.Bus;
import com.project.busticketbooking.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public List<Bus> searchBuses(String from, String to) {
        return busRepository.findByFromDestinationAndToDestination(from, to);
    }

    public void saveBus(Bus bus) {
        busRepository.save(bus);
    }
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public void deleteBus(Long id) {

        busRepository.deleteById(id);
    }
}



