package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Level {
    GamePanel GP;
    int[][] layout;
    Tile grassTile = new Tile();
    Tile pathTile = new Tile();
    Tile tree0Tile = new Tile();
    Tile tree1Tile = new Tile();
    Tile waterTile = new Tile();
    Tile stoneTile = new Tile();
    Tile bossPathTile0 = new Tile();
    Tile bossPathTile2 = new Tile();
    Tile[] tileList = new Tile[8];

    public Level(GamePanel GP, String levelNum) {
        this.layout = new LevelLayout(levelNum).level;
        this.GP = GP;
        getTileImage();

    }
    public void getTileImage() {
        try {
            grassTile.image = ImageIO.read(getClass().getResourceAsStream("/grass.png"));
            pathTile.image = ImageIO.read(getClass().getResourceAsStream("/path.png"));
            tree0Tile.image = ImageIO.read(getClass().getResourceAsStream("/tree_0.png"));
            tree0Tile.collision0 = true;
            tree0Tile.collision1 = true;
            tree1Tile.image = ImageIO.read(getClass().getResourceAsStream("/tree_1.png"));
            tree1Tile.collision0 = true;
            tree1Tile.collision1 = true;
            waterTile.image = ImageIO.read(getClass().getResourceAsStream("/water.png"));
            waterTile.collision0 = true;
            waterTile.collision1 = false;
            stoneTile.image = ImageIO.read(getClass().getResourceAsStream("/stone.png"));
            stoneTile.collision0 = true;
            stoneTile.collision1 = true;
            bossPathTile0.image = ImageIO.read(getClass().getResourceAsStream("/boss_path_0.png"));
            bossPathTile2.image = ImageIO.read(getClass().getResourceAsStream("/boss_path_2.png"));

            tileList[0] = grassTile;
            tileList[1] = pathTile;
            tileList[2] = tree0Tile;
            tileList[3] = tree1Tile;
            tileList[4] = waterTile;
            tileList[5] = stoneTile;
            tileList[6] = bossPathTile0;
            tileList[7] = bossPathTile2;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 16; j++) {
                if(layout[i][j] == 0) { g2.drawImage(grassTile.image, j * GP.tileSize, i * GP.tileSize, null); }
                else if(layout[i][j] == 1) { g2.drawImage(pathTile.image, j * GP.tileSize, i * GP.tileSize, null); }
                else if(layout[i][j] == 2) { g2.drawImage(tree0Tile.image, j * GP.tileSize, i * GP.tileSize, null); }
                else if(layout[i][j] == 3) { g2.drawImage(tree1Tile.image, j * GP.tileSize, i * GP.tileSize, null); }
                else if(layout[i][j] == 4) { g2.drawImage(waterTile.image, j * GP.tileSize, i * GP.tileSize, null); }
                else if(layout[i][j] == 5) { g2.drawImage(stoneTile.image, j * GP.tileSize, i * GP.tileSize, null); }
                else if(layout[i][j] == 6) { g2.drawImage(bossPathTile0.image, j * GP.tileSize, i * GP.tileSize, null); }
                else if(layout[i][j] == 7) { g2.drawImage(bossPathTile2.image, j * GP.tileSize, i * GP.tileSize, null); }
            }
        }
    }
    public void update() {
//        LEVEL 20 <----> LEVEL 10
        if(GP.currentLevel == GP.level20 && GP.player.pos.y <= -32) {
            GP.currentLevel = GP.level10;
            GP.player.pos.y = GP.screenHeight - 32;
            GP.currentMonsters = GP.monsters10;
            GP.currentCollectibles = GP.collectibles10;
        }
        else if(GP.currentLevel == GP.level10 && GP.player.pos.y >= GP.screenHeight) {
            GP.currentLevel = GP.level20;
            GP.player.pos.y = -32;
            GP.currentMonsters = GP.monsters20;
            GP.currentCollectibles = GP.collectibles20;
        }
//        LEVEL 10 <----> LEVEL 00
        else if(GP.currentLevel == GP.level10 && GP.player.pos.y <= -32) {
            GP.currentLevel = GP.level00;
            GP.player.pos.y = GP.screenHeight - 32;
            GP.currentMonsters = GP.monsters00;
            GP.currentCollectibles = GP.collectibles00;
        }
        else if(GP.currentLevel == GP.level00 && GP.player.pos.y >= GP.screenHeight) {
            GP.currentLevel = GP.level10;
            GP.player.pos.y = -32;
            GP.currentMonsters = GP.monsters10;
            GP.currentCollectibles = GP.collectibles10;
        }
//        LEVEL 00 <----> LEVEL 01
        else if(GP.currentLevel == GP.level00 && GP.player.pos.x >= GP.screenWidth) {
            GP.currentLevel = GP.level01;
            GP.player.pos.x = 0;
            GP.currentMonsters = GP.monsters01;
            GP.currentCollectibles = GP.collectibles01;
        }
        else if(GP.currentLevel == GP.level01 && GP.player.pos.x <= 0) {
            GP.currentLevel = GP.level00;
            GP.player.pos.x = GP.screenWidth;
            GP.currentMonsters = GP.monsters00;
            GP.currentCollectibles = GP.collectibles00;
        }
//        LEVEL 01 <----> LEVEL 02
        else if(GP.currentLevel == GP.level01 && GP.player.pos.x >= GP.screenWidth) {
            GP.currentLevel = GP.level02;
            GP.player.pos.x = 0;
            GP.currentMonsters = GP.monsters02;
            GP.currentCollectibles = GP.collectibles02;
        }
        else if(GP.currentLevel == GP.level02 && GP.player.pos.x <= 0) {
            GP.currentLevel = GP.level01;
            GP.player.pos.x = GP.screenWidth;
            GP.currentMonsters = GP.monsters01;
            GP.currentCollectibles = GP.collectibles01;
        }
//        LEVEL 02 <----> LEVEL 12
        else if(GP.currentLevel == GP.level12 && GP.player.pos.y <= -32) {
            GP.currentLevel = GP.level02;
            GP.player.pos.y = GP.screenHeight - 32;
            GP.currentMonsters = GP.monsters02;
            GP.currentCollectibles = GP.collectibles02;
        }
        else if(GP.currentLevel == GP.level02 && GP.player.pos.y >= GP.screenHeight) {
            GP.currentLevel = GP.level12;
            GP.player.pos.y = -32;
            GP.currentMonsters = GP.monsters12;
            GP.currentCollectibles = GP.collectibles12;
        }
//        LEVEL 12 <----> LEVEL 22
        else if(GP.currentLevel == GP.level22 && GP.player.pos.y <= -32) {
            GP.currentLevel = GP.level12;
            GP.player.pos.y = GP.screenHeight - 32;
            GP.currentMonsters = GP.monsters12;
            GP.currentCollectibles = GP.collectibles12;
        }
        else if(GP.currentLevel == GP.level12 && GP.player.pos.y >= GP.screenHeight) {
            GP.currentLevel = GP.level22;
            GP.player.pos.y = -32;
            GP.currentMonsters = GP.monsters22;
            GP.currentCollectibles = GP.collectibles22;
        }
//        LEVEL 21 <----> LEVEL 22
        else if(GP.currentLevel == GP.level21 && GP.player.pos.x >= GP.screenWidth) {
            GP.currentLevel = GP.level22;
            GP.player.pos.x = 0;
            GP.currentMonsters = GP.monsters22;
            GP.currentCollectibles = GP.collectibles22;
        }
        else if(GP.currentLevel == GP.level22 && GP.player.pos.x <= 0) {
            GP.currentLevel = GP.level21;
            GP.player.pos.x = GP.screenWidth;
            GP.currentMonsters = GP.monsters21;
            GP.currentCollectibles = GP.collectibles21;
        }
//        LEVEL 21 ----> LEVEL 11 (BOSS) no way back
        else if(GP.currentLevel == GP.level21 && GP.player.pos.y <= -32) {
            GP.currentLevel = GP.level11;
            GP.player.pos.y = GP.screenHeight - 200;
            GP.currentMonsters = GP.monsters11;
            GP.currentCollectibles = GP.collectibles11;
        }
        for(Entity item : GP.inventory) {
            if(!GP.currentCollectibles.contains(item))
                GP.currentCollectibles.add(item);
        }
    }
}
