package agh.ics.oop;
import java.util.LinkedHashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;
    private Map<Vector2d, Animal> animals = new LinkedHashMap<>();

    public RectangularMap(int width, int height) {
        super(width - 1, height - 1, 0, 0);
        this.width = width;
        this.height = height;
    }
    public Map<Vector2d, Animal> getAnimals() { return this.animals; }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && position.x >= 0 && position.x < this.width && position.y >= 0 && position.y < this.height;
    }
    @Override
    public Vector2d[] edges() {
        return new Vector2d[] {new Vector2d(0, 0), new Vector2d(width - 1, height - 1)};
    }
}
