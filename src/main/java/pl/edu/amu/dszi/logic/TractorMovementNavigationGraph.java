package pl.edu.amu.dszi.logic;

import org.xguzm.pathfinding.NavigationGraph;
import org.xguzm.pathfinding.NavigationNode;
import org.xguzm.pathfinding.PathFinderOptions;
import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import pl.edu.amu.dszi.model.FieldHandler;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by eryk on 23.05.16.
 */
public class TractorMovementNavigationGraph extends NavigationGrid<GridCell> {

    private GridCell[][] gridCell = new GridCell[7][7];

    public TractorMovementNavigationGraph(TreeMap<Location, Field> treeMap) {
        for (Map.Entry<Location, Field> entry : treeMap.entrySet()) {
            int x = entry.getValue().getLocation().getX();
            int y = entry.getValue().getLocation().getY();
            gridCell[x][y] = new GridCell(x, y, entry.getValue().getWalkable());
        }
        for (Pair pair : FieldHandler.getInstance().treeLocations) {
            int x = pair.getA();
            int y = pair.getB();
            gridCell[x][y] = new GridCell(x, y, false);
        }
        nodes = gridCell;
        setHeight(7);
        setWidth(7);
    }

    @Override
    public boolean isWalkable(GridCell node) {
        return node.isWalkable();
    }

}
