package pl.edu.amu.dszi.util;

/**
 * Created by eryk on 22.05.16.
 */
public class Pair {

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

    public Pair(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    private Integer a;

    private Integer b;

    @Override
    public boolean equals(Object o) {
        Pair p = (Pair) o;
        return (this.getA().intValue() == p.getA().intValue()) && (this.getB().intValue() == p.getB().intValue());
    }

}
