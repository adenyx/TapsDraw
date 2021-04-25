package org.Denyx.figures;

import javafx.scene.canvas.GraphicsContext;
import org.Denyx.actions.Actions;

import java.util.List;

public class CircleFigure extends Figure {
    private String figureType = "circle";

    @Override
    public String getFigureType() {
        return figureType;
    }

    @Override
    public void preview(double[] startCoords, double[] endCoords, GraphicsContext gc) {
        Actions.clear(gc);
        gc.setStroke(getStrokeFigureColor());
        gc.setLineWidth(getFigureLineWidth());
        double startX = Math.min(startCoords[0], endCoords[0]);
        double startY = Math.min(startCoords[1], endCoords[1]);
        double endX = (endCoords[0] - startCoords[0] > 0) ? endCoords[0] - startCoords[0] : startCoords[0] - endCoords[0];
        double endY = (endCoords[1] - startCoords[1] > 0) ? endCoords[1] - startCoords[1] : startCoords[1] - endCoords[1];
        gc.strokeOval(startX, startY, endX, endY);
        if (isNeedToFillFigure()) {
            gc.setFill(getFillFigureColor());
            gc.fillOval(startX, startY, endX, endY);
        }
    }

    @Override
    public void draw(List<double[]> figureCoords, GraphicsContext gc) {
        gc.setStroke(getStrokeFigureColor());
        gc.setLineWidth(getFigureLineWidth());
        double[] startCoords = figureCoords.get(0);
        double[] endCoords = figureCoords.get(1);
        double startX = Math.min(startCoords[0], endCoords[0]);
        double startY = Math.min(startCoords[1], endCoords[1]);
        double endX = (endCoords[0] - startCoords[0] > 0) ? endCoords[0] - startCoords[0] : startCoords[0] - endCoords[0];
        double endY = (endCoords[1] - startCoords[1] > 0) ? endCoords[1] - startCoords[1] : startCoords[1] - endCoords[1];
        gc.strokeOval(startX, startY, endX, endY);
        if (isNeedToFillFigure()) {
            gc.setFill(getFillFigureColor());
            gc.fillOval(startX, startY, endX, endY);
        }
    }
}
