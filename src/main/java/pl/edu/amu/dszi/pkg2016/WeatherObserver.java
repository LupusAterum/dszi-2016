package pl.edu.amu.dszi.pkg2016;

import pl.edu.amu.dszi.model.weather.Weather;
import pl.edu.amu.dszi.model.weather.WeatherChanger;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by lupus on 14.05.16.
 */
public class WeatherObserver implements Observer {
    private Weather currentWeather;
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherChanger) {
            if (arg instanceof Weather) {
                currentWeather = (Weather) arg;
                System.out.println("SUN: " + currentWeather.getSunType());
                System.out.println("RAIN: " + currentWeather.getRain());
            }
        }
    }
    public Weather getWeather() {
        return currentWeather;
    }
}
