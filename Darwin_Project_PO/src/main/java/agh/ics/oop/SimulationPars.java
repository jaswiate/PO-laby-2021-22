package agh.ics.oop;

public class SimulationPars {
    private final int noMonkes;
    private final int mapWidth;
    private final int mapHeight;
    private final int jungleWidth;
    private final int jungleHeight;
    private final int monkeStartEnergy;
    private final int bananaStartEnergy;

    public SimulationPars(int mapWidth, int mapHeight, int jungleWidth, int jungleHeight, int startEnergy, int bananaEnergy, int noMonkes){
        this.monkeStartEnergy = startEnergy;
        this.bananaStartEnergy = bananaEnergy;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.noMonkes = noMonkes;

    }
    public int getMapWidth() {
        return mapWidth;
    }
    public int getMapHeight() {
        return mapHeight;
    }
    public int getStartEnergy() {
        return monkeStartEnergy;
    }
    public int getEnergyBanana() {
        return bananaStartEnergy;
    }
    public int getNumberOfAnimals() {
        return noMonkes;
    }
}

