package main;

public class Monster extends MovingEntity{
    protected int pathIdx = 0;
    protected int walkCounter = 0;
    protected int pathDirection = 1;
    public MapDirection[] monsterPath;
    public MapDirection[] monsterPathRev;

    public Monster(Vector2d pos, int HP, int AD, int speed, MapDirection[] monsterPath) {
        super(pos, HP, AD, speed);
        this.monsterPath = monsterPath;
        collision = true;
        if(monsterPath != null) {
            monsterPathRev = new MapDirection[monsterPath.length];
            for (int i = 0; i < monsterPath.length; i++) {
                monsterPathRev[i] = monsterPath[i].opposite();
            }
        }
    }
}
