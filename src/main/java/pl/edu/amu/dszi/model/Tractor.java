package pl.edu.amu.dszi.model;

import pl.edu.amu.dszi.logic.ai.DecisionEvaluator;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.model.weather.WeatherChanger;
import pl.edu.amu.dszi.pkg2016.Main;
import pl.edu.amu.dszi.pkg2016.WeatherObserver;

import static pl.edu.amu.dszi.pkg2016.Main.DEBUG;

import java.util.Observable;
import java.util.TreeMap;

/**
 * Created by softra43 on 20.04.2016.
 */
public class Tractor extends Observable {


    public static final String IRRIGATION = "IRRIGATION";
    public static final String FERTILIZATION = "FERTILIZATION";
    private volatile Location location;
    private TreeMap<String, LevelledDecision> decisions;
    private DecisionEvaluator decisionEvaluator;
    private static volatile Tractor instance;
    private WeatherObserver weatherObserver = new WeatherObserver();


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
//        switch (l) {
//            case HEAVY:
//                f.setIrrigation(f.getIrrigation() + 40);
//                Thread.sleep(4000);
//                break;
//            case MEDIUM:
//                f.setIrrigation(f.getIrrigation() + 25);
//                Thread.sleep(2000);
//                break;
//            case LIGHT:
//                f.setIrrigation(f.getIrrigation() + 10);
//                Thread.sleep(1000);
//                break;
//            case NO:
//                break;
//            default:
//                break;
//        }
        decisions.put(IRRIGATION, l);
        setChanged();
    }

    public void makeFertilizationDecision(Field f) throws Exception {

        LevelledDecision l = decisionEvaluator.calassifyFertilization(f.getPriority(),
                f.getSoilRichness(), f.getIrrigation());
        if (DEBUG) {
            System.out.println("fert dec:" + l);
        }
        decisions.put(FERTILIZATION, l);
        setChanged();
    }

//    switch (l) {
//        case HEAVY:
//            f.setSoilRichness(f.getSoilRichness() + 40);
//            Thread.sleep(4000);
//            break;
//        case MEDIUM:
//            f.setSoilRichness(f.getSoilRichness() + 25);
//            Thread.sleep(2000);
//            break;
//        case LIGHT:
//            f.setSoilRichness(f.getSoilRichness() + 10);
//            Thread.sleep(1000);
//            break;
//        case NO:
//            break;
//        default:
//            break;
//    }
}
