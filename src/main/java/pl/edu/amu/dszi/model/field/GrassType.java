package pl.edu.amu.dszi.model.field;

/**
 * Created by softra43 on 20.04.2016.
 */
public enum GrassType {

    OK_GRASS(0, 25),
    NEGLECTED_GRASS(26, 50),
    URGENT_ATTENTION_GRASS(51, 75),
    TO_TOTAL_RECLAMATION_GRASS(76, 100),
    TREE(-1, -1);

    public Integer a;

    public Integer b;

    GrassType(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public boolean inRange(int x) {
        return x >= a && x <= b;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }
}
