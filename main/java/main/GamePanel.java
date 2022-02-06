package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int FPS = 60;
    final int tileSize = 64;
    final int mapWidth = 16;
    final int mapHeight = 12;
    final int screenWidth = tileSize * mapWidth;
    final int screenHeight = tileSize * mapHeight;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public CollisionHandler collisionHandler = new CollisionHandler(this);
    Vector2d pos = new Vector2d(5 * 64, 7 * 64);

    Level level20 = new Level(this, "20");
    Level level10 = new Level(this, "10");
    Level level00 = new Level(this, "00");
    Level level01 = new Level(this, "01");
    Level level02 = new Level(this, "02");
    Level level12 = new Level(this, "12");
    Level level22 = new Level(this, "22");
    Level level21 = new Level(this, "21");
    Level level11 = new Level(this, "11");

    Player player = new Player(pos, 8, 2, 3, this, keyHandler);
    List<Entity> inventory = new ArrayList<>();
    EntitiesLayout entitiesLayout10 = new EntitiesLayout("10", this);
    List<Monster> monsters10 = entitiesLayout10.monsters;
    List<Entity> collectibles10 = entitiesLayout10.collectibles;
    EntitiesLayout entitiesLayout20 = new EntitiesLayout("20", this);
    List<Monster> monsters20 = entitiesLayout20.monsters;
    List<Entity> collectibles20 = entitiesLayout20.collectibles;
    EntitiesLayout entitiesLayout00 = new EntitiesLayout("00", this);
    List<Monster> monsters00 = entitiesLayout00.monsters;
    List<Entity> collectibles00 = entitiesLayout00.collectibles;
    EntitiesLayout entitiesLayout01 = new EntitiesLayout("01", this);
    List<Monster> monsters01 = entitiesLayout01.monsters;
    List<Entity> collectibles01 = entitiesLayout01.collectibles;
    EntitiesLayout entitiesLayout02 = new EntitiesLayout("02", this);
    List<Monster> monsters02 = entitiesLayout02.monsters;
    List<Entity> collectibles02 = entitiesLayout02.collectibles;
    EntitiesLayout entitiesLayout12 = new EntitiesLayout("12", this);
    List<Monster> monsters12 = entitiesLayout12.monsters;
    List<Entity> collectibles12 = entitiesLayout12.collectibles;
    EntitiesLayout entitiesLayout22 = new EntitiesLayout("22", this);
    List<Monster> monsters22 = entitiesLayout22.monsters;
    List<Entity> collectibles22 = entitiesLayout22.collectibles;
    EntitiesLayout entitiesLayout21 = new EntitiesLayout("21", this);
    List<Monster> monsters21 = entitiesLayout21.monsters;
    List<Entity> collectibles21 = entitiesLayout21.collectibles;
    EntitiesLayout entitiesLayout11 = new EntitiesLayout("11", this);
    List<Monster> monsters11 = entitiesLayout11.monsters;
    List<Entity> collectibles11 = entitiesLayout11.collectibles;


    Level currentLevel = level20;
    List<Monster> currentMonsters = monsters20;
    List<Entity> currentCollectibles = collectibles20;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {
            update();
            repaint();
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        player.update();
        for(Monster monster : currentMonsters) {
            if(monster != null)
                monster.update();
        }
        List<Entity> toRemove = new ArrayList<>();
        for(Entity collectible : currentCollectibles) {
            if(collectible != null)
                collectible.update();
            assert collectible != null;
            if(collectible.used)
                    toRemove.add(collectible);
        }
        for(Entity c : toRemove) {
            if(c instanceof Chest)
                currentCollectibles.add(((Chest) c).contains);
            currentCollectibles.remove(c);
        }

        currentLevel.update();
        if(player.getHP() <= 0) {
            gameThread.stop();
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        currentLevel.draw(g2);
        for(Monster monster : currentMonsters) {
            if(monster != null) {
                monster.draw(g2);
                if(monster instanceof Pikachu) {
                    for(Projectile projectile : ((Pikachu) monster).projectiles) {
                        projectile.draw(g2);
                    }
                }
                if(monster instanceof Lapras) {
                    for(Projectile projectile : ((Lapras) monster).projectiles) {
                        projectile.draw(g2);
                    }
                }
                if(monster instanceof PurpleGuy) {
                    for(Projectile projectile : ((PurpleGuy) monster).projectiles) {
                        projectile.draw(g2);
                    }
                }
            }
        }
        for(Entity collectible : currentCollectibles) {
            if(collectible != null)
                collectible.draw(g2);
                if(collectible instanceof Wand) {
                    for(Projectile p : ((Wand) collectible).projectiles) {
                        p.draw(g2);
                    }
                }
        }
        if(player.getDirection() == MapDirection.NORTH || player.getDirection() == MapDirection.WEST) {
            for(Entity item : inventory) {
                item.draw(g2);
            }
            player.draw(g2);
        }
        else {
            player.draw(g2);
            for(Entity item : inventory) {
                item.draw(g2);
            }
        }
        g2.dispose();
    }
}
