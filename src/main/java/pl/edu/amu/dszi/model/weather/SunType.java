package pl.edu.amu.dszi.model.weather;

/**
 * Created by lupus on 15.05.16.
 */
public enum SunType {
    NO_SUN(0), PARTLY_CLOUDY(1), FULL_SUN(2), HOT_SUN(3);

    private int value;

    SunType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
