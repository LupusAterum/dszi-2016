package pl.edu.amu.dszi.logic.ai;

import pl.edu.amu.dszi.abstractClasses.FieldPriorityHandler;
import pl.edu.amu.dszi.model.FieldValueChanger;
import pl.edu.amu.dszi.model.FieldHandler;
import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.logic.tractor.MainTractorMovementLogicService;
import pl.edu.amu.dszi.logic.tractor.TractorFieldPriorityHandler;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.Tractor;
import pl.edu.amu.dszi.model.weather.WeatherChanger;
import pl.edu.amu.dszi.pkg2016.Main;
import pl.edu.amu.dszi.pkg2016.WeatherObserver;
import pl.edu.amu.dszi.view.WindowManager;

import java.io.IOException;
import java.util.*;

/**
 * Created by softra43 on 20.04.2016.
 */
public class MainFuzzyLogicServiceHandler implements Runnable, Observer {

    private MainTractorMovementLogicService mainTractorMovementLogicService;
    private DecisionEvaluator decisionEvaluator;
    private WindowManager windowManager;
    private Tractor tractor;
    private FieldHandler fieldHandler;
    WeatherObserver weatherObserver = new WeatherObserver();
    Thread weatherChangerThread;

    public boolean end;

    public FieldPriorityHandler handler;

    public MainFuzzyLogicServiceHandler() throws Exception {
        init();
        this.end = false;
    }

    private void init() throws Exception {
        try {
            handler = new TractorFieldPriorityHandler("res/fieldHandling.fcl", true, 8, 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fieldHandler = FieldHandler.getInstance();
        FieldValueChanger.getInstance().addObserver(this);

        decisionEvaluator = new DecisionEvaluator();
        Location tractorLocation = new Location(1, 1);
        calculatePriorities(tractorLocation, fieldHandler.getFields());

        tractor = Tractor.getInstance();
        tractor.setLocation(tractorLocation);
        windowManager = new WindowManager();
        windowManager.repaint();
        windowManager.initMap(this.tractor);


        weatherChangerThread = new Thread(WeatherChanger.getInstance());
        WeatherChanger.getInstance().addObserver(weatherObserver);
        weatherChangerThread.start();

        mainTractorMovementLogicService
                = new MainTractorMovementLogicService();
    }

    private void calculatePriorities(Location tractorLocation, TreeMap<Location, Field> fields) {
        for (Map.Entry<Location, Field> entry : fields.entrySet()) {
            Field f = entry.getValue();
            fieldHandler.setFieldPriorityAt(entry.getKey(), handler.getFieldPriority(f, tractorLocation));

            if (Main.DEBUG) {
                String toFormat = "D: %d, I: %.3f, R: %.3f, P: %.3f, X: %d, Y: %d\n";
                System.out.printf(toFormat, f.getLocation().getManhattanDistanceTo(tractorLocation),
                        f.getIrrigation(), f.getSoilRichness(), f.getPriority(), f.getLocation().getX(), f.getLocation().getY());
            }
        }
        fieldHandler.notifyThatAllFieldsChanged();
    }


    private Field getFieldWhichTractorIsStandingOn() {
        Location l = Tractor.getInstance().getLocation();
        return fieldHandler.getFieldAt(l);
    }

    @Override
    public void run() {
        do {
            while (!mainTractorMovementLogicService.calculateTractorTurn()) {
                try {
                    Thread.sleep(1000);
                    if (Main.DEBUG) {
                        System.out.printf("X: %d Y: %d\n", Tractor.getInstance().getLocation().getX(), Tractor.getInstance().getLocation().getY());
                        System.out.printf("Target X: %d Y: %d\n", Tractor.getInstance().getTargetLocation().getX(), Tractor.getInstance().getTargetLocation().getY());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                windowManager.repaint();
            }
            Field f = getFieldWhichTractorIsStandingOn();
            try {
                if(Main.DEBUG) {
                    System.out.printf("X: %d Y: %d\n", Tractor.getInstance().getLocation().getX(), Tractor.getInstance().getLocation().getY());
                    System.out.printf("Target X: %d Y: %d\n", Tractor.getInstance().getTargetLocation().getX(), Tractor.getInstance().getTargetLocation().getY());
                }
                Tractor.getInstance().makeFertilizationDecision(f);
                Tractor.getInstance().makeIrrigationDecision(f);
                calculatePriorities(Tractor.getInstance().getLocation(), fieldHandler.getFields());
                Tractor.getInstance().setTargetLocation(fieldHandler.maxPriorityField().getLocation());
                windowManager.repaint();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (!end);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof FieldValueChanger) {
            if (arg == null) {

                calculatePriorities(Tractor.getInstance().getLocation(), fieldHandler.getFields());
            }
        }
    }
}
