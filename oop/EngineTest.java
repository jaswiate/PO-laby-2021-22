package agh.ics.oop;

//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class EngineTest {
//
//    @Test
//    public void runTest1() {
//        MoveDirection[] directions = new OptionsParser().parse("f b r l f f r r f f f f f f f f".split(" "));
//        IWorldMap map = new RectangularMap(10, 5);
//        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//        IEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();
//
//        List<Animal> animals = ((RectangularMap) map).getAnimals();
//        assertEquals(animals.get(0).getPosition(), new Vector2d(2, 0));
//        assertEquals(animals.get(0).getOrient(), MapDirection.SOUTH);
//        assertEquals(animals.get(1).getPosition(), new Vector2d(3, 4));
//        assertEquals(animals.get(1).getOrient(), MapDirection.NORTH);
//    }
//
//    @Test
//    public void runTest2() {
//        MoveDirection[] directions = new OptionsParser().parse("r l f f f f f f f f f f f f f f f".split(" "));
//        IWorldMap map = new RectangularMap(10, 5);
//        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(9,0) };
//        IEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();
//
//        List<Animal> animals = ((RectangularMap) map).getAnimals();
//        assertEquals(animals.get(0).getPosition(), new Vector2d(4, 0));
//        assertEquals(animals.get(0).getOrient(), MapDirection.EAST);
//        assertEquals(animals.get(1).getPosition(), new Vector2d(5, 0));
//        assertEquals(animals.get(1).getOrient(), MapDirection.WEST);
//    }
//}