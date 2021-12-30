package agh.ics.oop;

public class Banana {
    private final Vector2d bananaPosition;
    private final int bananaEnergy;
    public int bananaEaters = 0;

    public Banana(Vector2d position, int energy) {
        this.bananaPosition = position;
        this.bananaEnergy = energy;
    }
    public Vector2d getPosition() {
        return bananaPosition;
    }
    public String toString() {
        return "(";
    }
    public int getBananaEnergy() {
        return this.bananaEnergy;
    }
    public void addEater() {
        this.bananaEaters += 1;
    }
}
