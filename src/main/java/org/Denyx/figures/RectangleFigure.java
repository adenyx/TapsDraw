package org.Denyx.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.Denyx.actions.Actions;

import java.util.List;

public class RectangleFigure extends Figure {
    private Boolean isFigurePolygonal = false;
    private int figureType = 3;

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
        double startX = Math.min(startCoords[0], endCoords[0]);
        double startY = Math.min(startCoords[1], endCoords[1]);
        double endX = (endCoords[0] - startCoords[0] > 0) ? endCoords[0] - startCoords[0] : startCoords[0] - endCoords[0];
        double endY = (endCoords[1] - startCoords[1] > 0) ? endCoords[1] - startCoords[1] : startCoords[1] - endCoords[1];
        gc.strokeRect(startX, startY, endX, endY);
        if (isNeedToFillFigure()) {
            gc.setFill(Color.web(getFillFigureColor()));
            gc.fillRect(startX, startY, endX, endY);
        }
    }

    @Override
    public void draw(List<double[]> figureCoords, GraphicsContext gc) {
        gc.setStroke(Color.web(getStrokeFigureColor()));
        gc.setLineWidth(getFigureLineWidth());
        double[] startCoords = figureCoords.get(0);
        double[] endCoords = figureCoords.get(1);
        double startX = Math.min(startCoords[0], endCoords[0]);
        double startY = Math.min(startCoords[1], endCoords[1]);
        double endX = (endCoords[0] - startCoords[0] > 0) ? endCoords[0] - startCoords[0] : startCoords[0] - endCoords[0];
        double endY = (endCoords[1] - startCoords[1] > 0) ? endCoords[1] - startCoords[1] : startCoords[1] - endCoords[1];
        gc.strokeRect(startX, startY, endX, endY);
        if (isNeedToFillFigure()) {
            gc.setFill(Color.web(getFillFigureColor()));
            gc.fillRect(startX, startY, endX, endY);
        }
    }
}
