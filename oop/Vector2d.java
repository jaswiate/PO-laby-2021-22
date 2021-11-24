package agh.ics.oop;

public class Vector2d {
    public int x;
    public int y;
    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }
    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.x >= other.y;
    }
    Vector2d upperRight(Vector2d other) {
        int upX;
        int upY;
        upX = Math.max(this.x, other.y);
        upY = Math.max(this.x, other.y);
        return new Vector2d(upX, upY);
    }
    Vector2d lowerLeft(Vector2d other) {
        int lowX;
        int lowY;
        lowX = Math.min(this.x, other.y);
        lowY = Math.min(this.x, other.y);
        return new Vector2d(lowX, lowY);
    }
    Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }
    Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return this.x == that.x && this.y == that.y;
    }
    Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }
}
