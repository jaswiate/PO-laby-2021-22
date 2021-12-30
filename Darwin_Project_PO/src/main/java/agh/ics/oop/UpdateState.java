package agh.ics.oop;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class UpdateState {
    private final MonkeSimulation simulation;
    public final GridPane gridPane;
    private final MainApp app;

    private final Image monkeStepImage100 = new Image(new FileInputStream("src/main/resources/monkestep100.png"));
    private final Image monkeStepImage80 = new Image(new FileInputStream("src/main/resources/monkestep80.png"));
    private final Image monkeStepImage60 = new Image(new FileInputStream("src/main/resources/monkestep60.png"));
    private final Image monkeStepImage40 = new Image(new FileInputStream("src/main/resources/monkestep40.png"));
    private final Image monkeStepImage20 = new Image(new FileInputStream("src/main/resources/monkestep20.png"));
    private final Image monkeStepImageDom = new Image(new FileInputStream("src/main/resources/monkestepdom.png"));
    private final Image monkeStepImageFollow = new Image(new FileInputStream("src/main/resources/monkestepfollow.png"));
    private final Image bananaStepImage = new Image(new FileInputStream("src/main/resources/bananastep.png"));
    private final Image stepImage = new Image(new FileInputStream("src/main/resources/step.png"));

    private final Image monkeJungleImage100 = new Image(new FileInputStream("src/main/resources/monkejungle100.png"));
    private final Image monkeJungleImage80 = new Image(new FileInputStream("src/main/resources/monkejungle80.png"));
    private final Image monkeJungleImage60 = new Image(new FileInputStream("src/main/resources/monkejungle60.png"));
    private final Image monkeJungleImage40 = new Image(new FileInputStream("src/main/resources/monkejungle40.png"));
    private final Image monkeJungleImage20 = new Image(new FileInputStream("src/main/resources/monkejungle20.png"));
    private final Image monkeJungleImageDom = new Image(new FileInputStream("src/main/resources/monkejungledom.png"));
    private final Image monkeJungleImageFollow = new Image(new FileInputStream("src/main/resources/monkejunglefollow.png"));
    private final Image bananaJungleImage = new Image(new FileInputStream("src/main/resources/bananajungle.png"));
    private final Image jungleImage = new Image(new FileInputStream("src/main/resources/jungle.png"));

    private ImageView imageView;

    public UpdateState(MonkeSimulation simulation, MainApp app) throws FileNotFoundException {
        this.app = app;
        this.simulation = simulation;
        this.gridPane = new GridPane();

        for(int i = 0; i < simulation.map.getMapWidth(); i++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(1200 / (2 * simulation.map.getMapWidth())));
            StackPane pane = new StackPane();
        }
        for(int i = 0; i < simulation.map.getMapHeight(); i++) {
            this.gridPane.getRowConstraints().add(new RowConstraints(1200 / (2 * simulation.map.getMapHeight())));
        }
        update(simulation.map);
    }
    public void update(MonkeMap map) {
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);

        for(int i = 0; i < map.getMapWidth(); i++) {
            for(int j = 0; j < map.getMapHeight(); j++) {
                Vector2d pos = new Vector2d(i, j);
                StackPane pane = new StackPane();
                if(map.isInJungle(pos)) {
                    if(map.getObjectAt(pos) == null) {
                        imageView = new ImageView(jungleImage);
                    }
                    else if(map.getObjectAt(pos) instanceof Banana) {
                        imageView = new ImageView(bananaJungleImage);
                    }
                    else {
                        int monkeEnergy = (int) (((float) map.getMonkesEnergy(pos) / (float) simulation.maxEnergy) * 100);
                        if(monkeEnergy >= 0 && monkeEnergy <= 20) imageView = new ImageView(monkeJungleImage20);
                        else if(monkeEnergy > 20 && monkeEnergy <= 40) imageView = new ImageView(monkeJungleImage40);
                        else if(monkeEnergy > 40 && monkeEnergy <= 60) imageView = new ImageView(monkeJungleImage60);
                        else if(monkeEnergy > 60 && monkeEnergy <= 80) imageView = new ImageView(monkeJungleImage80);
                        else imageView = new ImageView(monkeJungleImage100);
                        pane.setOnMouseClicked(event -> {
                            if(!simulation.run)
                                app.showMonkeGenotype(map.getMonkeAt(pos).monkeGenotype, map.folded);
                                simulation.followMonke(map.getMonkeAt(pos));
                        });
                        if(map.getMonkeAt(pos).checkFollow()) {
                            imageView = new ImageView(monkeJungleImageFollow);
                            app.showFollowedMonke(simulation);
                        }
                    }
                }
                else {
                    if(map.getObjectAt(pos) == null) {
                        imageView = new ImageView(stepImage);
                    }
                    else if(map.getObjectAt(pos) instanceof Banana) {
                        imageView = new ImageView(bananaStepImage);
                    }
                    else {
                        int monkeEnergy = (int) (((float) map.getMonkesEnergy(pos) / (float) simulation.maxEnergy) * 100);
                        if(monkeEnergy >= 0 && monkeEnergy <= 20) imageView = new ImageView(monkeStepImage20);
                        else if(monkeEnergy > 20 && monkeEnergy <= 40) imageView = new ImageView(monkeStepImage40);
                        else if(monkeEnergy > 40 && monkeEnergy <= 60) imageView = new ImageView(monkeStepImage60);
                        else if(monkeEnergy > 60 && monkeEnergy <= 80) imageView = new ImageView(monkeStepImage80);
                        else imageView = new ImageView(monkeStepImage100);
                        pane.setOnMouseClicked(event -> {
                            if(!simulation.run)
                                app.showMonkeGenotype(map.getMonkeAt(pos).monkeGenotype, map.folded);
                                simulation.followMonke(map.getMonkeAt(pos));
                        });
                        if(map.getMonkeAt(pos).checkFollow()) {
                            imageView = new ImageView(monkeStepImageFollow);
                            if(map.folded)
                                app.dayOfDeathLeft = new Label(Integer.toString(simulation.dayOfDeath));
                            else app.dayOfDeathRight = new Label(Integer.toString(simulation.dayOfDeath));
                            app.showFollowedMonke(simulation);
                        }
                    }
                }

                imageView.setFitWidth(1200 / (2 * simulation.map.getMapWidth()));
                imageView.setFitHeight(1200 / (2 * simulation.map.getMapHeight()));
                pane.getChildren().add(imageView);
                gridPane.add(pane, i, j);
            }
        }
    }
    public void showMonkesDominant(MonkeMap map) {
        boolean show;
        for(int i = 0; i < map.getMapWidth(); i++) {
            for (int j = 0; j < map.getMapHeight(); j++) {
                Vector2d pos = new Vector2d(i, j);
                StackPane pane = new StackPane();
                show = false;
                if (map.getObjectAt(pos) instanceof Monke) {
                    if (map.getMonkeAt(pos).monkeGenotype == simulation.dominantGenotype) {
                        show = true;
                        if (map.isInJungle(pos)) {
                            imageView = new ImageView(monkeJungleImageDom);
                        } else {
                            imageView = new ImageView(monkeStepImageDom);
                        }
                    }
                }
                if(show) {
                    imageView.setFitWidth(1200 / (2 * simulation.map.getMapWidth()));
                    imageView.setFitHeight(1200 / (2 * simulation.map.getMapHeight()));
                    pane.getChildren().add(imageView);
                    gridPane.add(pane, i, j);
                }
            }
        }
    }

}
