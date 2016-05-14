package pl.edu.amu.dszi.logic;

import pl.edu.amu.dszi.abstractClasses.GenericObservableRunnable;
import pl.edu.amu.dszi.model.Weather;

import java.util.Random;
import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.*;

/**
 * Created by lupus on 14.05.16.
 */
public class WeatherHandler extends GenericObservableRunnable {

    private static final Object lock = new Object();
    private volatile Weather currentWeather;
    private Random random = new Random();
    private static volatile WeatherHandler instance;
    private WeatherHandler() {
        currentWeather = new Weather();
        changeWeather();
        notifyObservers(currentWeather);
    }
    public static synchronized WeatherHandler getInstance() {
            if(instance == null)
                instance = new WeatherHandler();
        return instance;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("in thread");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Exception");
            }
            changeWeather();
            notifyObservers(currentWeather);
        }
    }

    private void changeWeather() {
        currentWeather.setFog(anyEnumValue(Weather.FogType.class));
        currentWeather.setRain(anyEnumValue(Weather.RainType.class));
        currentWeather.setSky(anyEnumValue(Weather.Sky.class));
        currentWeather.setTemperature(random.nextDouble() * 30 + 1);
        setChanged();
    }



}
