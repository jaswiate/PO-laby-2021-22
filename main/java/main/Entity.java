package main;

import java.awt.*;

public class Entity {
    protected Vector2d pos;
    protected Rectangle box;
    public boolean used = false;
    public boolean collision = false;
    public boolean held = false;

    public Entity(Vector2d pos) {
        this.pos = pos;
    }
    public Vector2d getPos() {
        return pos;
    }
    public void setPos(Vector2d pos) {
        this.pos = pos;
    }
    public Rectangle getBox() {
        return this.box;
    }
    public void update() {}
    public void draw(Graphics2D g2) {}
}
