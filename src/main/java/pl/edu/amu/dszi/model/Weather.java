package pl.edu.amu.dszi.model;

/**
 * Created by lupus on 14.05.16.
 */
public class Weather {
    public enum RainType {HEAVY_RAIN, MEDIUM_RAIN, LIGHT_RAIN, NO_RAIN, STORM}

    public enum Sky {CLEAR, LIGHT_CLOUDS, MEDIUM_CLOUDS, HEAVY_CLOUDS}

    public enum FogType {NO_FOG, LIGHT, MEDIUM, HEAVY}

    RainType rain;
    Sky sky;
    FogType fog;
    Double temperature;

    public Weather() {
        super();
    }

    public Weather(RainType rain, Sky sky, FogType fog, Double temperature) {
        super();
        this.rain = rain;
        this.sky = sky;
        this.fog = fog;
        this.temperature = temperature;
    }

    //<editor-fold desc="getters and setters">
    public RainType getRain() {
        return rain;
    }

    public void setRain(RainType rain) {
        this.rain = rain;
    }

    public Sky getSky() {
        return sky;
    }

    public void setSky(Sky sky) {
        this.sky = sky;
    }

    public FogType getFog() {
        return fog;
    }

    public void setFog(FogType fog) {
        this.fog = fog;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    //</editor-fold>
}
