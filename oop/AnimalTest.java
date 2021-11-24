package agh.ics.oop;
/*import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
public class AnimalTest {
    @Test
    public void directionTest(){
        Animal dog = new Animal();
        assertEquals(MapDirection.NORTH, dog.orient);
        for (int i = 0; i < 15; i++){
            dog.move(MoveDirection.LEFT);
        }
        assertEquals(MapDirection.EAST, dog.orient);
        dog.move(MoveDirection.RIGHT);
        dog.move(MoveDirection.RIGHT);
        dog.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, dog.orient);
    }
    @Test
    public void positionTest(){
        Animal dog = new Animal();
        assertEquals(new Vector2d(2, 2), dog.position);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.RIGHT);
        dog.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(3, 4), dog.position);
        dog.move(MoveDirection.LEFT);
        dog.move(MoveDirection.BACKWARD);
        dog.move(MoveDirection.BACKWARD);
        dog.move(MoveDirection.BACKWARD);
        dog.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(3, 0), dog.position);
    }*/
/*  @Test
    public void borderTest(){
        Animal cat = new Animal();
        for (int i = 0; i < 10; i++){
            cat.move(MoveDirection.FORWARD);
        }
        assertEquals(new Vector2d(2, 4), cat.position);
        cat.move(MoveDirection.LEFT);
        for (int i = 0; i < 10; i++){
            cat.move(MoveDirection.FORWARD);
        }
        assertEquals(new Vector2d(0, 4), cat.position);
        cat.move(MoveDirection.LEFT);
        for (int i = 0; i < 10; i++){
            cat.move(MoveDirection.FORWARD);
        }
        assertEquals(new Vector2d(0, 0), cat.position);
        cat.move(MoveDirection.LEFT);
        for (int i = 0; i < 10; i++){
            cat.move(MoveDirection.FORWARD);
        }
        assertEquals(new Vector2d(4,0), cat.position);

    }
    @Test
    public void parserTest(){
        OptionsParser parser = new OptionsParser();
        String[] args1 = {"f", "backward", "right", "r", "back", "n", "b", "left"};
        ArrayList<MoveDirection> output1 = new ArrayList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT));
        ArrayList<MoveDirection> testone = parser.parse(args1);
        assertArrayEquals(output1.toArray(), testone.toArray());

        String[] args2 = {"1", "2", "3", "b"};
        ArrayList<MoveDirection> testtwo = parser.parse(args2);
        MoveDirection[] output2 = {MoveDirection.BACKWARD};
        assertArrayEquals(output2, testtwo.toArray());

        String[] args3 = {"f", "r", "b", "l", "forward", "right", "backward", "left"};
        ArrayList<MoveDirection> testthree = parser.parse(args3);
        MoveDirection[] output3 = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT};
        assertArrayEquals(output3, testthree.toArray());
        }
    }*/
