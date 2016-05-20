package pl.edu.amu.dszi.model;

import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Created by lupus on 20.05.16.
 */
public class FieldDegradator extends Observable implements Runnable {
    boolean end = false;
    Random r = new Random();
    private static FieldDegradator instance;
    public static synchronized FieldDegradator getInstance() {
        if(instance == null) {
            instance = new FieldDegradator();
        }
        return instance;
    }
    private FieldDegradator() {

    }
    @Override
    public void run() {
        while (!end) {
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("FIELD DEGRADATION!!!!!!");
            for (Map.Entry<Location, Field> entry : FieldHandler.getInstance().getFields().entrySet()) {
                entry.getValue().degrade(r.nextInt(26), r.nextInt(11));
            }
            setChanged();
            notifyObservers();
        }
    }
    public void scheduleStop() {
        end = true;
    }
}
