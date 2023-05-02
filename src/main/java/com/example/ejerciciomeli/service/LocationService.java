package com.example.ejerciciomeli.service;

import com.example.ejerciciomeli.model.Position;
import com.example.ejerciciomeli.model.Satellite;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private Environment environment;

    public Position getPositionShipSpeace(double[][] positions, double[] distances) {

        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver solver =
                new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        return new Position(solver.solve().getPoint().toArray());
    }

    protected double[] getDistances(List<Satellite> satellitesList){
        return  satellitesList.stream().mapToDouble(Satellite::getDistance).toArray();
    }

    protected double[][] getPositions(List<Satellite> satellitesList){
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
