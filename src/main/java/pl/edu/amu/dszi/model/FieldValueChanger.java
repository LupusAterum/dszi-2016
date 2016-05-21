package pl.edu.amu.dszi.model;

import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.model.weather.Weather;
import pl.edu.amu.dszi.model.weather.WeatherChanger;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Created by lupus on 20.05.16.
 */
public class FieldValueChanger extends Observable implements Runnable, Observer {
    boolean end = false;
    Random r = new Random();
    private static FieldValueChanger instance;
    private volatile Weather currentWeather;

    public static synchronized FieldValueChanger getInstance() {
        if (instance == null) {
            instance = new FieldValueChanger();
        }
        return instance;
    }

    private FieldValueChanger() {
        WeatherChanger.getInstance().addObserver(this);
        this.currentWeather = WeatherChanger.getInstance().getCurrentWeather();
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
            int irrDeg = 0, soilDeg = 0;
            switch (currentWeather.getRain()) {
                case HEAVY_RAIN:
                    switch (currentWeather.getSunType()) {
                        case NO_SUN:
                            irrDeg = -(r.nextInt(40));
                            soilDeg = r.nextInt(7);
                            break;
                        case PARTLY_CLOUDY:
                            irrDeg = -(r.nextInt(30));
                            soilDeg = r.nextInt(10);
                            break;
                        case FULL_SUN:
                            irrDeg = -(r.nextInt(20));
                            soilDeg = r.nextInt(15);
                            break;
                        case HOT_SUN:
                            irrDeg = -(r.nextInt(15));
                            soilDeg = r.nextInt(20);
                            break;
                    }
                    break;
                case MEDIUM_RAIN:
                    switch (currentWeather.getSunType()) {
                        case NO_SUN:
                            irrDeg = -(r.nextInt(25));
                            soilDeg = r.nextInt(7);
                            break;
                        case PARTLY_CLOUDY:
                            irrDeg = -(r.nextInt(20));
                            soilDeg = r.nextInt(10);
                            break;
                        case FULL_SUN:
                            irrDeg = -(r.nextInt(15));
                            soilDeg = r.nextInt(10);
                            break;
                        case HOT_SUN:
                            irrDeg = -(r.nextInt(10));
                            soilDeg = r.nextInt(15);
                            break;
                    }
                    break;
                case LIGHT_RAIN:
                    switch (currentWeather.getSunType()) {
                        case NO_SUN:
                            irrDeg = -(r.nextInt(10));
                            soilDeg = r.nextInt(7);
                            break;
                        case PARTLY_CLOUDY:
                            irrDeg = -(r.nextInt(10));
                            soilDeg = r.nextInt(7);
                            break;
                        case FULL_SUN:
                            irrDeg = -(r.nextInt(5));
                            soilDeg = r.nextInt(7);
                            break;
                        case HOT_SUN:
                            irrDeg = -(r.nextInt(5));
                            soilDeg = r.nextInt(7);
                            break;
                    }
                    break;
                case NO_RAIN:
                    switch (currentWeather.getSunType()) {
                        case NO_SUN:
                            irrDeg = r.nextInt(5);
                            soilDeg = r.nextInt(15);
                            break;
                        case PARTLY_CLOUDY:
                            irrDeg = r.nextInt(7);
                            soilDeg = r.nextInt(15);
                            break;
                        case FULL_SUN:
                            irrDeg = r.nextInt(15);
                            soilDeg = r.nextInt(15);
                            break;
                        case HOT_SUN:
                            irrDeg = r.nextInt(25);
                            soilDeg = r.nextInt(15);
                            break;
                    }
                    break;
            }
            for (Map.Entry<Location, Field> entry : FieldHandler.getInstance().getFields().entrySet()) {
                entry.getValue().degrade(irrDeg, soilDeg);
            }
            setChanged();
            notifyObservers();
        }
    }

    public void scheduleStop() {
        end = true;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherChanger) {
            if (arg instanceof Weather) {
                currentWeather = (Weather) arg;
            }
        }
    }
}
