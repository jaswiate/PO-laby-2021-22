package agh.ics.oop;

public class Animal{
    private MapDirection orient;
    private Vector2d position;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.orient = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }

    public MapDirection getOrient() {
        return this.orient;
    }
    public Vector2d getPosition() {
        return this.position;
    }
    public String toString() {
        return this.orient.toString();
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case RIGHT -> this.orient = this.orient.next();
            case LEFT -> this.orient = this.orient.previous();
            case FORWARD -> {
                Vector2d posAdd = this.position.add(this.orient.toUnitVector());
                if(this.map.canMoveTo(posAdd))
                    this.position = posAdd;
            }
            case BACKWARD -> {
                Vector2d posSubtract = this.position.subtract(this.orient.toUnitVector());
                if(this.map.canMoveTo(posSubtract))
                    this.position = posSubtract;
            }
        }
    }
}
