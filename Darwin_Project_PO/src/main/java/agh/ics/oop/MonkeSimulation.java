package agh.ics.oop;

import javafx.application.Application;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MonkeSimulation implements Runnable{
    public final MonkeMap map;
    private Vector2d[] monkePositions;
    private Random random = new Random();
    public final int FPS;
    public final int iterations;
    public int noMonkes;
    public boolean run = false;
    private final MainApp app;
    public int noCurMonkes;
    public int noBananas;
    public float averageEnergy;
    public float averageDeadAge;
    private int deadMonkes = 0;
    private int deadMonkesAgeSum = 0;
    public float averageChildren;
    public MonkeGen dominantGenotype;
    public int simulationDays = 0;
    public WriteToFile file;
    public int dayOfDeath = 0;
    public int childrenOfFollowed = 0;
    public boolean following = false;
    public boolean showing = false;
    public int magicTimes = 3;
    public boolean magic;
    public int maxEnergy;

    public MonkeSimulation(MainApp app, int mapWidth, int mapHeight, int jungleWidth, int jungleHeight, int startEnergy, int bananaEnergy, int FPS, int iterations, int noMonkes, boolean folded, WriteToFile file, boolean magic) {
        this.app = app;
        this.FPS = FPS;
        this.file = file;
        this.iterations = iterations;
        this.noMonkes = noMonkes;
        this.magic = magic;
        this.map = new MonkeMap(mapWidth, mapHeight, jungleWidth, jungleHeight, startEnergy, bananaEnergy, folded);
        placeMonkes(noMonkes);
    }
    public void placeMonkes(int noMonkes) {
        this.monkePositions = new Vector2d[noMonkes];
        for(int i = 0; i < noMonkes; i++) {
            this.monkePositions[i] = new Vector2d(random.nextInt(map.getMapWidth()), random.nextInt(map.getMapHeight()));
        }
        for(Vector2d monkePosition : this.monkePositions) {
            map.placeMonke(new Monke(map, monkePosition, map.getMonkeStartEnergy()));
        }
    }
    public void placeMagicMonkes() {
        List<Monke> monkesToAdd = new ArrayList<>();
        for(List<Monke> monkes : map.hashMonkes.values()) {
            if(monkes.size() > 0) {
                monkesToAdd.addAll(monkes);
            }
        }
        for(Monke monke : monkesToAdd) map.placeMonke(new Monke(map, monke.getPosition(), map.getMonkeStartEnergy()));
    }
    public void placeBananaInJungle() {
        if(!map.freeJunglePlacesList.isEmpty()) {
            int randomIdx = random.nextInt(map.freeJunglePlacesList.size());
            Vector2d bananaPosition = map.freeJunglePlacesList.get(randomIdx);
            map.placeBanana(new Banana(bananaPosition, map.getBananaStartEnergy()));
            map.freeJunglePlacesList.remove(randomIdx);
        }
    }
    public void placeBananaOutOfJungle() {
        if (!map.freeMapPlacesList.isEmpty()) {
            int randomIdx = random.nextInt(map.freeMapPlacesList.size());
            Vector2d bananaPosition = map.freeMapPlacesList.get(randomIdx);
            map.placeBanana(new Banana(bananaPosition, map.getBananaStartEnergy()));
            map.freeMapPlacesList.remove(randomIdx);
        }
    }
    public void changeRun() {
        this.run = !this.run;
    }
    public void stop() {
        this.run = false;
    }
    public void showGenotype(MonkeGen monkeGen, boolean folded) {
        app.showMonkeGenotype(monkeGen, folded);
    }

    public void monkeOneDay(MonkeMap map) throws IOException {
        if(run) {
            maxEnergy = 0;
            simulationDays += 1;
            noCurMonkes = 0;
            averageEnergy = 0;
            List<Monke> monkesToMove = new ArrayList<>();
            List<Monke> monkesToEat = new ArrayList<>();
            List<Monke> monkesToRemove = new ArrayList<>();

            noBananas = map.hashBanana.size();

            //        MOVE MONKE
            for (List<Monke> monkes : map.hashMonkes.values()) {
                if (monkes.size() > 0) {
                    for (Monke monke : monkes) {
                        monke.monkeAge += 1;
                        if(monke.getMonkeEnergy() >= maxEnergy) {
                            maxEnergy = monke.getMonkeEnergy();
                        }
                        if (monke.getMonkeEnergy() <= 0) {
                            monkesToRemove.add(monke);
                            if(monke.checkFollow())
                                this.dayOfDeath = simulationDays;
                        } else {
                            monkesToMove.add(monke);
                            noCurMonkes += 1;
                            averageEnergy += monke.getMonkeEnergy();
                        }
                    }
                }
            }
            for (Monke monke : monkesToRemove) {
                deadMonkes += 1;
                deadMonkesAgeSum += monke.monkeAge;
                map.removeMonke(monke);
            }
            if(deadMonkes != 0)
                averageDeadAge = (float) deadMonkesAgeSum / (float) deadMonkes;
            for (Monke monke : monkesToMove) {
                map.removeMonke(monke);
                int gen = monke.monkeGenotype.randomGen();
                if (gen == 0 || gen == 4) {
                    MapDirection nextMove = monke.getOrientation().rotation(gen);
                    if (map.canMoveTo(monke.getPosition().add(nextMove.toUnitVector())))
                        monke.moveMonke(nextMove, map.folded);
                } else {
                    monke.spinMonke(gen);
                }
                map.placeMonke(monke);
            }
            //        EAT MONKE
            for (List<Monke> monkes : map.hashMonkes.values()) {
                if (monkes.size() > 0) {
                    int maxi = map.getMaxMonkeEnergyAt(monkes.get(0).getPosition());
                    for (Monke monke : monkes) {
                        if (monke.getMonkeEnergy() == maxi && map.isOccupiedBanana(monke.getPosition())) {
                            monkesToEat.add(monke);
                            map.getBananaAt(monke.getPosition()).addEater();
                        }
                    }
                }
            }
            for (Monke monke : monkesToEat) {
                Vector2d position = monke.getPosition();
                if (map.getBananaAt(position) != null) {
                    Banana banana = map.getBananaAt(position);
                    monke.addMonkeEnergy(banana.getBananaEnergy() / banana.bananaEaters);
                }
            }
            for (Monke monke : monkesToEat) {
                Vector2d position = monke.getPosition();
                map.removeBanana(position);
                if (map.isInJungle(position)) {
                    map.freeJunglePlacesList.add(position);
                }
                else {
                    map.freeMapPlacesList.add(position);
                }
            }
            //        REPRODUCE MONKE
            for (List<Monke> monkes : map.hashMonkes.values()) {
                if (monkes.size() > 0) {
                    List<Monke> monkesToReproduce = map.getMonkesToReproduce(monkes.get(0).getPosition());
                    int size = monkesToReproduce.size();
                    Monke newMonke;
                    if (size >= 2) {
                        while (true) {
                            int idx1 = random.nextInt(size);
                            int idx2 = random.nextInt(size);
                            if (idx1 != idx2) {
                                Monke monke1 = monkesToReproduce.get(idx1);
                                Monke monke2 = monkesToReproduce.get(idx2);
                                if(monke1.getMonkeEnergy() != 0 && monke2.getMonkeEnergy() != 0) {
                                    newMonke = monke1.reproduceMonke(monke2);
                                    noCurMonkes += 1;
                                    averageEnergy += newMonke.getMonkeEnergy();
                                    break;

                                }
                            }
                        }
                        map.placeMonke(newMonke);
                    }
                }
            }
            averageEnergy /= noCurMonkes;
            int noChildren = 0;
            for (List<Monke> monkes : map.hashMonkes.values()) {
                if (monkes.size() > 0) {
                    for (Monke monke : monkes) {
                        noChildren += monke.monkeMinis;
                        if(monke.checkFollow())
                            this.childrenOfFollowed += monke.monkeMinis;
                    }
                }
            }
            averageChildren = (float) noChildren / (float) noCurMonkes;
            dominantGenotype = findDominantGenotype();
            for(List<Monke> monkes : map.hashMonkes.values()) { if (monkes.size() > 0) { for (Monke monke : monkes) { monke.reduceMonkeEnergy(1); }}}
            if(noCurMonkes <= 5 && magicTimes != 0) {
                placeMagicMonkes();
                this.magicTimes -= 1;
            }
            placeBananaInJungle();
            placeBananaOutOfJungle();
            app.updateMap();
        }
    }
    public MonkeGen findDominantGenotype() {
        MonkeGen doma = new MonkeGen();
        MonkeGen[] genotypes = new MonkeGen[noCurMonkes];
        int idx = 0;
        int i = 0;
        for(List<Monke> monkes : map.hashMonkes.values()) {
            if (monkes.size() > 0) {
                for (Monke monke : monkes) {
                    genotypes[i] = monke.monkeGenotype;
                    i += 1;
                }
            }
        }
        for(int j = 0; j < genotypes.length; j++) {
            MonkeGen moda = genotypes[j];
            int c = 0;
            for(i = 0; i < genotypes.length; i++) {
                if(moda == genotypes[i])
                    c += 1;
            }
            if(c > idx) {
                idx = c;
                doma = moda;
            }
        }
        return doma;
    }
    public void followMonke(Monke monke) {
        this.dayOfDeath = 0;
        this.childrenOfFollowed = 0;
        monke.makeFollow();
        this.following = true;
    }
    public void stopFollowing() {
        this.following = false;
    }

    public void run() {
        while(true) {
            try {
                monkeOneDay(map);
                if(!map.folded)
                    file.writeStats1(this, false);
                else file.writeStats2(this, false);
                Thread.sleep(this.FPS);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
