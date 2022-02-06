package main;

import java.awt.*;

public class MovingEntity extends Entity {
    protected MapDirection direction;
    protected int HP;
    protected int maxHP;
    protected int AD;
    protected Rectangle hitBox;
    protected int speed;
    public boolean hit = false;
    public int spriteNum = 0;
    public int spriteCounter = 0;

    public MovingEntity(Vector2d pos, int HP, int AD, int speed) {
        super(pos);
        this.direction = MapDirection.SOUTH;
        this.HP = HP;
        this.AD = AD;
        this.maxHP = HP;
        this.speed = speed;
    }
    public void damage(int dmg) {
        this.HP -= dmg;
    }
    public MapDirection getDirection() {
        return direction;
    }
    public void setDirection(MapDirection d) {
        this.direction = d;
    }
    public int getHP() {
        return this.HP;
    }
    public int getMaxHP() {
        return this.maxHP;
    }
    public int getAD() {
        return this.AD;
    }
    public Rectangle getHitBox() {
        return this.hitBox;
    }
    public int getSpeed() {
        return this.speed;
    }
    public void heal(int h) {
        this.HP += h;
        if(this.HP > getMaxHP()) { this.HP = getMaxHP(); }
    }
}
