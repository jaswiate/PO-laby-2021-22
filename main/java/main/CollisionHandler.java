package main;

import java.awt.*;

public class CollisionHandler {
    GamePanel GP;

    public CollisionHandler(GamePanel GP) {
        this.GP = GP;

    }
    public void checkCollisionMonster(Monster w, Player p) {
        Vector2d BL = p.pos;
        Vector2d UP = p.pos.add(new Vector2d(p.hitBox.width, p.hitBox.height));

        Vector2d p1 = w.pos;
        Vector2d p2 = w.pos.add(new Vector2d(w.hitBox.width, 0));
        Vector2d p3 = w.pos.add(new Vector2d(0, w.hitBox.height));
        Vector2d p4 = w.pos.add(new Vector2d(w.hitBox.width, w.hitBox.height));
        if(p1.isInSquare(BL, UP) || p2.isInSquare(BL, UP) || p3.isInSquare(BL, UP) || p4.isInSquare(BL, UP)) {
            p.hit = true;
        }
    }
    public boolean checkCollisionAttack(Rectangle r, MovingEntity w) {
        Vector2d BL = new Vector2d(r.x, r.y);
        Vector2d UP = BL.add(new Vector2d(r.width, r.height));

        Vector2d p1 = w.pos;
        Vector2d p2 = w.pos.add(new Vector2d(w.hitBox.width, 0));
        Vector2d p3 = w.pos.add(new Vector2d(0, w.hitBox.height));
        Vector2d p4 = w.pos.add(new Vector2d(w.hitBox.width, w.hitBox.height));
        return p1.isInSquare(BL, UP) || p2.isInSquare(BL, UP) || p3.isInSquare(BL, UP) || p4.isInSquare(BL, UP);
    }

    public boolean checkRangeAttack(Vector2d BL, Vector2d UP, MovingEntity w) {
        Vector2d p1 = w.pos;
        Vector2d p2 = w.pos.add(new Vector2d(w.hitBox.width, 0));
        Vector2d p3 = w.pos.add(new Vector2d(0, w.hitBox.height));
        Vector2d p4 = w.pos.add(new Vector2d(w.hitBox.width, w.hitBox.height));
        return p1.isInSquare(BL, UP) || p2.isInSquare(BL, UP) || p3.isInSquare(BL, UP) || p4.isInSquare(BL, UP);
    }

    public void checkCollisionEntity(Entity e, MovingEntity p) {
        Vector2d BL = p.pos;
        Vector2d UP = p.pos.add(new Vector2d(p.hitBox.width, p.hitBox.height));

        Vector2d p1 = e.pos;
        Vector2d p2 = e.pos.add(new Vector2d(e.box.width, 0));
        Vector2d p3 = e.pos.add(new Vector2d(0, e.box.height));
        Vector2d p4 = e.pos.add(new Vector2d(e.box.width, e.box.height));
        if(p1.isInSquare(BL, UP) || p2.isInSquare(BL, UP) || p3.isInSquare(BL, UP) || p4.isInSquare(BL, UP)) {
            if(e instanceof Projectile) {
                e.collision = true;
                p.hit = true;
            }
            else if(e instanceof RedHeart || e instanceof Chest) {
                e.collision = true;
            }
            else if(p instanceof Player){
                e.held = true;
                ((Player) p).curHeld = e;
                GP.inventory.add(e);
            }
            else if(p instanceof Monster) {
                p.hit = true;
            }
        }
    }

    public void checkCollisionTerrain(MovingEntity p) {
        int leftX = p.pos.x + p.box.x;
        int rightX = p.pos.x + p.box.x + p.box.width;
        int topY = p.pos.y + p.box.y;
        int bottomY = p.pos.y + p.box.y + p.box.height;

        int leftCol = leftX / GP.tileSize;
        int rightCol = rightX / GP.tileSize;
        int topRow = topY / GP.tileSize;
        int bottomRow = bottomY / GP.tileSize;

        int tile1, tile2;

        switch(p.getDirection()) {
            case NORTH -> {
                topRow = (topY - p.speed) / GP.tileSize;
                if(topRow < 12 && topRow >= 0 && bottomRow < 12 && bottomRow >= 0 && leftCol < 16 && leftCol >= 0 && rightCol < 16 && rightCol >= 0) {
                    tile1 = GP.currentLevel.layout[topRow][leftCol];
                    tile2 = GP.currentLevel.layout[topRow][rightCol];
                    if(p instanceof Player)
                        if(GP.currentLevel.tileList[tile1].collision0 || GP.currentLevel.tileList[tile2].collision0) {
                            p.collision = true;
                        }
                    if(p instanceof Projectile)
                        if(GP.currentLevel.tileList[tile1].collision1 || GP.currentLevel.tileList[tile2].collision1 || p.pos.x > GP.screenWidth || p.pos.y > GP.screenHeight || p.pos.x < 0 || p.pos.y < 0) {
                            p.collision = true;
                        }
                }
            }
            case SOUTH -> {
                bottomRow = (bottomY + p.speed) / GP.tileSize;
                if(topRow < 12 && topRow >= 0 && bottomRow < 12 && bottomRow >= 0 && leftCol < 16 && leftCol >= 0 && rightCol < 16 && rightCol >= 0) {
                    tile1 = GP.currentLevel.layout[bottomRow][leftCol];
                    tile2 = GP.currentLevel.layout[bottomRow][rightCol];
                    if(p instanceof Player)
                        if(GP.currentLevel.tileList[tile1].collision0 || GP.currentLevel.tileList[tile2].collision0) {
                            p.collision = true;
                        }
                    if(p instanceof Projectile)
                        if(GP.currentLevel.tileList[tile1].collision1 || GP.currentLevel.tileList[tile2].collision1 || p.pos.x > GP.screenWidth || p.pos.y > GP.screenHeight || p.pos.x < 0 || p.pos.y < 0) {
                            p.collision = true;
                        }
                }
            }
            case WEST -> {
                leftCol = (leftX - p.speed) / GP.tileSize;
                if(leftCol < 16 && leftCol >= 0 && rightCol < 16 && rightCol >= 0 && topRow < 12 && topRow >= 0 && bottomRow < 12 && bottomRow >= 0) {
                    tile1 = GP.currentLevel.layout[topRow][leftCol];
                    tile2 = GP.currentLevel.layout[bottomRow][leftCol];
                    if(p instanceof Player)
                        if(GP.currentLevel.tileList[tile1].collision0 || GP.currentLevel.tileList[tile2].collision0) {
                            p.collision = true;
                        }
                    if(p instanceof Projectile)
                        if(GP.currentLevel.tileList[tile1].collision1 || GP.currentLevel.tileList[tile2].collision1 || p.pos.x > GP.screenWidth || p.pos.y > GP.screenHeight || p.pos.x < 0 || p.pos.y < 0) {
                            p.collision = true;
                        }
                }
            }
            case EAST -> {
                rightCol = (rightX + p.speed) / GP.tileSize;
                if(leftCol < 16 && leftCol >= 0 && rightCol < 16 && rightCol >= 0 && topRow < 12 && topRow >= 0 && bottomRow < 12 && bottomRow >= 0) {
                    tile1 = GP.currentLevel.layout[topRow][rightCol];
                    tile2 = GP.currentLevel.layout[bottomRow][rightCol];
                    if(p instanceof Player)
                        if(GP.currentLevel.tileList[tile1].collision0 || GP.currentLevel.tileList[tile2].collision0) {
                            p.collision = true;
                        }
                    if(p instanceof Projectile)
                        if(GP.currentLevel.tileList[tile1].collision1 || GP.currentLevel.tileList[tile2].collision1 || p.pos.x > GP.screenWidth || p.pos.y > GP.screenHeight || p.pos.x < 0 || p.pos.y < 0) {
                            p.collision = true;
                        }
                }
            }
        }
    }
}
