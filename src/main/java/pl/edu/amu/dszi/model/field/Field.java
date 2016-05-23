/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dszi.model.field;

import org.xguzm.pathfinding.NavigationNode;
import org.xguzm.pathfinding.grid.NavigationGridGraphNode;

/**
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
public class Field implements Comparable<Field> {

    private Double irrigation;
    private Double soilRichness;
    private Location location;
    private Double priority;

    public Boolean getWalkable() {
        return walkable;
    }

    public void setWalkable(Boolean walkable) {
        this.walkable = walkable;
    }

    private Boolean walkable;

    public Field(Double irrigation, Double soilRichness) {
        this.irrigation = irrigation;
        this.soilRichness = soilRichness;
        this.location = new Location(-1, -1);
    }


    public Field(Double irrigation, Double soilRichness, Integer x, Integer y) {
        this.irrigation = irrigation;
        this.soilRichness = soilRichness;
        this.location = new Location(x, y);
    }

    // <editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public Double getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(Double irrigation) {
        if(irrigation < 0) this.irrigation = 0d;
        else if(irrigation > 100) this.irrigation = 100d;
        else this.irrigation = irrigation;
    }

    public Double getSoilRichness() {
        return soilRichness;
    }

    public void setSoilRichness(Double soilRichness) {
        if(soilRichness < 0) this.soilRichness = 0d;
        else if(soilRichness > 100) this.soilRichness = 100d;
        else this.soilRichness = soilRichness;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }


    public int compareTo(Field o) {
        double result = this.getPriority() - o.getPriority();
        return (int) result;
    }
    public void degrade(int irrDeg, int soilDeg) {
        if(walkable) {
            setIrrigation(irrigation - irrDeg);
            setSoilRichness(soilRichness - soilDeg);
        }
    }
    private void doNothing() {

    }
    //</editor-fold>
}
