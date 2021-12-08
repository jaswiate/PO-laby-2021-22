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
        Animal[] animals = map.getAnimals().values().toArray(new Animal[0]);
        int len = animals.length;
        int i = 0;
        for(MoveDirection direction : this.directions) {
            System.out.println(animals[i%len].getPosition());
            System.out.println(direction);
            animals[i % len].move(direction);
            System.out.println(map);
            i += 1;
        }
    }
}
