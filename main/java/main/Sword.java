package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sword extends Entity {
    GamePanel GP;
    String type;
    public BufferedImage left, right;

    public Sword(Vector2d pos, String type, GamePanel GP) {
        super(pos);
        this.GP = GP;
        this.type = type;
        box = new Rectangle(0, 0, 16, 16);
        getSwordImage();
    }
    public void getSwordImage() {
        try {
            switch(type) {
                case "iron" -> {
                    left = ImageIO.read(getClass().getResourceAsStream("/iron_sword_left.png"));
                    right = ImageIO.read(getClass().getResourceAsStream("/iron_sword_right.png"));
                }
                case "diamond" -> {
                    left = ImageIO.read(getClass().getResourceAsStream("/diamond_sword_left.png"));
                    right = ImageIO.read(getClass().getResourceAsStream("/diamond_sword_right.png"));
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        if(held) {
            switch(GP.player.getDirection()) {
                case NORTH, EAST -> g2.drawImage(right, pos.x, pos.y, null);
                case SOUTH, WEST -> g2.drawImage(left, pos.x, pos.y, null);
            }
        }
        else {
            g2.drawImage(right, pos.x, pos.y, null);
        }
    }
    @Override
    public void update() {
        if(!held) { GP.collisionHandler.checkCollisionEntity(this, GP.player); }
        if(held) {
            switch(GP.player.getDirection()) {
                case NORTH -> {
                    pos.x = GP.player.pos.x + 32;
                    pos.y = GP.player.pos.y + 12;
                }
                case SOUTH -> {
                    pos.x = GP.player.pos.x - 26;
                    pos.y = GP.player.pos.y + 12;
                }
                case WEST -> {
                    pos.x = GP.player.pos.x - 15;
                    pos.y = GP.player.pos.y + 12;
                }
                case EAST -> {
                    pos.x = GP.player.pos.x + 28;
                    pos.y = GP.player.pos.y + 12;
                }
            }
        }
    }
}
