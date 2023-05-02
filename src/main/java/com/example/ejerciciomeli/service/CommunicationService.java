package com.example.ejerciciomeli.service;

import com.example.ejerciciomeli.exeption.LocationException;
import com.example.ejerciciomeli.exeption.MessageException;
import com.example.ejerciciomeli.model.CollectionSatellites;
import com.example.ejerciciomeli.model.Position;
import com.example.ejerciciomeli.model.Satellite;
import com.example.ejerciciomeli.model.Spaceship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunicationService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessageService messageService;

    public Spaceship decryptMessageLocation(CollectionSatellites satellites) throws MessageException, LocationException {

        List<Satellite> satelliteList = satellites.getSatellites();
        Position position = locationService.getPositionShipSpeace(locationService.getPositions(satelliteList),
                locationService.getDistances(satelliteList));
        String message = messageService.decryptingMessage(messageService.getListMessages(satellites));

        return new Spaceship(position, message);

    }

}
