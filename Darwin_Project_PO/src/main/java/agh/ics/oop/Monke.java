package agh.ics.oop;
import java.awt.*;
import java.util.Comparator;
import java.util.Random;

public class Monke{
    private Vector2d monkePosition;
    private MapDirection monkeOrientation;
    public MonkeGen monkeGenotype;
    public int monkeAge = 0;
    public int monkeMinis = 0;
    private int monkeEnergy;
    public final Random random = new Random();
    protected final MonkeMap map;
    private boolean follow = false;

    public Monke(MonkeMap map, Vector2d position, int energy) {
        this.map = map;
        this.monkePosition = position;
        this.monkeOrientation = MapDirection.NORTH.rotation(random.nextInt(8));
        this.monkeGenotype = new MonkeGen();
        this.monkeEnergy = energy;

    }
    public Monke(MonkeMap map, Vector2d position, Monke parent1, Monke parent2, int energy) {
        this.map = map;
        this.monkePosition = position;
        this.monkeOrientation = MapDirection.NORTH.rotation(random.nextInt(8));
        this.monkeGenotype = new MonkeGen(parent1, parent2);
        this.monkeEnergy = energy;
    }
    public void moveMonke(MapDirection monkeDirection, boolean folded) {
        if(!folded)
            this.monkePosition = this.monkePosition.add(monkeDirection.toUnitVector());
        else {
            Vector2d newMonkePosition = this.monkePosition.add(monkeDirection.toUnitVector());
            if(newMonkePosition.follows(map.mapBottomLeft) && newMonkePosition.precedes(map.mapTopRight))
                this.monkePosition = newMonkePosition;
            else {
                int x, y;
                if(newMonkePosition.x < 0)
                    x = map.getMapWidth() - 1;
                else if(newMonkePosition.x > map.getMapWidth() - 1)
                    x = 0;
                else x = newMonkePosition.x;
                if(newMonkePosition.y < 0)
                    y = map.getMapHeight() - 1;
                else if(newMonkePosition.y > map.getMapHeight() - 1)
                    y = 0;
                else y = newMonkePosition.y;
                this.monkePosition = new Vector2d(x, y);
            }
        }
    }
    public void spinMonke(int spinVal) {
        this.monkeOrientation = this.monkeOrientation.rotation(spinVal);
    }
    public Monke reproduceMonke(Monke other) {
        this.monkeMinis += 1;
        other.monkeMinis += 1;
        this.reduceMonkeEnergy(this.monkeEnergy / 4);
        other.reduceMonkeEnergy(other.monkeEnergy / 4);
        int babyMonkeEnergy = this.monkeEnergy / 4 + other.monkeEnergy / 4;
        return new Monke(map, this.monkePosition, this, other, babyMonkeEnergy);
    }

    public void reduceMonkeEnergy(int energy) {
        this.monkeEnergy -= energy;
    }
    public void addMonkeEnergy(int energy) {
        this.monkeEnergy += energy;
    }
    public int getMonkeEnergy() {
        return monkeEnergy;
    }
    public Vector2d getPosition() {
        return monkePosition;
    }
    public MapDirection getOrientation() {
        return monkeOrientation;
    }
    public String toString() {
        return "*";
    }
    public Color getMonkeColor() {
        float colorVal = (float) 255 / map.getMonkeStartEnergy();
        int monkeColor = Math.max(Math.min(254, (int) colorVal * this.getMonkeEnergy()), 0);
        return new Color(0, monkeColor, monkeColor);
    }
    public void makeFollow() {
        this.follow = true;
    }
    public void stopFollow() {
        this.follow = false;
    }
    public boolean checkFollow() {
        return this.follow;
    }
}

class compareMonkesEnergy implements Comparator<Monke> {
    @Override
    public int compare(Monke o1, Monke o2) {
        return o1.getMonkeEnergy() - o2.getMonkeEnergy();
    }
}
