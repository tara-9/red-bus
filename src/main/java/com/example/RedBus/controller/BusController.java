package com.example.RedBus.controller;

import com.example.RedBus.model.Bus;
import com.example.RedBus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @GetMapping("/")
    private List<Bus> findAllBus(){
        return busRepository.findAll();
    }

    @PostMapping("/")
    private Bus insertBus(@RequestBody Bus bus){
        return busRepository.save(bus);
    }

    @PutMapping("/")
    private Bus editBus(@RequestBody Bus requestBus){
        return busRepository.findByName(requestBus.getName())
                .map(bus -> {
                    bus.setDestination(requestBus.getDestination());
                    bus.setSource(requestBus.getSource());
                    busRepository.save(bus);
                    return bus;
                }).orElse(null);
    }


}
