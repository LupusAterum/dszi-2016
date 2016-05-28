/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dszi.logic.tractor;

import pl.edu.amu.dszi.abstractClasses.FieldPriorityHandler;
import pl.edu.amu.dszi.abstractClasses.Location2D;

import java.io.IOException;

import pl.edu.amu.dszi.model.field.Field;
import net.sourceforge.jFuzzyLogic.FIS;

import static net.sourceforge.jFuzzyLogic.FIS.*;

/**
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
public class TractorFieldPriorityHandler implements FieldPriorityHandler {

    private final FIS fuzzyLogicHandler;
    private Integer xSize;
    private Integer ySize;

    public TractorFieldPriorityHandler(String fclFileName, boolean verboseMode, Integer xSize, Integer ySize) throws IOException {
        fuzzyLogicHandler = load(fclFileName, verboseMode);
        if (fuzzyLogicHandler == null) {
            throw new IOException("Cannot load file " + fclFileName);
        }
        this.xSize = xSize;
        this.ySize = ySize;
    }

    @Override
    public Double getFieldPriority(Field field, Location2D location) {
        if (!field.getWalkable()) {
           return  -1.0d;
        }
        fuzzyLogicHandler.setVariable("irrigation", field.getIrrigation());
        fuzzyLogicHandler.setVariable("soilRichness", field.getSoilRichness());
        Double calculatedDistance;
        Double manhattanDistance = (double) location.getManhattanDistanceTo(field.getLocation());

        calculatedDistance = (manhattanDistance / maxDistance()) * 100;
        fuzzyLogicHandler.setVariable("distance", calculatedDistance);

        fuzzyLogicHandler.evaluate();

        Double priority = fuzzyLogicHandler.getVariable("priority").getValue();
        return priority;
    }
    @Override
    public Double getFieldPriorityWithWeights(Field field, Location2D location, double irrWeight, double soilWeight, double distanceWeight) {
        if (!field.getWalkable()) {
           return  -1.0d;
        }
        fuzzyLogicHandler.setVariable("irrigation", field.getIrrigation()*irrWeight);
        fuzzyLogicHandler.setVariable("soilRichness", field.getSoilRichness()*soilWeight);
        Double calculatedDistance;
        Double manhattanDistance = (double) location.getManhattanDistanceTo(field.getLocation());

        calculatedDistance = (manhattanDistance / maxDistance()) * 100;
        fuzzyLogicHandler.setVariable("distance", calculatedDistance*distanceWeight);

        fuzzyLogicHandler.evaluate();

        Double priority = fuzzyLogicHandler.getVariable("priority").getValue();
        return priority;
    }

    private Double maxDistance() {
        return (double) xSize + ySize - 2.0;
    }

    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public Integer getxSize() {
        return xSize;
    }

    public void setxSize(Integer xSize) {
        this.xSize = xSize;
    }

    public Integer getySize() {
        return ySize;
    }

    public void setySize(Integer ySize) {
        this.ySize = ySize;
    }
    //</editor-fold>

}
