package main;

import java.util.ArrayList;
import java.util.List;

public class EntitiesLayout {
    List<Monster> monsters = new ArrayList<>();
    List<Entity> collectibles = new ArrayList<>();
    GamePanel GP;

    public EntitiesLayout(String levelNum, GamePanel GP) {
        this.GP = GP;
        switch (levelNum) {
            case "20" -> {
                MapDirection[] walker0_path = {MapDirection.SOUTH, MapDirection.SOUTH};
                Walker walker0 = new Walker(new Vector2d(12 * 64, 4 * 64), 3, 1, 2, walker0_path, GP);
                Pikachu pikachu0 = new Pikachu(new Vector2d(5 * 64, 64), 2, 2, 2, null, GP);
                pikachu0.setDirection(MapDirection.EAST);
                monsters.add(walker0);
                monsters.add(pikachu0);
                Sword sword0 = new Sword(new Vector2d(14 * 64, 6 * 64), "iron", GP);
                Chest chest0 = new Chest(new Vector2d(14 * 64, 5 * 64), sword0, GP);
                collectibles.add(chest0);
            }
            case "10" -> {
                MapDirection[] walker0_path = {MapDirection.EAST, MapDirection.EAST};
                MapDirection[] walker1_path = {MapDirection.NORTH, MapDirection.NORTH};
                MapDirection[] lapras0_path = {MapDirection.NORTH, MapDirection.EAST, MapDirection.EAST, MapDirection.EAST, MapDirection.NORTH};
                Walker walker0 = new Walker(new Vector2d(9 * 64, 7 * 64), 3, 1, 2, walker0_path, GP);
                Walker walker1 = new Walker(new Vector2d(5 * 64, 5 * 64), 3, 1, 2, walker1_path, GP);
                Lapras lapras0 = new Lapras(new Vector2d(6 * 64, 6 * 64), 2, 3, 1, lapras0_path, GP);
                monsters.add(walker0);
                monsters.add(walker1);
                monsters.add(lapras0);
            }
            case "00" -> {
                MapDirection[] walker0_path = {MapDirection.WEST, MapDirection.NORTH};
                MapDirection[] walker1_path = {MapDirection.NORTH, MapDirection.NORTH, MapDirection.EAST};
                Walker walker0 = new Walker(new Vector2d(11 * 64, 2 * 64), 3, 1, 2, walker0_path, GP);
                Walker walker1 = new Walker(new Vector2d(3 * 64, 9 * 64), 3, 1, 2, walker1_path, GP);
                Pikachu pikachu0 = new Pikachu(new Vector2d(13 * 64, 9 * 64), 2, 2, 2, null, GP);
                pikachu0.setDirection(MapDirection.NORTH);
                Pikachu pikachu1 = new Pikachu(new Vector2d(14 * 64, 64), 2, 2, 2, null, GP);
                pikachu1.setDirection(MapDirection.WEST);
                monsters.add(walker0);
                monsters.add(walker1);
                monsters.add(pikachu0);
                monsters.add(pikachu1);
                RedHeart heart0 = new RedHeart(new Vector2d(14 * 64, 3 * 64), 0, GP);
                Chest chest0 = new Chest(new Vector2d(14 * 64, 2 * 64), heart0, GP);
                collectibles.add(chest0);
            }
            case "01" -> {
                MapDirection[] walker0_path = {MapDirection.EAST, MapDirection.EAST, MapDirection.EAST, MapDirection.EAST, MapDirection.SOUTH, MapDirection.SOUTH};
                MapDirection[] walker1_path = {MapDirection.NORTH, MapDirection.NORTH};
                MapDirection[] lapras0_path = {MapDirection.EAST, MapDirection.NORTH, MapDirection.EAST, MapDirection.EAST, MapDirection.SOUTH, MapDirection.SOUTH};
                MapDirection[] lapras1_path = {MapDirection.NORTH, MapDirection.NORTH, MapDirection.EAST, MapDirection.EAST, MapDirection.EAST, MapDirection.EAST};
                Walker walker0 = new Walker(new Vector2d(3 * 64, 6 * 64), 3, 1, 2, walker0_path, GP);
                Walker walker1 = new Walker(new Vector2d(12 * 64, 5 * 64), 3, 1, 2, walker1_path, GP);
                Lapras lapras0 = new Lapras(new Vector2d(6 * 64, 2 * 64), 2, 3, 1, lapras0_path, GP);
                Lapras lapras1 = new Lapras(new Vector2d(9 * 64, 9 * 64), 2, 3, 1, lapras1_path, GP);
                monsters.add(walker0);
                monsters.add(walker1);
                monsters.add(lapras0);
                monsters.add(lapras1);
            }
            case "02" -> {
                Pikachu pikachu0 = new Pikachu(new Vector2d(4 * 64, 8 * 64), 2, 2, 2, null, GP);
                pikachu0.setDirection(MapDirection.NORTH);
                Pikachu pikachu1 = new Pikachu(new Vector2d(8 * 64, 3 * 64), 2, 2, 2, null, GP);
                pikachu1.setDirection(MapDirection.SOUTH);
                Pikachu pikachu2 = new Pikachu(new Vector2d(12 * 64, 5 * 64), 2, 2, 2, null, GP);
                pikachu2.setDirection(MapDirection.WEST);
                monsters.add(pikachu0);
                monsters.add(pikachu1);
                monsters.add(pikachu2);
                RedHeart heart0 = new RedHeart(new Vector2d(14 * 64, 8 * 64), 1, GP);
                collectibles.add(heart0);
            }
            case "12" -> {
                MapDirection[] walker0_path = {MapDirection.SOUTH, MapDirection.EAST};
                MapDirection[] walker1_path = {MapDirection.WEST, MapDirection.WEST};
                MapDirection[] lapras0_path = {MapDirection.EAST, MapDirection.EAST, MapDirection.EAST, MapDirection.EAST};
                MapDirection[] lapras1_path = {MapDirection.EAST, MapDirection.EAST};
                Walker walker0 = new Walker(new Vector2d(9 * 64, 3 * 64), 3, 1, 2, walker0_path, GP);
                Walker walker1 = new Walker(new Vector2d(14 * 64, 10 * 64), 3, 1, 2, walker1_path, GP);
                Lapras lapras0 = new Lapras(new Vector2d(8 * 64, 5 * 64), 2, 3, 1, lapras0_path, GP);
                Lapras lapras1 = new Lapras(new Vector2d(10 * 64, 7 * 64), 2, 3, 1, lapras1_path, GP);
                monsters.add(walker0);
                monsters.add(walker1);
                monsters.add(lapras0);
                monsters.add(lapras1);
            }
            case "22" -> {
                MapDirection[] walker0_path = {MapDirection.SOUTH, MapDirection.WEST, MapDirection.WEST, MapDirection.NORTH};
                MapDirection[] walker1_path = {MapDirection.NORTH};
                MapDirection[] walker2_path = {MapDirection.NORTH, MapDirection.NORTH};
                Walker walker0 = new Walker(new Vector2d(5 * 64, 5 * 64), 3, 1, 2, walker0_path, GP);
                Walker walker1 = new Walker(new Vector2d(9 * 64, 10 * 64), 3, 1, 2, walker1_path, GP);
                Walker walker2 = new Walker(new Vector2d(12 * 64, 10 * 64), 3, 1, 2, walker2_path, GP);
                Pikachu pikachu0 = new Pikachu(new Vector2d(9 * 64, 2 * 64), 2, 2, 2, null, GP);
                pikachu0.setDirection(MapDirection.SOUTH);
                Pikachu pikachu1 = new Pikachu(new Vector2d(64, 9 * 64), 2, 2, 2, null, GP);
                pikachu1.setDirection(MapDirection.EAST);
                monsters.add(walker0);
                monsters.add(walker1);
                monsters.add(walker2);
                monsters.add(pikachu0);
                monsters.add(pikachu1);
                Wand wand0 = new Wand(new Vector2d(14 * 64, 8 * 64), GP);
                Chest chest0 = new Chest(new Vector2d(14 * 64, 7 * 64), wand0, GP);
                collectibles.add(chest0);
            }
            case "21" -> {
                MapDirection[] walker0_path = {MapDirection.WEST, MapDirection.WEST, MapDirection.SOUTH};
                MapDirection[] walker1_path = {MapDirection.NORTH, MapDirection.EAST, MapDirection.NORTH};
                MapDirection[] walker2_path = {MapDirection.EAST, MapDirection.EAST, MapDirection.SOUTH};
                MapDirection[] walker3_path = {MapDirection.WEST, MapDirection.WEST, MapDirection.NORTH};
                MapDirection[] lapras0_path = {MapDirection.EAST, MapDirection.EAST};
                MapDirection[] lapras1_path = {MapDirection.NORTH};
                Walker walker0 = new Walker(new Vector2d(14 * 64, 4 * 64), 3, 1, 2, walker0_path, GP);
                Walker walker1 = new Walker(new Vector2d(9 * 64, 3 * 64), 3, 1, 2, walker1_path, GP);
                Walker walker2 = new Walker(new Vector2d(64, 3 * 64), 3, 1, 2, walker2_path, GP);
                Walker walker3 = new Walker(new Vector2d(3 * 64, 6 * 64), 3, 1, 2, walker3_path, GP);
                Pikachu pikachu0 = new Pikachu(new Vector2d(12 * 64, 2 * 64), 2, 2, 2, null, GP);
                pikachu0.setDirection(MapDirection.SOUTH);
                Pikachu pikachu1 = new Pikachu(new Vector2d(64, 64), 2, 2, 2, null, GP);
                pikachu1.setDirection(MapDirection.SOUTH);
                Lapras lapras0 = new Lapras(new Vector2d(6 * 64, 4 * 64), 2, 3, 1, lapras0_path, GP);
                Lapras lapras1 = new Lapras(new Vector2d(9 * 64, 6 * 64), 2, 3, 1, lapras1_path, GP);
                monsters.add(walker0);
                monsters.add(walker1);
                monsters.add(walker2);
                monsters.add(walker3);
                monsters.add(pikachu1);
                monsters.add(pikachu0);
                monsters.add(lapras0);
                monsters.add(lapras1);
                Sword sword0 = new Sword(new Vector2d(2 * 64, 9 * 64), "diamond", GP);
                RedHeart heart0 = new RedHeart(new Vector2d(11 * 64, 64), 0, GP);
                collectibles.add(sword0);
                collectibles.add(heart0);
            }
            case "11" -> {
                PurpleGuy purpleGuy = new PurpleGuy(new Vector2d(6 * 64, 2 * 64), 50, 3, 2, null, GP);
                monsters.add(purpleGuy);
                RedHeart heart0 = new RedHeart(new Vector2d(2 * 64, 7 * 64), 0, GP);
                RedHeart heart1 = new RedHeart(new Vector2d(13 * 64, 7 * 64), 0, GP);
                collectibles.add(heart0);
                collectibles.add(heart1);
            }
        }
    }
}
