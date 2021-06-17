package org.Denyx.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.Denyx.actions.Actions;
import org.example.Figure;

import java.util.List;

public class PolylineFigure extends Figure {
    private Boolean isFigurePolygonal = true;
    private int figureType = 4;

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
        gc.strokeLine(startCoords[0], startCoords[1], endCoords[0], endCoords[1]);
    }

    @Override
    public void draw(List<double[]> figureCoords, GraphicsContext gc) {
        if (figureCoords.size() > 1) {
            gc.setStroke(Color.web(getStrokeFigureColor()));
            gc.setLineWidth(getFigureLineWidth());
            for (int i = figureCoords.size() - 1; i > 0; i--) {
                double[] startCoords = figureCoords.get(i - 1);
                double[] endCoords = figureCoords.get(i);
                gc.strokeLine(startCoords[0], startCoords[1], endCoords[0], endCoords[1]);
            }
        }
    }
}
