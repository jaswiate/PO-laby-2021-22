package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile extends MovingEntity {
    GamePanel GP;
    String type;
    BufferedImage up, down, left, right;

    public Projectile(Vector2d pos, int HP, int AD, int speed, String type, GamePanel GP) {
        super(pos, HP, AD, speed);
        this.type = type;
        this.GP = GP;
        getProjectileImage(type);
    }
    public void getProjectileImage(String type) {
        try {
            switch(type) {
                case "thunder" -> {
                    up = ImageIO.read(getClass().getResourceAsStream("/thunder_up.png"));
                    down = ImageIO.read(getClass().getResourceAsStream("/thunder_down.png"));
                    left = ImageIO.read(getClass().getResourceAsStream("/thunder_left.png"));
                    right = ImageIO.read(getClass().getResourceAsStream("/thunder_right.png"));
                    box = new Rectangle(12, 12, 18, 18);
                }
                case "droplet" -> {
                    up = ImageIO.read(getClass().getResourceAsStream("/drop_up.png"));
                    down = ImageIO.read(getClass().getResourceAsStream("/drop_down.png"));
                    left = ImageIO.read(getClass().getResourceAsStream("/drop_left.png"));
                    right = ImageIO.read(getClass().getResourceAsStream("/drop_right.png"));
                    box = new Rectangle(9, 9, 27, 27);
                }
                case "boss" -> {
                    down = ImageIO.read(getClass().getResourceAsStream("/purple_j33z.png"));
                    box = new Rectangle(9, 9, 27, 27);
                }
                case "blood" -> {
                    up = ImageIO.read(getClass().getResourceAsStream("/blood_up.png"));
                    down = ImageIO.read(getClass().getResourceAsStream("/blood_down.png"));
                    left = ImageIO.read(getClass().getResourceAsStream("/blood_left.png"));
                    right = ImageIO.read(getClass().getResourceAsStream("/blood_right.png"));
                    box = new Rectangle(9, 9, 27, 27);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        switch(getDirection()) {
            case NORTH -> g2.drawImage(up, pos.x, pos.y, null);
            case SOUTH -> g2.drawImage(down, pos.x, pos.y, null);
            case WEST -> g2.drawImage(left, pos.x, pos.y, null);
            case EAST -> g2.drawImage(right, pos.x, pos.y, null);
        }
    }
    @Override
    public void update() {
        switch(getDirection()) {
            case NORTH -> pos.y -= speed;
            case SOUTH -> pos.y += speed;
            case WEST -> pos.x -= speed;
            case EAST -> pos.x += speed;
        }
    }
}
