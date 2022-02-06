package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wand extends Entity {
    GamePanel GP;
    public BufferedImage left, right;
    public List<Projectile> projectiles = new ArrayList<>();

    public Wand(Vector2d pos, GamePanel GP) {
        super(pos);
        this.GP = GP;
        box = new Rectangle(0, 0, 16, 16);
        getWandImage();
    }
    public void getWandImage() {
        try {
                left = ImageIO.read(getClass().getResourceAsStream("/wand_left.png"));
                right = ImageIO.read(getClass().getResourceAsStream("/wand_right.png"));
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
            for(Projectile p : projectiles) {
                boolean f = false;
                if(p != null) {
                    p.update();
                    for(Monster m : GP.currentMonsters) {
                        GP.collisionHandler.checkCollisionEntity(p, m);
                        GP.collisionHandler.checkCollisionTerrain(p);
                        if(m.hit) {
                            f = true;
                            m.damage(2);
                            if(m.getHP() <= 0) {
                                GP.currentMonsters.remove(m);
                                break;
                            }
                        }
                        else if(p.collision) {
                            f = true;
                        }
                    }
                    if(f) {
                        projectiles.remove(p);
                        break;
                    }
                }
            }
        }
    }
}
