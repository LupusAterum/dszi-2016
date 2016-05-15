package pl.edu.amu.dszi.logic;

import pl.edu.amu.dszi.abstractClasses.FieldPriorityHandler;
import pl.edu.amu.dszi.model.Field;
import pl.edu.amu.dszi.model.Tractor;
import pl.edu.amu.dszi.pkg2016.WindowManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by softra43 on 20.04.2016.
 */
public class MainFuzzyLogicServiceHandler implements Runnable{

    private MainTractorMovementLogicService mainTractorMovementLogicService;

    private WindowManager windowManager;

    public ArrayList<Field> fields;

    public boolean end;

    public FieldPriorityHandler handler;

    public MainFuzzyLogicServiceHandler() {
        init();
        this.end = false;
    }

    private void init() {
        try {
            handler = new TractorFieldPriorityHandler("res/fieldHandling.fcl", true, 5, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r = new Random();
        fields = new ArrayList<>();
        for (int x = 1; x < 5; x++) {
            for (int y = 1; y < 5; y++) {
                Double soil = r.nextDouble() * 100d;
                Double irr = r.nextDouble() * 100d;
                Field f = new Field(irr, soil, x, y);
                fields.add(f);
            }
        }

        Location tractorLocation = new Location(3, 2);
        System.out.println("Maximum distance: 8");
        for (Field f : fields) {
//            String toFormat = "Location x: %d, y: %d | irrigation: %.3f | soilRichness: %.3f | ManhattanDistance: %d | Priority: %.3f\n";
            String toFormat = "%d,%.3f,%.3f,%.3f\n";
            f.setPriority(handler.getFieldPriority(f, tractorLocation));
//            System.out.printf(toFormat,
//                    f.getLocation().getX(), f.getLocation().getY(),
//                    f.getIrrigation(), f.getSoilRichness(),
//                    f.getLocation().getManhattanDistanceTo(tractorLocation),
//                    f.getPriority());
            System.out.printf(toFormat, f.getLocation().getManhattanDistanceTo(tractorLocation),
                    f.getIrrigation(), f.getSoilRichness(), f.getPriority());
        }

        Tractor tractor = new Tractor(new Location(1, 1));

        windowManager = new WindowManager();
        windowManager.repaint();
        windowManager.initMap(fields, tractor);

        mainTractorMovementLogicService
                = new MainTractorMovementLogicService(maxPriorityField().getLocation(), tractor);
    }

    private Field maxPriorityField() {
        Collections.sort(fields);
        return fields.get(fields.size() - 1);
    }

    @Override
    public void run() {
        do {
            mainTractorMovementLogicService.calculateTractorTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            windowManager.repaint();
        } while (!end);
    }

}
