package agh.ics.oop;

//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class MapTest {
//
//    @Test
//    public void isOccupiedTest() {
//        RectangularMap map = new RectangularMap(4, 4);
//        map.place(new Animal(map, new Vector2d(2,2)));
//
//        assertTrue(map.isOccupied(new Vector2d(2,2)));
//        assertFalse(map.isOccupied(new Vector2d(2,3)));
//    }
//
//    @Test
//    public void canMoveToTest() {
//        RectangularMap map = new RectangularMap(4, 4);
//        map.place(new Animal(map, new Vector2d(2,2)));
//
//        assertFalse(map.canMoveTo(new Vector2d(2,2)));
//        assertFalse(map.canMoveTo(new Vector2d(4,0)));
//        assertTrue(map.canMoveTo(new Vector2d(1,2)));
//    }
//
//    @Test
//    public void placeTest() {
//        RectangularMap map = new RectangularMap(4, 4);
//        List<Animal> animals = map.getAnimals();
//        assertEquals(animals.size(), 0);
//        map.place(new Animal(map, new Vector2d(2,2)));
//        assertTrue(animals.get(0).isAt(new Vector2d(2,2)));
//        map.place(new Animal(map, new Vector2d(2,2)));
//        assertEquals(animals.size(), 1);
//        map.place(new Animal(map, new Vector2d(4,2)));
//        assertEquals(animals.size(), 1);
//    }
//}