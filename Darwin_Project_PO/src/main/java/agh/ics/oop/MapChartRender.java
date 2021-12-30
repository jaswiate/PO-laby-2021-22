package agh.ics.oop;
import javafx.scene.chart.*;

public class MapChartRender {
    private final LineChart lineChart;
    private final XYChart.Series mapSeriesMonke;
    private final XYChart.Series mapSeriesBanana;
    public MapChartRender(String name) {
        NumberAxis axisX = new NumberAxis();
        NumberAxis axisY = new NumberAxis();
        axisX.setLabel("Current Day");
        this.lineChart = new LineChart(axisX, axisY);
        mapSeriesMonke = new XYChart.Series();
        mapSeriesBanana = new XYChart.Series();

        lineChart.setTitle(name);
        mapSeriesMonke.setName("Number of Monkes");
        mapSeriesBanana.setName("Number of Bananas");
        lineChart.setCreateSymbols(false);
        lineChart.lookup(".chart-legend").setStyle("-fx-background-color: transparent;");
        lineChart.getData().addAll(mapSeriesMonke, mapSeriesBanana);

    }
    public LineChart getLineChart() {
        return this.lineChart;
    }
    public void updateChart(MonkeSimulation simulation) {
        mapSeriesMonke.getData().add(new XYChart.Data(simulation.simulationDays, simulation.noCurMonkes));
        mapSeriesBanana.getData().add(new XYChart.Data(simulation.simulationDays, simulation.noBananas));
    }

}
