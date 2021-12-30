package agh.ics.oop;
import java.util.*;

public class MonkeMap {
    private final int mapWidth;
    private final int mapHeight;
    private final int jungleWidth;
    private final int jungleHeight;
    private final int monkeStartEnergy;
    private final int bananaStartEnergy;
    public final Vector2d mapBottomLeft = new Vector2d(0, 0);
    public final Vector2d mapTopRight;
    public final Vector2d jungleBottomLeft;
    public final Vector2d jungleTopRight;
    protected Map<Vector2d, List<Monke>> hashMonkes = new HashMap<>();
    protected Map<Vector2d, Banana> hashBanana = new HashMap<>();
    public List<Vector2d> freeJunglePlacesList = new ArrayList<>();
    public List<Vector2d> freeMapPlacesList = new ArrayList<>();
    public boolean folded;

    public MonkeMap(int mapWidth, int mapHeight, int jungleWidth, int jungleHeight, int startEnergy, int bananaEnergy, boolean folded) {
        this.monkeStartEnergy = startEnergy;
        this.bananaStartEnergy = bananaEnergy;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.mapTopRight = new Vector2d(mapWidth - 1, mapHeight - 1);
        this.jungleBottomLeft = new Vector2d((mapWidth - jungleWidth) / 2, (mapHeight - jungleHeight) / 2);
        this.jungleTopRight = new Vector2d((mapWidth + jungleWidth) / 2 - 1, (mapHeight + jungleHeight) / 2 - 1);
        this.folded = folded;
        for(int i = 0; i < mapWidth; i++) {
            for(int j = 0; j < mapHeight; j++) {
                Vector2d position = new Vector2d(i, j);
                if(this.isInJungle(position)) { this.freeJunglePlacesList.add(position); }
                else { this.freeMapPlacesList.add(position); }
            }
        }

    }
    public boolean canMoveTo(Vector2d position) {
        if(!this.folded)
            return position.x >= 0 && position.y >= 0 && position.x < this.mapWidth && position.y < this.mapHeight;
        return true;
    }
    public boolean isOccupiedBanana(Vector2d position) {
        return this.hashBanana.containsKey(position);
    }
    public boolean isOccupiedMonke(Vector2d position) {
        List<Monke> monkesAt = getMonkesAt(position);
        if(monkesAt != null) {
            for (Monke monke : monkesAt) {
                if (monke.getPosition().equals(position)) {
                    return true;
                }
            }
        }
        return false;
    }
    public Banana getBananaAt(Vector2d position) {
        return hashBanana.get(position);
    }
    public List<Monke> getMonkesAt(Vector2d position) {
        return hashMonkes.get(position);
    }
    public Monke getMonkeAt(Vector2d position) {
        if(!hashMonkes.get(position).isEmpty())
            return hashMonkes.get(position).get(0);
        return null;
    }
    public int getMonkesEnergy(Vector2d position) {
        int energy = 0;
        for(Monke monke : getMonkesAt(position)) {
            energy += monke.getMonkeEnergy();
        }
        return energy;
    }
    public Object getObjectAt(Vector2d position) {
        if(hashMonkes.get(position) != null && hashMonkes.get(position).size() != 0)
            return hashMonkes.get(position).get(0);
        if(hashBanana.get(position) != null)
            return hashBanana.get(position);
        return null;
    }
    public int getMaxMonkeEnergyAt(Vector2d position) {
        List<Monke> monkesAt = getMonkesAt(position);
        int maxi = 0;
        for (Monke monke : monkesAt) {
            if (monke.getMonkeEnergy() >= maxi)
                maxi = monke.getMonkeEnergy();
        }
        return maxi;
    }
    public List<Monke> getMonkesToReproduce(Vector2d position) {
        List<Monke> monkesAt = getMonkesAt(position);
        List<Monke> monkesToReproduce = new ArrayList<>();
        int size = monkesAt.size();
        if(size > 1) {
            monkesAt.sort(new compareMonkesEnergy());
            int maxi = monkesAt.get(size - 1).getMonkeEnergy();
            for(Monke monke : monkesAt) {
                if(monke.getMonkeEnergy() == maxi)
                    monkesToReproduce.add(monke);
            }
            if(monkesToReproduce.size() == 1) {
                int i = 2;
                if(size == 2) {
                    monkesToReproduce.add(monkesAt.get(0));
                }
                while(size - i - 1 >= 0) {
                    if(monkesAt.get(size - i).getMonkeEnergy() == monkesAt.get(size - i - 1).getMonkeEnergy()) {
                        monkesToReproduce.add(monkesAt.get(size - i));
                        monkesToReproduce.add(monkesAt.get(size - i - 1));
                        i += 2;
                    }
                    else if(monkesAt.get(size - i).getMonkeEnergy() > monkesAt.get(size - i - 1).getMonkeEnergy()) {
                        monkesToReproduce.add(monkesAt.get(size - i));
                        break;
                    }
                }
            }
        }
        return monkesToReproduce;
    }
    public void placeMonke(Monke monke) {
        List<Monke> monkesAt = getMonkesAt(monke.getPosition());
        if(canMoveTo(monke.getPosition())) {
            if(monkesAt == null) { monkesAt = new ArrayList<>(); }
            monkesAt.add(monke);
            this.hashMonkes.put(monke.getPosition(), monkesAt);
        }
    }
    public void removeMonke(Monke monke) {
        List<Monke> monkesAt = getMonkesAt(monke.getPosition());
        if(monkesAt != null)
            monkesAt.remove(monke);
    }
    public void placeBanana(Banana banana) {
        if(!isOccupiedBanana(banana.getPosition()))
            this.hashBanana.put(banana.getPosition(), banana);
    }
    public void removeBanana(Vector2d bananaPosition) {
        hashBanana.remove(bananaPosition);
    }

    public boolean isInJungle(Vector2d position) {
        return position.follows(jungleBottomLeft) && position.precedes(jungleTopRight);
    }
    public int getMapWidth() {
        return this.mapWidth;
    }
    public int getMapHeight() {
        return this.mapHeight;
    }
    public int getMonkeStartEnergy() {
        return this.monkeStartEnergy;
    }
    public int getBananaStartEnergy() {
        return this.bananaStartEnergy;
    }
    public int getJungleWidth() {
        return this.jungleWidth;
    }
    public int getJungleHeight() {
        return this.jungleHeight;
    }
}