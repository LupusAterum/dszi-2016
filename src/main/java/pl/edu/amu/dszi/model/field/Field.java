/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dszi.model.field;

/**
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
public class Field implements Comparable<Field> {

    private Double irrigation;
    private Double soilRichness;
    private Location location;
    private Double priority;

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
        this.irrigation = irrigation;
    }

    public Double getSoilRichness() {
        return soilRichness;
    }

    public void setSoilRichness(Double soilRichness) {
        this.soilRichness = soilRichness;
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

    //</editor-fold>
}
