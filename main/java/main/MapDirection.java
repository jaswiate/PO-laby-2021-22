package main;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;
    MapDirection opposite() {
        return switch(this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            case EAST -> WEST;
        };
    }
}
