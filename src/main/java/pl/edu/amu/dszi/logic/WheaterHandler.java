package pl.edu.amu.dszi.logic;

import pl.edu.amu.dszi.abstractClasses.GenericObservableRunnable;
import pl.edu.amu.dszi.model.Wheater;

import java.util.Random;
import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.*;

/**
 * Created by lupus on 14.05.16.
 */
public class WheaterHandler extends GenericObservableRunnable {

    private volatile Wheater currentWheater;
    private Random random = new Random();
    private long refreshIntervalMillis;

    public WheaterHandler(long refreshIntervalMillis) {
        this.refreshIntervalMillis = refreshIntervalMillis;
        this.currentWheater = new Wheater();
        changeWeather();
        random.setSeed(System.currentTimeMillis());
    }
    public WheaterHandler(Wheater initWheater, long refreshIntervalMillis) {
        this.currentWheater = initWheater;
        random.setSeed(System.currentTimeMillis());
    }


    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(refreshIntervalMillis);
            } catch (InterruptedException e) {
                //do nothing
            }
            changeWeather();
        }
    }

    private void changeWeather() {
        currentWheater.setFog(anyEnumValue(Wheater.FogType.class));
        currentWheater.setRain(anyEnumValue(Wheater.RainType.class));
        currentWheater.setSky(anyEnumValue(Wheater.Sky.class));
        currentWheater.setTemperature(random.nextDouble() * 30 + 1);

    }


}
