/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dszi.abstractClasses;

/**
 *
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
public abstract class Location2D {
    protected Integer x;
    protected Integer y; 

    public Location2D(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract Double getEuclideanDistanceTo(Location2D loc);
    public abstract Integer getManhattanDistanceTo(Location2D loc);
     // <editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
    //</editor-fold>
    
}
