package org.Denyx.figures;

import javafx.scene.canvas.GraphicsContext;
import org.Denyx.actions.Actions;

import java.util.List;

public class LineFigure extends Figure {
    private String figureType = "line";

    @Override
    public String getFigureType() {
        return figureType;
    }

    @Override
    public void preview (double[] startCoords, double[] endCoords, GraphicsContext gc) {
        Actions.clear(gc);
        gc.setStroke(getStrokeFigureColor());
        gc.setLineWidth(getFigureLineWidth());
        gc.strokeLine(startCoords[0], startCoords[1], endCoords[0], endCoords[1]);
    }

    @Override
    public void draw (List<double[]> figureCoords, GraphicsContext gc) {
        gc.setStroke(getStrokeFigureColor());
        gc.setLineWidth(getFigureLineWidth());
        double[] startCoords = figureCoords.get(0);
        double[] endCoords = figureCoords.get(1);
        gc.strokeLine(startCoords[0], startCoords[1], endCoords[0], endCoords[1]);
    }
}
