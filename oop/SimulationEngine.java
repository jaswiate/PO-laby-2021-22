package agh.ics.oop;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] directions;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        placeAnimals(positions);
    }
    public void placeAnimals(Vector2d[] positions) {
        for(Vector2d pos : positions) {
            map.place(new Animal(this.map, pos));
        }
    }
    @Override
    public void run() {
        RectangularMap map = (RectangularMap) this.map;
        List<Animal> animals = map.getAnimals();
        int len = animals.size();
        int i = 0;

        System.out.println(map);
        for(MoveDirection direction : this.directions) {
            animals.get(i % len).move(direction);
            System.out.println(map);
            i++;
        }
    }
}
