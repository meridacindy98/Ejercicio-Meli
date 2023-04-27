package com.example.ejerciciomeli.service;

import com.example.ejerciciomeli.model.CollectionSatellites;
import com.example.ejerciciomeli.model.Satellite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommunicationService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessageService messageService;

    @Autowired
    private Environment environment;

    public void decryptMessageLocation(CollectionSatellites satellites) {

        List<Satellite> satelliteList = satellites.getSatellites();
        locationService.getPositionShipSpeace(getPositions(satelliteList), getDistances(satelliteList));

    }

    private double[] getDistances(List<Satellite> satellitesList){
        return  satellitesList.stream().mapToDouble(satellite -> satellite.getDistance()).toArray();
    }

    private double[][] getPositions(List<Satellite> satellitesList){
        double[][] pointsList = new double[satellitesList.size()][];
        String[] satellitePosition;
        String satelliteName;

        for (int i = 0; i < satellitesList.size(); i++) {
            satelliteName = satellitesList.get(i).getName();
            satellitePosition = environment.getProperty("satellites." + satelliteName + ".position").split(",");
            pointsList[i] = Arrays.stream(satellitePosition)
                    .map(Double::valueOf)
                    .mapToDouble(Double::doubleValue)
                    .toArray();
        }

        return pointsList;
    }


}
