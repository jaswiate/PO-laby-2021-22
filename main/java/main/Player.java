package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Player extends MovingEntity {
    GamePanel GP;
    KeyHandler KH;
    BufferedImage down0, down1, down2, up0, up1, up2, left0, left1, left2, right0, right1, right2;
    public Entity curHeld = null;
    public Rectangle attackRange;
    public int attackSpeed = 60;
    public int attackCnt = attackSpeed;
    public int damageDelay = 60;
    public int damageCnt = damageDelay;

    public Player(Vector2d startingPos, int HP, int AD, int speed, GamePanel GP, KeyHandler KH) {
        super(startingPos, HP, AD, speed);
        this.GP = GP;
        this.KH = KH;
        box = new Rectangle(12, 24, 40, 40);
        hitBox = new Rectangle(12, 24, 40, 40);
        attackRange = new Rectangle();
        getPlayerImage();
    }
    public void getPlayerImage() {
        try {
            down0 = ImageIO.read(getClass().getResourceAsStream("/player_down0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player_down2.png"));
            up0 = ImageIO.read(getClass().getResourceAsStream("/player_up0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player_up2.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/player_left0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player_left2.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/player_right0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player_right2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void attack() {
        if(attackCnt == attackSpeed) {
            if(curHeld instanceof Sword) {
                switch(getDirection()) {
                    case NORTH -> {
                        attackRange.x = pos.x;
                        attackRange.y = pos.y - 48;
                        attackRange.width = 64;
                        attackRange.height = 48;
                    }
                    case SOUTH -> {
                        attackRange.x = pos.x;
                        attackRange.y = pos.y + 64;
                        attackRange.width = 64;
                        attackRange.height = 48;
                    }
                    case WEST -> {
                        attackRange.x = pos.x - 48;
                        attackRange.y = pos.y;
                        attackRange.width = 48;
                        attackRange.height = 64;
                    }
                    case EAST -> {
                        attackRange.x = pos.x + 64;
                        attackRange.y = pos.y;
                        attackRange.width = 48;
                        attackRange.height = 64;
                    }
                }
                for(Monster monster : GP.currentMonsters) {
                    if(monster != null) {
                        if(GP.collisionHandler.checkCollisionAttack(attackRange, monster)) {
                            if(((Sword) curHeld).type == "iron") {
                                monster.damage(2);
                            } else {
                                monster.damage(3);
                            }
                            attackCnt = 0;
                        }
                        if (monster.getHP() <= 0) {
                            monster.setPos(new Vector2d(3 * GP.screenWidth, 3 * GP.screenHeight));
                            GP.currentMonsters.remove(monster);
                            break;
                        }
                    }
                }
            }
            else if(curHeld instanceof Wand) {
                switch(getDirection()) {
                    case NORTH -> {
                        Projectile blood = new Projectile(new Vector2d(pos.x + box.x, pos.y), 0, 2, 5, "blood", GP);
                        blood.setDirection(MapDirection.NORTH);
                        ((Wand) curHeld).projectiles.add(blood);
                        attackCnt = 0;
                    }
                    case SOUTH -> {
                        Projectile blood = new Projectile(new Vector2d(pos.x + box.x, pos.y + 64), 0, 2, 5, "blood", GP);
                        blood.setDirection(MapDirection.SOUTH);
                        ((Wand) curHeld).projectiles.add(blood);
                        attackCnt = 0;
                    }
                    case WEST -> {
                        Projectile blood = new Projectile(new Vector2d(pos.x, pos.y + box.y), 0, 2, 5, "blood", GP);
                        blood.setDirection(MapDirection.WEST);
                        ((Wand) curHeld).projectiles.add(blood);
                        attackCnt = 0;
                    }
                    case EAST -> {
                        Projectile blood = new Projectile(new Vector2d(pos.x + 64, pos.y + box.y), 0, 2, 5, "blood", GP);
                        blood.setDirection(MapDirection.EAST);
                        ((Wand) curHeld).projectiles.add(blood);
                        attackCnt = 0;
                    }
                }
            }
        }
    }
    @Override
    public void update() {
        if(attackCnt < attackSpeed) { attackCnt += 1; }
        if(damageCnt < damageDelay) { damageCnt += 1; }
        if(KH.sprintPressed) { speed = 5; }
        else { speed = 3; }
        if(KH.attackPressed) { attack(); }
        if(KH.upPressed || KH.downPressed || KH.leftPressed || KH.rightPressed) {
            if (KH.upPressed) {
                setDirection(MapDirection.NORTH);
            }
            if (KH.downPressed) {
                setDirection(MapDirection.SOUTH);
            }
            if (KH.leftPressed) {
                setDirection(MapDirection.WEST);
            }
            if (KH.rightPressed) {
                setDirection(MapDirection.EAST);
            }
            collision = false;
            hit = false;
            for(Monster monster : GP.currentMonsters) {
                if(monster != null && damageCnt == damageDelay) {
                    GP.collisionHandler.checkCollisionMonster(monster, this);
                    if(hit) {
                        damage(monster.getAD());
                        damageCnt = 0;
                    }
                }
            }
            GP.collisionHandler.checkCollisionTerrain(this);
            if(!collision) {
                switch (getDirection()) {
                    case NORTH -> pos.y -= speed;
                    case SOUTH -> pos.y += speed;
                    case WEST -> pos.x -= speed;
                    case EAST -> pos.x += speed;
                }
            }
            spriteCounter += 1;
            if(spriteCounter == 6) {
                if(spriteNum == 0) { spriteNum = 1; }
                else if(spriteNum == 1) { spriteNum = 3; }
                else if(spriteNum == 3) { spriteNum = 2; }
                else if(spriteNum == 2) { spriteNum = 0; }
                spriteCounter = 0;
            }
        }

    }
    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(getDirection()) {
            case NORTH -> {
                if(spriteNum == 0) { image = up0; }
                else if(spriteNum == 1) { image = up1; }
                else if(spriteNum == 2) { image = up2; }
                else if(spriteNum == 3) { image = up0; }
            }
            case SOUTH -> {
                if(spriteNum == 0) { image = down0; }
                else if(spriteNum == 1) { image = down1; }
                else if(spriteNum == 2) { image = down2; }
                else if(spriteNum == 3) { image = down0; }
            }
            case WEST -> {
                if(spriteNum == 0) { image = left0; }
                else if(spriteNum == 1) { image = left1; }
                else if(spriteNum == 2) { image = left2; }
                else if(spriteNum == 3) { image = left0; }
            }
            case EAST -> {
                if(spriteNum == 0) { image = right0; }
                else if(spriteNum == 1) { image = right1; }
                else if(spriteNum == 2) { image = right2; }
                else if(spriteNum == 3) { image = right0; }
            }
        }
        g2.drawImage(image, pos.x, pos.y, null);
    }
}
