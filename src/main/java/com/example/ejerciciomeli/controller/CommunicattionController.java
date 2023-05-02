package com.example.ejerciciomeli.controller;

import com.example.ejerciciomeli.exeption.LocationException;
import com.example.ejerciciomeli.exeption.MessageException;
import com.example.ejerciciomeli.model.CollectionSatellites;
import com.example.ejerciciomeli.model.Spaceship;
import com.example.ejerciciomeli.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunicattionController {

    @Autowired
    CommunicationService communicationService;

    @PostMapping("/topsecret")
    private ResponseEntity decryptMessage(@RequestBody CollectionSatellites satellites) {
        try{
            Spaceship spaceship = communicationService.decryptMessageLocation(satellites);
            return ResponseEntity.status(HttpStatus.OK).body(spaceship);
        } catch (MessageException | LocationException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
