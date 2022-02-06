package main;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pikachu extends Monster {
    GamePanel GP;
    BufferedImage pikachuLeft, pikachuRight;
    int attackSpeed = 75;
    int attackCnt = attackSpeed;
    List<Projectile> projectiles = new ArrayList<>();

    public Pikachu(Vector2d pos, int HP, int AD, int speed, MapDirection[] monsterPath, GamePanel GP) {
        super(pos, HP, AD, speed, monsterPath);
        this.GP = GP;
        box = new Rectangle(11, 23, 40, 40);
        hitBox = new Rectangle(11, 23, 40, 40);
        getPikachuImage();
    }
    public void getPikachuImage() {
        try {
            pikachuLeft = ImageIO.read(getClass().getResourceAsStream("/pikachu_left.png"));
            pikachuRight = ImageIO.read(getClass().getResourceAsStream("/pikachu_right.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        switch(getDirection()) {
            case WEST, SOUTH -> g2.drawImage(pikachuLeft, pos.x, pos.y, null);
            case EAST, NORTH -> g2.drawImage(pikachuRight, pos.x, pos.y, null);
        }
    }
    @Override
    public void update() {
        if(attackCnt < attackSpeed) { attackCnt += 1; }
        if(GP.player.pos.x >= pos.x && GP.player.pos.x <= pos.x + box.width && getDirection() == MapDirection.NORTH) {
            if(attackCnt == attackSpeed) {
                Projectile p = new Projectile(new Vector2d(pos.x + box.x, pos.y), 0, 2, 6, "thunder", GP);
                p.setDirection(MapDirection.NORTH);
                projectiles.add(p);
                attackCnt = 0;
            }
        }
        else if(GP.player.pos.x >= pos.x && GP.player.pos.x <= pos.x + box.width && getDirection() == MapDirection.SOUTH) {
            if(attackCnt == attackSpeed) {
                Projectile p = new Projectile(new Vector2d(pos.x + box.x, pos.y + 63), 0, 2, 6, "thunder", GP);
                p.setDirection(MapDirection.SOUTH);
                projectiles.add(p);
                attackCnt = 0;
            }
        }
        else if(GP.player.pos.y >= pos.y && GP.player.pos.y <= pos.y + box.height && getDirection() == MapDirection.WEST) {
            if(attackCnt == attackSpeed) {
                Projectile p = new Projectile(new Vector2d(pos.x, pos.y + box.y), 0, 2, 6, "thunder", GP);
                p.setDirection(MapDirection.WEST);
                projectiles.add(p);
                attackCnt = 0;
            }
        }
        else if(GP.player.pos.y >= pos.y && GP.player.pos.y <= pos.y + box.height && getDirection() == MapDirection.EAST) {
            if(attackCnt == attackSpeed) {
                Projectile p = new Projectile(new Vector2d(pos.x + 63, pos.y + box.y), 0, 2, 6, "thunder", GP);
                p.setDirection(MapDirection.EAST);
                projectiles.add(p);
                attackCnt = 0;
            }
        }
        for(Projectile projectile : projectiles) {
            GP.player.hit = false;
            if(projectile != null) {
                projectile.update();
                GP.collisionHandler.checkCollisionTerrain(projectile);
                GP.collisionHandler.checkCollisionEntity(projectile, GP.player);
                if(GP.player.hit) {
                    GP.player.damage(getAD());
                    System.out.println(GP.player.getHP());
                    GP.player.damageCnt = 0;
                    projectiles.remove(projectile);
                    break;
                }
                else if(projectile.collision) {
                    projectiles.remove(projectile);
                    break;
                }
            }
        }
    }
}
