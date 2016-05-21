package pl.edu.amu.dszi.model;

import pl.edu.amu.dszi.logic.ai.DecisionEvaluator;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.model.weather.Weather;
import pl.edu.amu.dszi.model.weather.WeatherChanger;
import pl.edu.amu.dszi.pkg2016.Main;
import pl.edu.amu.dszi.pkg2016.WeatherObserver;
import sun.reflect.generics.tree.Tree;

import static pl.edu.amu.dszi.pkg2016.Main.DEBUG;

import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

/**
 * Created by softra43 on 20.04.2016.
 */
public class Tractor extends Observable implements Observer {


    public static final String IRRIGATION = "IRRIGATION";
    public static final String FERTILIZATION = "FERTILIZATION";
    public static final String NEW_TARGET = "NEW_TARGET";
    private volatile Location location;
    private TreeMap<String, LevelledDecision> decisions;
    private DecisionEvaluator decisionEvaluator;
    private static volatile Tractor instance;
    private WeatherObserver weatherObserver = new WeatherObserver();
    private volatile Location targetLocation;

    public static synchronized Tractor getInstance() {
        if (instance == null)
            try {
                instance = new Tractor();
            } catch (Exception e) {
                return null;
            }
        return instance;
    }

    private Tractor() throws Exception {
        decisions = new TreeMap<>();
        decisionEvaluator = new DecisionEvaluator();
        decisions.put(FERTILIZATION, LevelledDecision.UNKNOWN);
        decisions.put(IRRIGATION, LevelledDecision.UNKNOWN);
        WeatherChanger.getInstance().addObserver(weatherObserver);
        WeatherChanger.getInstance().addObserver(this);
        targetLocation = new Location(0, 0);

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        setChanged();
        notifyObservers(location);
    }

    public void makeIrrigationDecision(Field f) throws Exception {

        LevelledDecision l = decisionEvaluator.classifyIrrigation(f.getIrrigation(),
                weatherObserver.getWeather().getSunType(), weatherObserver.getWeather().getRain());
        if (DEBUG) {
            System.out.println("irrDec: " + l);
        }
        switch (l) {
            case HEAVY:
                f.setIrrigation(f.getIrrigation() + 40);
                Thread.sleep(4000);
                break;
            case MEDIUM:
                f.setIrrigation(f.getIrrigation() + 25);
                Thread.sleep(2000);
                break;
            case LIGHT:
                f.setIrrigation(f.getIrrigation() + 10);
                Thread.sleep(1000);
                break;
            case NO:
                break;
            default:
                break;
        }
        decisions.put(IRRIGATION, l);
        setChanged();
        notifyObservers(IRRIGATION);
    }

    public void makeFertilizationDecision(Field f) throws Exception {

        LevelledDecision l = decisionEvaluator.calassifyFertilization(f.getPriority(),
                f.getSoilRichness(), f.getIrrigation());
        if (DEBUG) {
            System.out.println("fert dec:" + l);
        }
        switch (l) {
            case HEAVY:
                f.setSoilRichness(f.getSoilRichness() + 40);
                Thread.sleep(4000);
                break;
            case MEDIUM:
                f.setSoilRichness(f.getSoilRichness() + 25);
                Thread.sleep(2000);
                break;
            case LIGHT:
                f.setSoilRichness(f.getSoilRichness() + 10);
                Thread.sleep(1000);
                break;
            case NO:
                break;
            default:
                break;
        }
        decisions.put(FERTILIZATION, l);
        setChanged();
        notifyObservers(FERTILIZATION);
    }

    public void setTargetLocation(Location target) {
        this.targetLocation = target;
        setChanged();
        notifyObservers(NEW_TARGET);
    }
    public Location getTargetLocation() {
        return this.targetLocation;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherChanger) {
            if(arg instanceof Weather) {
                setChanged();
                notifyObservers(arg);
            }
        }
    }
    public TreeMap<String, LevelledDecision> getDecisions() {
        return this.decisions;
    }
}
