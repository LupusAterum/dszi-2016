/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dszi.model.field;

import pl.edu.amu.dszi.abstractClasses.Location2D;

/**
 *
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
public class Location extends Location2D implements Comparable<Location> {

    public Location(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    public Double getEuclideanDistanceTo(Location2D loc) {
        Double a = Math.abs((double) (loc.getX() - this.x));
        Double b = Math.abs((double) (loc.getY() - this.y));
        return Math.sqrt((a*a + b*b));
        
    }

    @Override
    public Integer getManhattanDistanceTo(Location2D loc) {
        Integer a = Math.abs(loc.getX() - this.x);
        Integer b = Math.abs(loc.getY() - this.y);
        return a+b;
    }

    @Override
    public int compareTo(Location o) {
        if(this.x == o.x) {
            if(this.y == o.y) {
                return 0;
            }
            else if(this.y < o.y) {
                return -1;
            }
            else if(this.y > o.y) {
                return 1;
            }
        } else {
            if(this.x < o.x) {
                return -1;
            }
            else if(this.x > o.x) {
                return 1;
            }
        }
        return 0;
    }
}
