package pl.edu.amu.dszi.model;

/**
 * Created by lupus on 15.05.16.
 */
public enum LevelDecision {
    NO(0), LIGHT(1), MEDIUM(2), HEAVY(3);

    private int value;
    LevelDecision(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }

    public static LevelDecision getEnumFromInt(int param) {
        switch(param) {
            case 0:
                return NO;
            case 1:
                return LIGHT;
            case 2:
                return MEDIUM;
            case 3:
                return HEAVY;
        }
        return param < 0 ? NO : HEAVY;
    }
}
