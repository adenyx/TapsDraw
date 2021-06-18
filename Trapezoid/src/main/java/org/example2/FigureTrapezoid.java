package org.example2;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TrapezoidFigure extends Figure {
    private Boolean isFigurePolygonal = false;
    private int figureType = 6;

    @Override
    public Boolean isFigurePolygonal() {
        return this.isFigurePolygonal;
    }

    @Override
    public int getFigureType() {
        return this.figureType;
    }

    @Override
    public void preview(double[] startCoords, double[] endCoords, GraphicsContext gc) {
        Actions.clear(gc);
        gc.setStroke(Color.web(getStrokeFigureColor()));
        gc.setLineWidth(getFigureLineWidth());
        double[] xs = new double[] {startCoords[0], startCoords[0] + (endCoords[0] - startCoords[0]) / 3, startCoords[0] + (endCoords[0] - startCoords[0]) / 3 * 2, endCoords[0]};
        double[] ys = new double[] {endCoords[1], startCoords[1], startCoords[1], endCoords[1]};
        gc.strokePolygon(xs, ys, 4);
    }

    @Override
    public void draw(List<double[]> figureCoords, GraphicsContext gc) {
        gc.setStroke(Color.web(getStrokeFigureColor()));
        gc.setLineWidth(getFigureLineWidth());
        double[] startCoords = figureCoords.get(0);
        double[] endCoords = figureCoords.get(1);
        double[] xs = new double[] {startCoords[0], startCoords[0] + (endCoords[0] - startCoords[0]) / 3, startCoords[0] + (endCoords[0] - startCoords[0]) / 3 * 2, endCoords[0]};
        double[] ys = new double[] {endCoords[1], startCoords[1], startCoords[1], endCoords[1]};
        gc.strokePolygon(xs, ys, 4);
    }

}

