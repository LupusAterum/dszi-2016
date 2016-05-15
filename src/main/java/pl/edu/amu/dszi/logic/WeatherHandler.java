package pl.edu.amu.dszi.logic;

import pl.edu.amu.dszi.abstractClasses.GenericObservableRunnable;
import pl.edu.amu.dszi.model.weather.RainType;
import pl.edu.amu.dszi.model.weather.SunType;
import pl.edu.amu.dszi.model.weather.Weather;

import java.util.Random;

import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.anyEnumValue;
import static pl.edu.amu.dszi.model.weather.SunType.*;

/**
 * Created by lupus on 14.05.16.
 */
public class WeatherHandler extends GenericObservableRunnable {

    private volatile Weather currentWeather;
    private Random random = new Random();
    private static volatile WeatherHandler instance;
    private volatile Long generationRateMillis = 30000L; //default 30 seconds
    private volatile Boolean endThread = false;

    private WeatherHandler() {
        currentWeather = new Weather();
        changeWeather();
        notifyObservers(currentWeather);
    }

    public static synchronized WeatherHandler getInstance() {
        if (instance == null)
            instance = new WeatherHandler();
        return instance;
    }

    @Override
    public void run() {
        while (!endThread) {

            try {
                Thread.sleep(generationRateMillis);
            } catch (InterruptedException e) {
                System.out.println("Exceptilon");
            }
            System.out.println("Wheater Change");
            changeWeather();
            notifyObservers(currentWeather);
        }
    }

    private void changeWeather() {

        currentWeather.setSunType(anyEnumValue(SunType.class));
        currentWeather.setRain(anyEnumValue(RainType.class));

        if (currentWeather.getSunType().compareTo(HOT_SUN) < 0) {
            currentWeather.setTemperature(random.nextDouble() * 20 + 1);
        } else {
            currentWeather.setTemperature(random.nextDouble() * 10 + 20);
        }
        setChanged();
    }

    public void setGenerationRateMillis(Long generationRateMillis) {
        this.generationRateMillis = generationRateMillis;
    }

    public void stop() {
        endThread = true;
    }

}
