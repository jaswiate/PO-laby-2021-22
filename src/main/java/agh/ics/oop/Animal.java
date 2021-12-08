package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class Animal implements IPositionChangeObserver{
    private MapDirection orient;
    private Vector2d position;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.orient = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        this.addObserver((IPositionChangeObserver) map);
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
        Vector2d previous = this.position;
        Vector2d next = this.position;
        switch(direction) {
            case RIGHT -> this.orient = this.orient.next();
            case LEFT -> this.orient = this.orient.previous();
            case FORWARD -> {
                Vector2d posAdd = this.position.add(this.orient.toUnitVector());
                if(this.map.canMoveTo(posAdd)) {
                    this.position = posAdd;
                    next = posAdd;
                }
            }
            case BACKWARD -> {
                Vector2d posSubtract = this.position.subtract(this.orient.toUnitVector());
                if(this.map.canMoveTo(posSubtract)) {
                    this.position = posSubtract;
                    next = posSubtract;
                }
            }
        }
        positionChanged(previous, next);
    }
    void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observer : this.observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
