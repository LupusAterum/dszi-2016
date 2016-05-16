package pl.edu.amu.dszi.model.weather;

/**
 * Created by lupus on 15.05.16.
 */
public enum RainType {
    NO_RAIN(0), LIGHT_RAIN(1), MEDIUM_RAIN(2), HEAVY_RAIN(3);

    public int value;

    RainType(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
}
