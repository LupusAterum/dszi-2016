package pl.edu.amu.dszi.model.weather;

/**
 * Created by lupus on 14.05.16.
 */
public class Weather {

    private RainType rain;
    private SunType sunType;
    private Double temperature;

    public Weather() {
        super();
    }

    public Weather(RainType rain, SunType sunType, Double temperature) {
        super();
        this.rain = rain;
        this.sunType = sunType;
        this.temperature = temperature;
    }

    //<editor-fold desc="getters and setters">
    public RainType getRain() {
        return rain;
    }

    public void setRain(RainType rain) {
        this.rain = rain;
    }

    public SunType getSunType() {
        return sunType;
    }

    public void setSunType(SunType sunType) {
        this.sunType = sunType;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    //</editor-fold>
}
