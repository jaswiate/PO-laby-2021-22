package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Walker extends Monster {
    GamePanel GP;
    BufferedImage monsterImage0, monsterImage1;
    MapDirection[] monsterPathCur = monsterPath;

    public Walker(Vector2d pos, int HP, int AD, int speed, MapDirection[] monsterPath, GamePanel GP) {
        super(pos, HP, AD, speed, monsterPath);
        this.GP = GP;
        hitBox = new Rectangle(12, 24, 40, 40);
        getWalkerImage();
    }

    public void getWalkerImage() {
        try {
            monsterImage0 = ImageIO.read(getClass().getResourceAsStream("/walker_0.png"));
            monsterImage1 = ImageIO.read(getClass().getResourceAsStream("/walker_1.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        switch(spriteNum) {
            case 0 -> g2.drawImage(monsterImage0, pos.x, pos.y, GP.tileSize, GP.tileSize, null);
            case 1 -> g2.drawImage(monsterImage1, pos.x, pos.y, GP.tileSize, GP.tileSize, null);
        }
    }
    @Override
    public void update() {
        switch(monsterPathCur[pathIdx]) {
            case NORTH -> pos.y -= speed;
            case SOUTH -> pos.y += speed;
            case WEST -> pos.x -= speed;
            case EAST -> pos.x += speed;
        }
        walkCounter += speed;
        if(walkCounter == GP.tileSize) {
            pathIdx += pathDirection;
            walkCounter = 0;
        }
        if(pathIdx == monsterPathCur.length || pathIdx == -1) {
            pathDirection *= -1;
            pathIdx += pathDirection;
            if(monsterPathCur == monsterPath) {
                monsterPathCur = monsterPathRev;
            }
            else {
                monsterPathCur = monsterPath;
            }
        }
        spriteCounter += 1;
        if(spriteCounter == 20) {
            if(spriteNum == 0) { spriteNum = 1; }
            else if(spriteNum == 1) { spriteNum = 0; }
            spriteCounter = 0;
        }
    }
}
