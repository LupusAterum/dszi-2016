package pl.edu.amu.dszi.model;

import pl.edu.amu.dszi.logic.Location;

/**
 * Created by softra43 on 20.04.2016.
 */
public class Tractor {

    private Location location;

    public Tractor(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
