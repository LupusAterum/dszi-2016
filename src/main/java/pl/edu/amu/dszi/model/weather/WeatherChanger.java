package pl.edu.amu.dszi.model.weather;

import pl.edu.amu.dszi.abstractClasses.GenericObservableRunnable;

import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.anyEnumValue;

/**
 * Created by lupus on 14.05.16.
 */
public class WeatherChanger extends GenericObservableRunnable {

    private volatile Weather currentWeather;
    private static volatile WeatherChanger instance;
    private volatile Long generationRateMillis = 30000L; //default 30 seconds
    private volatile Boolean endThread = false;

    private WeatherChanger() {
        currentWeather = new Weather();
        changeWeather();
        notifyObservers(currentWeather);
    }

    public static synchronized WeatherChanger getInstance() {
        if (instance == null)
            instance = new WeatherChanger();

        return instance;
    }

    @Override
    public void run() {
        while (!endThread) {
            changeWeather();
            notifyObservers(currentWeather);
            System.out.println("Wheater Change");
            try {
                Thread.sleep(generationRateMillis);
            } catch (InterruptedException e) {
                System.out.println("Exceptilon");
            }


        }
    }

    private void changeWeather() {

        currentWeather.setSunType(anyEnumValue(SunType.class));
        currentWeather.setRain(anyEnumValue(RainType.class));
        setChanged();
    }

    public void setGenerationRateMillis(Long generationRateMillis) {
        this.generationRateMillis = generationRateMillis;
    }

    public void stop() {
        endThread = true;
    }

    public Weather getCurrentWeather() {
        return this.currentWeather;
    }
}
