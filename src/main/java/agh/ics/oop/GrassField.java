package agh.ics.oop;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {
    private final int howMuchGrass;
    private final int maxRange;
    protected final Map<Vector2d, Grass> grassPoints = new LinkedHashMap<>();

    public GrassField(int howMuchGrass) {
        super(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1);
        this.howMuchGrass = howMuchGrass;
        this.maxRange = (int)Math.sqrt(this.howMuchGrass * 10);
        giveMeGrass();
    }
    private void giveMeGrass() {
        while(this.grassPoints.size() < this.howMuchGrass) {
            int curX = (int)(Math.random() * maxRange);
            int curY = (int)(Math.random() * maxRange);
            Vector2d curPos = new Vector2d(curX, curY);
            if(this.grassPoints.get(curPos) == null)
                this.grassPoints.put(curPos, new Grass(curPos));
        }
    }
    public Vector2d edgeUpRight() {
        Vector2d getUp = botL;
        for(var a : this.animals.entrySet()) {
            getUp = getUp.upperRight(a.getKey());
        }
        for(var g : this.grassPoints.entrySet()) {
            getUp = getUp.upperRight(g.getKey());
        }
        return getUp;
    }
    public Vector2d edgeBottomLeft() {
        Vector2d getDown = topR;
        for(var a : this.animals.entrySet()) {
            getDown = getDown.lowerLeft(a.getKey());
        }
        for(var g : this.grassPoints.entrySet()) {
            getDown = getDown.lowerLeft(g.getKey());
        }
        return getDown;
    }
    @Override
    public Vector2d[] edges() {
        return new Vector2d[]{edgeBottomLeft(), edgeUpRight()};
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        if(super.isOccupied(position))
            return true;
        return this.grassPoints.get(position) != null;
    }
    @Override
    public Object objectAt(Vector2d position) {
        if(super.objectAt(position) != null)
            return super.objectAt(position);
        return this.grassPoints.get(position);
    }
}
