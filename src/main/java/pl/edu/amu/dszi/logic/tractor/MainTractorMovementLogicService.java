package pl.edu.amu.dszi.logic.tractor;

import org.jfree.util.StringUtils;
import org.omg.CORBA.SystemException;
import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;
import pl.edu.amu.dszi.logic.TractorMovementNavigationGraph;
import pl.edu.amu.dszi.model.FieldHandler;
import pl.edu.amu.dszi.model.Tractor;
import pl.edu.amu.dszi.model.field.Location;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.StrictMath.abs;

/**
 * Created by softra43 on 20.04.2016.
 */
public class MainTractorMovementLogicService {

    volatile Location targetLocation;

    public MainTractorMovementLogicService() {
        Location target = FieldHandler.getInstance().maxPriorityField().getLocation();
        Tractor.getInstance().setTargetLocation(target);
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

//    int x = tractorLocation.getX();
//        int y = tractorLocation.getY();
//
//        Random random = new Random();
//
//        boolean axis;
//        if (abs(targetLocation.getX() - tractorLocation.getX()) <= 1 &&
//                abs(targetLocation.getY() - tractorLocation.getY()) <= 1) {
//            return true;
//        } else if (targetLocation.getX() == tractorLocation.getY()) {
//            axis = false;
//        } else if (targetLocation.getX() == tractorLocation.getY()) {
//            axis = true;
//        } else {
//            axis = (random.nextBoolean());
//        }
//
//        int ax = 0;
//        int ay = 0;
//        if (axis) {
//            ax = (tractorLocation.getX() < targetLocation.getX()) ? 1 : -1;
//        } else {
//            ay = (tractorLocation.getY() < targetLocation.getY()) ? 1 : -1;
//        }
//        int xToWrite = tractorLocation.getX() + ax;
//        int yToWrite = tractorLocation.getY() + ay;
//        tractorLocation.setX(xToWrite < 1 ? 1 : xToWrite > 10 ? 10 : xToWrite);
//        tractorLocation.setY(yToWrite < 1 ? 1 : yToWrite > 10 ? 10 : yToWrite);
//        Location target = Tractor.getInstance().getTargetLocation();
//        int x = tractorLocation.getX();
//        int y = tractorLocation.getY();
//        int tx = target.getX();
//        int ty = target.getY();
//        int xToWrite = x;
//        int yToWrite = y;
//        boolean toReturn = false;
//        if (tx == x && ty == y) return true;
//        if (tx > x) {
//            xToWrite++;
//        } else if (tx < x) {
//            xToWrite--;
//        } else if (tx == x) {
//            if (ty > y) {
//                yToWrite++;
//            } else if (ty < y) {
//                yToWrite--;
//            } else {
//                return true;
//            }
//        }
//        Location l = new Location(xToWrite, yToWrite);
//        Tractor.getInstance().setLocation(l);
        Location location2 = Tractor.getInstance().getTargetLocation();
        processAstar(tractorLocation, location2);
        Location location = new Location(pathToEnd.get(i).getX(), pathToEnd.get(i).getY());
        Tractor.getInstance().setLocation(location);
        if (i == (pathToEnd.size() - 1)) {
            i = 0;
            pathToEnd = null;
            return true;
        }
        i++;
        return false;
//        return toReturn;
    }

    int i = 0;

    List<GridCell> pathToEnd;

    private void processAstar(Location inputLocation, Location targetLocation) {
        GridCell[][] cells = new GridCell[7][7];
        NavigationGrid<GridCell> navigationGrid = new TractorMovementNavigationGraph(
                FieldHandler.getInstance().getFields());

        GridFinderOptions opt = new GridFinderOptions();
        opt.allowDiagonal = false;

        AStarGridFinder<GridCell> finder = new AStarGridFinder<GridCell>(GridCell.class, opt);

        if (pathToEnd == null) {
            pathToEnd = finder.findPath(
                    inputLocation.getX(), inputLocation.getY(),
                    targetLocation.getX(), targetLocation.getY(),
                    navigationGrid);
        }

    }

}
