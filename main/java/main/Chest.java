package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Chest extends Entity {
    GamePanel GP;
    Entity contains;
    BufferedImage image;
    boolean open = false;

    public Chest(Vector2d pos, Entity contains, GamePanel GP) {
        super(pos);
        this.GP = GP;
        this.contains = contains;
        box = new Rectangle(45, 45);
        getChestImage();
    }
    public void getChestImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/chest.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        if(!open)
            g2.drawImage(image, pos.x, pos.y, null);
    }
    @Override
    public void update() {
        collision = false;
        GP.collisionHandler.checkCollisionEntity(this, GP.player);
        if(collision) {
            open = true;
            used = true;
        }
    }
}
