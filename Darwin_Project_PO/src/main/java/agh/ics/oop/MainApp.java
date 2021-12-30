package agh.ics.oop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainApp extends Application {
    private final BorderPane border = new BorderPane();
    public MonkeSimulation simulation;
    public MonkeSimulation simulationFolded;
    public GridPane gridPane;
    public GridPane gridPaneFolded;
    public UpdateState stateMap;
    public UpdateState stateMapFolded;
    public WriteToFile file;
    public WriteToFile fileFolded;
    private final Button startButton = new Button("Start");
    private final Button exitButton = new Button("Exit");
    private Label noCurMonkesVal = new Label("0");
    public VBox mapBox = new VBox();
    public VBox foldedMapBox = new VBox();
    private MapChartRender chart = new MapChartRender("MAP CHART");
    private MapChartRender chartFolded = new MapChartRender("FOLDED MAP CHART");
    private boolean magic1 = false;
    private boolean magic2 = false;

    private VBox leftInfo = new VBox();
    private VBox leftGenotypeInfo = new VBox();
    private HBox leftFollowedInfo = new HBox();
    private VBox leftNoChildren = new VBox();
    private VBox leftDayOfDeath = new VBox();
    private Label genInfoLeft = new Label("This Monke Genotype is: ");
    private Label genLeft = new Label("0");
    private Label childrenInfoLeft = new Label("Number of This Monke Children is: ");
    private Label childrenLeft = new Label("0");
    private Label dayOfDeathInfoLeft = new Label("This Monke Death's Day is: ");
    public Label dayOfDeathLeft = new Label("0");

    private VBox rightInfo = new VBox();
    private VBox rightGenotypeInfo = new VBox();
    private HBox rightFollowedInfo = new HBox();
    private VBox rightNoChildren = new VBox();
    private VBox rightDayOfDeath = new VBox();
    private Label genInfoRight = new Label("This Monke Genotype is: ");
    private Label genRight = new Label("0");
    private Label childrenInfoRight = new Label("Number of This Monke Children is: ");
    private Label childrenRight = new Label("0");
    private Label dayOfDeathInfoRight = new Label("This Monke Death's Day is: ");
    public Label dayOfDeathRight = new Label("0");

    private Button showGenotypeLeft = new Button("Dom Genom");
    private Button showGenotypeRight = new Button("Dom Genom");

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("SIMULATION");
        primaryStage.setScene(new Scene(border, 1800, 1000));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            if(simulation != null && simulationFolded != null) {
                simulation.changeRun();
                simulationFolded.changeRun();
            }
            try {
                file.writeStats1(simulation, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileFolded.writeStats2(simulationFolded, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });
    }
    public void init() throws IOException {
        file = new WriteToFile("src/main/resources/borderMap.txt", false);
        fileFolded = new WriteToFile("src/main/resources/foldedMap.txt", true);

        HBox listOfFields = new HBox();
        TextField mapWidthField = new TextField("10");
        TextField mapHeightField = new TextField("10");
        TextField jungleWidthField = new TextField("4");
        TextField jungleHeightField = new TextField("4");
        TextField energyBananaField = new TextField("50");
        TextField noMonkesField = new TextField("10");
        TextField energyMonkeField = new TextField("100");
        TextField sleepInMSField = new TextField("100");

        mapWidthField.setMaxWidth(120);
        mapHeightField.setMaxWidth(120);
        jungleWidthField.setMaxWidth(120);
        jungleHeightField.setMaxWidth(120);
        energyBananaField.setMaxWidth(120);
        noMonkesField.setMaxWidth(120);
        energyMonkeField.setMaxWidth(120);

        Button create = new Button("CREATE");
        create.setStyle("-fx-background-color: #d79097; ");
        CheckBox leftCheckMagic = new CheckBox("Magic for Left Map?");
        CheckBox rightCheckMagic = new CheckBox("Magic for Right Map?");

        listOfFields.getChildren().addAll(mapWidthField, mapHeightField, jungleWidthField, jungleHeightField, energyBananaField, noMonkesField, energyMonkeField, sleepInMSField, create, leftCheckMagic, rightCheckMagic);
        listOfFields.setSpacing(10);

        Label mapWidthLabel = new Label("Enter Map Width");
        Label mapHeightLabel = new Label("Enter Map Height");
        Label jungleWidthLabel = new Label("Enter Jungle Width");
        Label jungleHeightLabel = new Label("Enter Jungle Height");
        Label energyBananaLabel = new Label("Enter Banana Energy");
        Label noMonkesLabel = new Label("Enter nO Monkes");
        Label energyMonkeLabel = new Label("Enter Monke Energy");
        Label sleepInMSLabel = new Label("Enter Sleep in Miliseconds");

        HBox listOfLabels = new HBox();
        listOfLabels.getChildren().addAll(mapWidthLabel, mapHeightLabel, jungleWidthLabel, jungleHeightLabel, energyBananaLabel, noMonkesLabel, energyMonkeLabel, sleepInMSLabel);
        listOfLabels.setSpacing(35);

        VBox inputs = new VBox();
        inputs.getChildren().addAll(listOfLabels, listOfFields);
        inputs.setAlignment(Pos.TOP_CENTER);

        border.setTop(inputs);
        border.setStyle("-fx-background-color: #fdf5e6");

//        LEFT MAP GUI
        HBox leftButtons = new HBox();
        Button stopButtonLeft = new Button("STOP / START");
        stopButtonLeft.setStyle("-fx-background-color: #d79097; ");
        Button stopFollowLeft = new Button("Stop Follow");
        leftButtons.getChildren().addAll(stopButtonLeft, showGenotypeLeft, stopFollowLeft);
        leftGenotypeInfo.getChildren().addAll(genInfoLeft, genLeft);
        leftNoChildren.getChildren().addAll(childrenInfoLeft, childrenLeft);
        leftDayOfDeath.getChildren().addAll(dayOfDeathInfoLeft, dayOfDeathLeft);
        leftFollowedInfo.getChildren().addAll(leftNoChildren, leftDayOfDeath);
        leftInfo.getChildren().addAll(leftGenotypeInfo, leftFollowedInfo);
        mapBox.getChildren().addAll(leftButtons, leftInfo);
        mapBox.setPadding(new Insets(15, 15, 15, 15));
        leftInfo.setSpacing(25);
        mapBox.setSpacing(50);

//        RIGHT MAP GUI
        HBox rightButtons = new HBox();
        Button stopButtonRight = new Button("STOP / START");
        stopButtonRight.setStyle("-fx-background-color: #d79097; ");
        Button stopFollowRight = new Button("Stop Follow");
        rightButtons.getChildren().addAll(stopButtonRight, showGenotypeRight, stopFollowRight);
        rightGenotypeInfo.getChildren().addAll(genInfoRight, genRight);
        rightNoChildren.getChildren().addAll(childrenInfoRight, childrenRight);
        rightDayOfDeath.getChildren().addAll(dayOfDeathInfoRight, dayOfDeathRight);
        rightFollowedInfo.getChildren().addAll(rightNoChildren, rightDayOfDeath);
        rightInfo.getChildren().addAll(rightGenotypeInfo, rightFollowedInfo);
        foldedMapBox.getChildren().addAll(rightButtons, rightInfo);
        foldedMapBox.setPadding(new Insets(15, 15, 15, 15));
        rightInfo.setSpacing(25);
        foldedMapBox.setSpacing(50);

        HBox legend = new HBox();
        Label white = new Label("White Monke -> is followed");
        Label purple = new Label("Purple Monke -> dominant genotyp");
        Label red = new Label("Red Monke -> energy 0 - 20%");
        Label orange = new Label("Orange Monke -> energy 21 - 40&");
        Label yellow = new Label("Yellow Monke -> energy 41 - 60%");
        Label lightblue = new Label("LightBlue Monke -> energy 61 - 80%");
        Label blue = new Label("Blue Monke -> energy 81 - 100%");
        legend.getChildren().addAll(white, purple, red, orange, yellow, lightblue, blue);
        legend.setSpacing(30);
        border.setBottom(legend);

        create.setOnAction(actionEvent -> {
            int mapWidth = Integer.parseInt(mapWidthField.getText());
            int mapHeight = Integer.parseInt(mapHeightField.getText());
            int jungleWidth = Integer.parseInt(jungleWidthField.getText());
            int jungleHeight = Integer.parseInt(jungleHeightField.getText());
            int energyBanana = Integer.parseInt(energyBananaField.getText());
            int noMonkes = Integer.parseInt(noMonkesField.getText());
            int energyMonke = Integer.parseInt(energyMonkeField.getText());
            int ms = Integer.parseInt(sleepInMSField.getText());
            if(leftCheckMagic.isSelected()) { magic1 = true; }
            if(rightCheckMagic.isSelected()) { magic2 = true; }
            simulation = new MonkeSimulation(this, mapWidth, mapHeight, jungleWidth, jungleHeight, energyMonke, energyBanana, ms, 1000000, noMonkes, false, file, magic1);
            simulationFolded = new MonkeSimulation(this, mapWidth, mapHeight, jungleWidth, jungleHeight, energyMonke, energyBanana, ms, 1000000, noMonkes, true, fileFolded, magic2);
            chart = new MapChartRender("MAP CHART");
            chartFolded = new MapChartRender("FOLDED MAP CHART");
            try {
                startApp();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        stopButtonLeft.setOnAction(actionEvent -> simulation.changeRun());
        stopButtonRight.setOnAction(actionEvent -> simulationFolded.changeRun());
        stopFollowLeft.setOnAction(actionEvent -> {
            simulation.stopFollowing();
            simulationFolded.stopFollowing();
        });
    }

    public void startApp() throws FileNotFoundException {
        Thread simulationThread = new Thread(simulation);
        Thread simulationFoldedThread = new Thread(simulationFolded);
        showGenotypeLeft.setOnAction(actionEvent -> stateMap.showMonkesDominant(simulation.map));
        showGenotypeRight.setOnAction(actionEvent -> stateMapFolded.showMonkesDominant(simulationFolded.map));
        simulation.changeRun();
        simulationFolded.changeRun();
        if(simulation.run && stateMap != null && stateMapFolded != null) {
            mapBox.getChildren().remove(stateMap.gridPane);
            foldedMapBox.getChildren().remove(stateMapFolded.gridPane);
        }
        simulationThread.start();
        simulationFoldedThread.start();
        stateMap = new UpdateState(simulation, this);
        stateMapFolded = new UpdateState(simulationFolded, this);
        mapBox.getChildren().add(stateMap.gridPane);
        foldedMapBox.getChildren().add(stateMapFolded.gridPane);
        VBox right = new VBox(chart.getLineChart(), chartFolded.getLineChart());
        border.setRight(right);
        border.setLeft(mapBox);
        border.setCenter(foldedMapBox);
        chart.updateChart(simulation);
        chartFolded.updateChart(simulationFolded);

    }
    public void updateMap() {
        Platform.runLater(() -> {
            stateMap.update(simulation.map);
            stateMapFolded.update(simulationFolded.map);
            gridPane = stateMap.gridPane;
            gridPaneFolded = stateMapFolded.gridPane;
            chart.updateChart(simulation);
            chartFolded.updateChart(simulationFolded);
        });
    }
    public void showMonkeGenotype(MonkeGen monkeGen, boolean folded) {
        Platform.runLater(() -> {
            if(!folded) {
                genLeft = new Label(monkeGen.toString());
                leftGenotypeInfo.getChildren().setAll(genInfoLeft, genLeft);
            }
            else {
                genRight = new Label(monkeGen.toString());
                rightGenotypeInfo.getChildren().setAll(genInfoRight, genRight);
            }
        });
    }
    public void showFollowedMonke(MonkeSimulation simulation) {
        if(!simulation.map.folded) {
            childrenLeft = new Label(Integer.toString(simulation.childrenOfFollowed));
            leftNoChildren.getChildren().setAll(childrenInfoLeft, childrenLeft);
            leftDayOfDeath.getChildren().setAll(dayOfDeathInfoLeft, dayOfDeathLeft);
            leftFollowedInfo.getChildren().setAll(leftNoChildren, leftDayOfDeath);
        }
        else {
            childrenRight = new Label(Integer.toString(simulation.childrenOfFollowed));
            rightNoChildren.getChildren().setAll(childrenInfoRight, childrenRight);
            rightDayOfDeath.getChildren().setAll(dayOfDeathInfoRight, dayOfDeathRight);
            rightFollowedInfo.getChildren().setAll(rightNoChildren, rightDayOfDeath);
        }
    }
}
