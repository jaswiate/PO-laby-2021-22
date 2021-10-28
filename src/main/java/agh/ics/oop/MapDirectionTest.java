package agh.ics.oop;
import org.junit.Test;

public class MapDirectionTest {
    @Test
    public void checkRun() {
        if(MapDirection.NORTH.next() == MapDirection.EAST)
            System.out.println("True");


    }
}
