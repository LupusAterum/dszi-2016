package pl.edu.amu.dszi.logic.tractor;

import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.model.Tractor;

import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by softra43 on 20.04.2016.
 */
public class MainTractorMovementLogicService {

    volatile Location targetLocation;

    public MainTractorMovementLogicService(Location targetLocation) {

        this.targetLocation = targetLocation;
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Location targetLocation) {

        this.targetLocation = targetLocation;
        Tractor.getInstance().setTargetLocation(targetLocation);
    }

    // https://github.com/rogemus/SZI/blob/master/WaiterMovement.cpp
    // true - jest u celu, w przeciwnym wypadku false
    public boolean calculateTractorTurn() {
        Location tractorLocation = Tractor.getInstance().getLocation();
        int x = tractorLocation.getX();
        int y = tractorLocation.getY();

        Random random = new Random();

        boolean axis;
        if (abs(targetLocation.getX() - tractorLocation.getX()) <= 1 &&
                abs(targetLocation.getY() - tractorLocation.getY()) <= 1) {
            return true;
        } else if (targetLocation.getX() == tractorLocation.getY()) {
            axis = false;
        } else if (targetLocation.getX() == tractorLocation.getY()) {
            axis = true;
        } else {
            axis = (random.nextBoolean());
        }

        int ax = 0;
        int ay = 0;
        if (axis) {
            ax = (tractorLocation.getX() < targetLocation.getX()) ? 1 : -1;
        } else {
            ay = (tractorLocation.getY() < targetLocation.getY()) ? 1 : -1;
        }
        int xToWrite = tractorLocation.getX() + ax;
        int yToWrite = tractorLocation.getY() + ay;
        tractorLocation.setX(xToWrite < 0 ? 0 : xToWrite > 4 ? 4 : xToWrite);
        tractorLocation.setY(yToWrite < 0 ? 0 : yToWrite > 4 ? 4 : yToWrite);
        return false;
    }

}
