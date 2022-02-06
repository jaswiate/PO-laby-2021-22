package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lapras extends Monster {
    GamePanel GP;
    BufferedImage lapras0, lapras1, lapras_hidden;
    MapDirection[] monsterPathCur = monsterPath;
    int attackSpeed = 150;
    int attackCnt = attackSpeed;
    List<Projectile> projectiles = new ArrayList<>();
    boolean hidden = true;

    public Lapras(Vector2d pos, int HP, int AD, int speed, MapDirection[] monsterPath, GamePanel GP) {
        super(pos, HP, AD, speed, monsterPath);
        this.GP = GP;
        hitBox = new Rectangle(12, 24, 40, 40);
        getLaprasImage();
    }
    public void getLaprasImage() {
        try {
            lapras0 = ImageIO.read(getClass().getResourceAsStream("/lapras_0.png"));
            lapras1 = ImageIO.read(getClass().getResourceAsStream("/lapras_1.png"));
            lapras_hidden = ImageIO.read(getClass().getResourceAsStream("/lapras_hidden.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        if(!hidden) {
            switch(getDirection()) {
                case WEST, SOUTH -> g2.drawImage(lapras0, pos.x, pos.y, null);
                case EAST, NORTH -> g2.drawImage(lapras1, pos.x, pos.y, null);
            }
        }
        else { g2.drawImage(lapras_hidden, pos.x, pos.y, null); }
    }
    @Override
    public void update() {
        if(attackCnt < attackSpeed) {
            attackCnt += 1;
            if(attackCnt == 60) {
                hidden = true;
            }
        }
        if(GP.collisionHandler.checkRangeAttack(new Vector2d(pos.x, 0), new Vector2d(pos.x + 64, pos.y + 32), GP.player) && attackCnt == attackSpeed) {
            setDirection(MapDirection.NORTH);
            Projectile p = new Projectile(new Vector2d(pos.x + hitBox.x, pos.y), 0, 2, 7, "droplet", GP);
            p.setDirection(MapDirection.NORTH);
            projectiles.add(p);
            attackCnt = 0;
            hidden = false;
        }
        else if(GP.collisionHandler.checkRangeAttack(new Vector2d(pos.x, pos.y + 32), new Vector2d(pos.x + 64, GP.screenHeight), GP.player) && attackCnt == attackSpeed) {
            setDirection(MapDirection.SOUTH);
            Projectile p = new Projectile(new Vector2d(pos.x + hitBox.x, pos.y + 64), 0, 2, 7, "droplet", GP);
            p.setDirection(MapDirection.SOUTH);
            projectiles.add(p);
            attackCnt = 0;
            hidden = false;
        }
        else if(GP.collisionHandler.checkRangeAttack(new Vector2d(0, pos.y), new Vector2d(pos.x + 32, pos.y + 64), GP.player) && attackCnt == attackSpeed) {
            setDirection(MapDirection.WEST);
            Projectile p = new Projectile(new Vector2d(pos.x, pos.y + hitBox.y), 0, 2, 7, "droplet", GP);
            p.setDirection(MapDirection.WEST);
            projectiles.add(p);
            attackCnt = 0;
            hidden = false;
        }
        else if(GP.collisionHandler.checkRangeAttack(new Vector2d(pos.x + 32, pos.y), new Vector2d(GP.screenWidth, pos.y + 64), GP.player) && attackCnt == attackSpeed) {
            setDirection(MapDirection.EAST);
            Projectile p = new Projectile(new Vector2d(pos.x + 64, pos.y + hitBox.y), 0, 2, 7, "droplet", GP);
            p.setDirection(MapDirection.EAST);
            projectiles.add(p);
            attackCnt = 0;
            hidden = false;
        }
        for(Projectile projectile : projectiles) {
            GP.player.hit = false;
            if(projectile != null) {
                projectile.update();
                GP.collisionHandler.checkCollisionTerrain(projectile);
                GP.collisionHandler.checkCollisionEntity(projectile, GP.player);
                if(GP.player.hit) {
                    GP.player.damage(getAD());
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
        if(hidden) {
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
                } else {
                    monsterPathCur = monsterPath;
                }
            }
        }
    }
}
