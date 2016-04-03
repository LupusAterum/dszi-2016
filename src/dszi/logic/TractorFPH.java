/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dszi.logic;

import dszi.abstractClasses.FieldPriorityHandler;
import dszi.abstractClasses.Location2D;
import java.awt.Dimension;
import java.io.IOException;
import net.sourceforge.jFuzzyLogic.FIS;

/**
 *
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
public class TractorFPH implements FieldPriorityHandler {
    
    private final FIS fuzzyLogicHandler;
    private Integer xSize;
    private Integer ySize;
    public TractorFPH(String fclFileName, boolean verboseMode, Integer xSize, Integer ySize) throws IOException {
        fuzzyLogicHandler = FIS.load(fclFileName, verboseMode);
        if (fuzzyLogicHandler == null) {
            throw new IOException("Cannot load file " + fclFileName);
        }
        this.xSize = xSize;
        this.ySize = ySize;
    }
    
    @Override
    public Double getFieldPriority(Field field, Location2D location) {
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
