package com.example.ejerciciomeli.controller;

import com.example.ejerciciomeli.model.CollectionSatellites;
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

        communicationService.decryptMessageLocation(satellites);

        return ResponseEntity.status(HttpStatus.OK).body("A");

    }

}
