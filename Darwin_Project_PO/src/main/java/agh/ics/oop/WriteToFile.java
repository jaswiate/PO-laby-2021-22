package agh.ics.oop;
import java.io.*;

public class WriteToFile {
    private final File statsFile;
    int monkes1, bananas1, energy1, children1, age1, monkes2, bananas2, energy2, children2, age2;

    public WriteToFile(String path, boolean folded) throws IOException {
        if(!folded) {
            this.statsFile = new File(path);
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(statsFile));
            writer1.write("MAP WITH WALL");
            writer1.newLine();
            writer1.write("Current Day, Number of Monkes, Number of Bananas, Average Energy, Average Children, Average Dead Age");
            writer1.newLine();
            writer1.flush();
            writer1.close();
        }
        else {
            this.statsFile = new File(path);
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(statsFile));
            writer2.write("FOLDED MAP");
            writer2.newLine();
            writer2.write("Current Day, Number of Monkes, Number of Bananas, Average Energy, Average Children, Average Dead Age");
            writer2.newLine();
            writer2.flush();
            writer2.close();
        }
    }
    public void writeStats1(MonkeSimulation s1, boolean endOfLife) throws IOException {
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(statsFile, true));
        if(!endOfLife) {
            monkes1 += s1.noCurMonkes;
            bananas1 += s1.noBananas;
            energy1 += s1.averageEnergy;
            children1 += s1.averageChildren;
            age1 += s1.averageDeadAge;
            writer1.write(s1.simulationDays + ", " + s1.noCurMonkes + ", " + s1.noBananas + ", " + s1.averageEnergy + ", " + s1.averageChildren + ", " + s1.averageDeadAge);
            writer1.newLine();
        }
        else {
            int days1 = s1.simulationDays;
            writer1.write(monkes1 / days1 + ", " + bananas1 / days1 + ", " + energy1 / days1 + ", " + children1 / days1 + ", " + age1 / days1);
        }
        writer1.flush();
        writer1.close();
    }
    public void writeStats2(MonkeSimulation s2, boolean endOfLife) throws IOException {
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(statsFile, true));
        if(!endOfLife) {
            monkes2 += s2.noCurMonkes;
            bananas2 += s2.noBananas;
            energy2 += s2.averageEnergy;
            children2 += s2.averageChildren;
            age2 += s2.averageDeadAge;
            writer2.write(s2.simulationDays + ", " + s2.noCurMonkes + ", " + s2.noBananas + ", " + s2.averageEnergy + ", " + s2.averageChildren + ", " + s2.averageDeadAge);
            writer2.newLine();
        }
        else {
            int days2 = s2.simulationDays;
            writer2.write(monkes2 / days2 + ", " + bananas2 / days2 + ", " + energy2 / days2 + ", " + children2 / days2 + ", " + age2 / days2);
        }
        writer2.flush();
        writer2.close();
    }
}
