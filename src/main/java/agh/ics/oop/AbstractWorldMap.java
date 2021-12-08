package agh.ics.oop;
import java.util.LinkedHashMap;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    protected Vector2d topR;
    protected Vector2d botL;

    public AbstractWorldMap(int topW, int topH, int botW, int botH) {
        this.topR = new Vector2d(topW, topH);
        this.botL = new Vector2d(botW, botH);
    }
    @Override
    public Map<Vector2d, Animal> getAnimals() {
        return this.animals;
    }
    @Override
    public void positionChanged(Vector2d prevPos, Vector2d newPos) {
        Animal animal = this.animals.get(prevPos);
        this.animals.remove(prevPos);
        this.animals.put(newPos, animal);
    }
    public boolean isOccupied(Vector2d position) { return animals.get(position) != null; }
    public Object objectAt(Vector2d position) { return animals.get(position); }
    @Override
    public boolean place(Animal animal) {
        if(!this.canMoveTo(animal.getPosition())) return false;
        this.animals.put(animal.getPosition(), animal);
        return true;
    }
    public abstract Vector2d[] edges();
    public String toString() {
        Vector2d[] curEdges = edges();
        MapVisualizer mapV = new MapVisualizer(this);
        return mapV.draw(curEdges[0], curEdges[1]);
    }
}
