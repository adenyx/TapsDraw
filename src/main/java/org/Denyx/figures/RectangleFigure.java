package org.Denyx.figures;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class RectangleFigure extends Figure {

    @Override
    public void preview(double[] startCoords, double[] endCoords, GraphicsContext gc) {
        gc.clearRect(0, 0, 600, 286);
        gc.setStroke(getStrokeFigureColor());
        gc.setLineWidth(getFigureLineWidth());
        double startX = Math.min(startCoords[0], endCoords[0]);
        double startY = Math.min(startCoords[1], endCoords[1]);
        double endX = (endCoords[0] - startCoords[0] > 0) ? endCoords[0] - startCoords[0] : startCoords[0] - endCoords[0];
        double endY = (endCoords[1] - startCoords[1] > 0) ? endCoords[1] - startCoords[1] : startCoords[1] - endCoords[1];
        gc.strokeRect(startX, startY, endX, endY);
        if (isNeedToFillFigure()) {
            gc.setFill(getFillFigureColor());
            gc.fillRect(startX, startY, endX, endY);
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
        gc.strokeRect(startX, startY, endX, endY);
        if (isNeedToFillFigure()) {
            gc.setFill(getFillFigureColor());
            gc.fillRect(startX, startY, endX, endY);
        }
    }
}
