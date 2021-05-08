package org.Denyx.figures;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class PolygonFigure extends Figure {
    private Boolean isFigurePolygonal = true;
    List<Double> xCoordsList = new ArrayList<>();
    List<Double> yCoordsList = new ArrayList<>();

    @Override
    public Boolean isFigurePolygonal() {
        return this.isFigurePolygonal;
    }

    @Override
    public void preview(double[] startCoords, double[] endCoords, GraphicsContext gc) {

    }

    @Override
    public void draw(List<double[]> figureCoords, GraphicsContext gc) {
        gc.setStroke(getStrokeFigureColor());
        gc.setFill(getStrokeFigureColor());
        gc.setLineWidth(getFigureLineWidth());
        for (int j = 0; j < figureCoords.size(); j++) {
            xCoordsList.add(figureCoords.get(j)[0]);
            yCoordsList.add(figureCoords.get(j)[1]);
        }
        double[] xCoords = listToArray(xCoordsList);
        double[] yCoords = listToArray(yCoordsList);
        if (xCoords.length < 3) {
            gc.fillOval(xCoords[xCoords.length - 1], yCoords[yCoords.length - 1], 3, 3);
        } else {
            gc.fillPolygon(xCoords, yCoords, xCoords.length);
        }
        xCoordsList.clear();
        yCoordsList.clear();
    }

    private double[] listToArray(List<Double> coords) {
        double[] array = new double[coords.size()];
        for (int j = 0; j < coords.size(); j++) {
            array[j] = coords.get(j);
        }
        return array;
    }
}
