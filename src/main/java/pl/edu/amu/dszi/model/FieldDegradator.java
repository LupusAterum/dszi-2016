package pl.edu.amu.dszi.model;

import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;

import java.util.Map;
import java.util.Random;

/**
 * Created by lupus on 20.05.16.
 */
public class FieldDegradator implements Runnable {
    boolean end = false;
    Random r = new Random();
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
        }
    }
    public void scheduleStop() {
        end = true;
    }
}
