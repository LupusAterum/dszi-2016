package pl.edu.amu.dszi.model;

import javafx.collections.ObservableList;
import pl.edu.amu.dszi.abstractClasses.FieldPriorityHandler;
import pl.edu.amu.dszi.logic.tractor.TractorFieldPriorityHandler;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;

import java.io.IOException;
import java.util.*;

/**
 * Created by lupus on 19.05.16.
 */
public class FieldHandler extends Observable {
    private static FieldHandler instance;
    private volatile TreeMap<Location, Field> fields;
    private Random r = new Random();
    private FieldPriorityHandler handler;
    private FieldHandler() {
        fields = new TreeMap<>();
        try {
            handler = new TractorFieldPriorityHandler("res/fieldHandling.fcl", true, 8, 8);
        } catch (IOException e) {
            System.out.println("FATAL: CANNOT LOAD FCL FILE");
        }
        generateFields();

    }

    public static synchronized FieldHandler getInstance() {
        if (instance == null)
            instance = new FieldHandler();
        return instance;
    }

    private synchronized void generateFields() {
        for (int x = 1; x < 8; x++) {
            for (int y = 1; y < 8; y++) {
                Location l = new Location(x, y);
                Double soil = r.nextDouble() * 100d;
                Double irr = r.nextDouble() * 100d;
                Field f = new Field(irr, soil, x, y);
                fields.put(l, f);
            }

        }
        setChanged();
        notifyObservers(fields);
    }
    public synchronized void setFieldPriorityAt(Location location, double priority) {
        fields.get(location).setPriority(priority);
        setChanged();
        notifyObservers(fields);
    }

    public synchronized Field getFieldAt(Location location) {
        return fields.get(location);
    }

    public synchronized TreeMap<Location, Field> getFields() {
        return this.fields;
    }

    public synchronized Field maxPriorityField() {
        double max = 0;
        Location l = new Location(0, 0);
        for (Map.Entry<Location, Field> entry : fields.entrySet()) {
            if (max < entry.getValue().getPriority()) {
                max = entry.getValue().getPriority();
                l = entry.getKey();
            }
        }
        return fields.get(l);
    }

    public synchronized void calculatePriorites(Location tractorLocation) {

    }
}
