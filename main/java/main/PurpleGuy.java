package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PurpleGuy extends Monster {
    GamePanel GP;
    BufferedImage sprite0, sprite1, sprite2;
    int attackSpeed = 180;
    int attackCnt = attackSpeed;
    List<Projectile> projectiles = new ArrayList<>();

    public PurpleGuy(Vector2d pos, int HP, int AD, int speed, MapDirection[] monsterPath, GamePanel GP) {
        super(pos, HP, AD, speed, monsterPath);
        this.GP = GP;
        hitBox = new Rectangle(25, 50, 164, 164);
        getPurpleGuyImage();
    }
    public void getPurpleGuyImage() {
        try {
            sprite0 = ImageIO.read(getClass().getResourceAsStream("/boss_0.png"));
            sprite1 = ImageIO.read(getClass().getResourceAsStream("/boss_1.png"));
            sprite2 = ImageIO.read(getClass().getResourceAsStream("/boss_2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        switch(spriteNum) {
            case 0, 4 -> g2.drawImage(sprite0, pos.x, pos.y, null);
            case 1, 3 -> g2.drawImage(sprite1, pos.x, pos.y, null);
            case 2 -> g2.drawImage(sprite2, pos.x, pos.y, null);
        }
    }
    @Override
    public void update() {
        spriteCounter += 1;
        if(spriteCounter == 7) {
            if(spriteNum == 0) { spriteNum = 1; }
            else if(spriteNum == 1) { spriteNum = 2; }
            else if(spriteNum == 2) { spriteNum = 3; }
            else if(spriteNum == 3) { spriteNum = 4; }
            else if(spriteNum == 4) { spriteNum = 0; }
            spriteCounter = 0;
        }
        if(attackCnt < attackSpeed) { attackCnt += 1; }
        if(GP.player.pos.x > pos.x + 256 && GP.player.pos.x < pos.x + 64 + 256 && GP.player.pos.y > pos.y && GP.player.pos.y < pos.y + 256 && attackCnt == attackSpeed) {
            GP.player.damage(AD);
            attackCnt = 0;
        }
        if(GP.player.pos.x < pos.x && GP.player.pos.x > pos.x - 64 && GP.player.pos.y > pos.y && GP.player.pos.y < pos.y + 256 && attackCnt == attackSpeed) {
            GP.player.damage(AD);
            attackCnt = 0;
        }
        if(GP.player.pos.x > pos.x && GP.player.pos.x < pos.x + 256 && GP.player.pos.y > pos.y + 256 && attackCnt == attackSpeed) {
            for(int i = 6; i <= 9; i++) {
                for(int j = 1; j <= 5; j += 2) {
                    Projectile p = new Projectile(new Vector2d(i * 64, j * 64), 0, 5, 8, "boss", GP);
                    p.setDirection(MapDirection.SOUTH);
                    projectiles.add(p);
                    attackCnt = 0;
                }
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
