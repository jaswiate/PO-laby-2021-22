package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    private List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public List<Animal> getAnimals() {
        return this.animals;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && position.x >= 0 && position.x < this.width && position.y >= 0 && position.y < this.height;
    }
    @Override
    public boolean place(Animal animal) {
        if(!this.canMoveTo(animal.getPosition())) return false;
        this.animals.add(animal);
        return true;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal : animals) {
            if(animal.isAt(position)) return true;
        }
        return false;
    }
    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals) {
            if(animal.isAt(position)) return animal;
        }
        return null;
    }
    public String toString() {
        MapVisualizer mapV = new MapVisualizer(this);
        return mapV.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }
}
