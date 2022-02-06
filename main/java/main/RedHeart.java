package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RedHeart extends Entity {
    GamePanel GP;
    BufferedImage fullHeart, halfHeart;
    int type;

    public RedHeart(Vector2d pos, int type, GamePanel GP) {
        super(pos);
        this.GP = GP;
        this.type = type;
        box = new Rectangle(9, 18, 45, 45);
        getRedHeartImage();
    }
    public void getRedHeartImage() {
        try {
            fullHeart = ImageIO.read(getClass().getResourceAsStream("/full_heart.png"));
            halfHeart = ImageIO.read(getClass().getResourceAsStream("/half_heart.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        if(type == 0) { g2.drawImage(fullHeart, pos.x, pos.y, null); }
        else if(type == 1) { g2.drawImage(halfHeart, pos.x, pos.y, null); }
    }
    @Override
    public void update() {
        GP.collisionHandler.checkCollisionEntity(this, GP.player);
        if(collision && GP.player.getHP() < GP.player.getMaxHP()) {
            if(type == 0) { GP.player.heal(2); }
            else if(type == 1) { GP.player.heal(1); }
            used = true;
        }
    }
}
