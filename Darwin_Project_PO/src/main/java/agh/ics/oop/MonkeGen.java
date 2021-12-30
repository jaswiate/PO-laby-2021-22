package agh.ics.oop;
import java.util.Arrays;
import java.util.Random;

public class MonkeGen {
        private final int genSize = 32;
        private final int noGenes = 8;
        private final int[] monkeGenotype = new int[this.genSize];
        private final Random random = new Random();

    public MonkeGen() { //constructor for starting monke
        for(int i = 0; i < this.genSize; i++) {
            this.monkeGenotype[i] = random.nextInt(noGenes);
        }
        Arrays.sort(this.monkeGenotype);
    }
    public MonkeGen(Monke monke1, Monke monke2) { //constructor for every other monke (children)
        Monke monkeStrong;
        Monke monkeWeak;
        int energyStrong;
        int energyWeak;
        if(monke1.getMonkeEnergy() >= monke2.getMonkeEnergy()) {
            monkeStrong = monke1;
            energyStrong = monke1.getMonkeEnergy();
            monkeWeak = monke2;
            energyWeak = monke2.getMonkeEnergy();
        }
        else {
            monkeStrong = monke2;
            energyStrong = monke2.getMonkeEnergy();
            monkeWeak = monke1;
            energyWeak = monke1.getMonkeEnergy();
        }
        int strongSide = random.nextInt(2);
        if(strongSide == 1) {
            float div = ((float) energyWeak / (float) (energyWeak + energyStrong)) * this.genSize;
            for(int i = 0; i < div; i++) {
                this.monkeGenotype[i] = monkeWeak.monkeGenotype.genAt(i);
            }
            if(div != genSize) {
                for (int i = (int) div; i < genSize; i++) {
                    this.monkeGenotype[i] = monkeStrong.monkeGenotype.genAt(i);
                }
            }
        }
        else {
            float div = ((float) energyStrong / (float) (energyStrong + energyWeak)) * this.genSize;
            for(int i = 0; i < div; i++) {
                this.monkeGenotype[i] = monkeStrong.monkeGenotype.genAt(i);
            }
            if(div != genSize) {
                for (int i = (int) div; i < genSize; i++) {
                    this.monkeGenotype[i] = monkeWeak.monkeGenotype.genAt(i);
                }
            }
        }
        Arrays.sort(this.monkeGenotype);
    }
    public int randomGen() { return this.monkeGenotype[random.nextInt(this.genSize)]; }
    public int genAt(int idx) { return this.monkeGenotype[idx]; }
    public String toString() {
        String genom = "[";
        for(int i = 0; i < this.monkeGenotype.length - 1; i++) {
            genom += this.monkeGenotype[i] + ", ";
        }
        genom += this.monkeGenotype[this.monkeGenotype.length - 1] + "]";
        return genom;
    }
}
