package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] directions) {
        int length = directions.length;
        MoveDirection[] result = new MoveDirection[length];
        int i = 0;
        for(String dir : directions) {
            switch(dir) {
                case "f", "forward" -> result[i] = MoveDirection.FORWARD;
                case "b", "backward" -> result[i] = MoveDirection.BACKWARD;
                case "r", "right" -> result[i] = MoveDirection.RIGHT;
                case "l", "left"-> result[i] = MoveDirection.LEFT;
            }
            i += 1;
        }
        return result;
    }
}
