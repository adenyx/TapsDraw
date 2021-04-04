package org.Denyx.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class Figure {

    private String figureType; // for future updates
    private Color lineColor;
    private Color fillColor;
    private int lineWidth;
    private boolean isNeedToFillFigure;

    public String getFigureType() {
        return this.figureType;
    }

    public void setFigureType(String type) {
        this.figureType = type;
    }

    public Color getStrokeFigureColor() {
        return this.lineColor;
    }

    public Color getFillFigureColor() {
        return this.fillColor;
    }

    public void setFigureColor(Color strokeColor, Color fillColor) {
        this.lineColor = strokeColor;
        this.fillColor = fillColor;
    }

    public int getFigureLineWidth() {
        return this.lineWidth;
    }

    public void setFigureLineWidth(int width) {
        this.lineWidth = width;
    }

    public void setNeedToFillFigure(boolean isNeedToFillFigure) {
        this.isNeedToFillFigure = isNeedToFillFigure;
    }

    public boolean isNeedToFillFigure() {
        return this.isNeedToFillFigure;
    }

    public abstract void draw(List<double[]> figureCoords, GraphicsContext gc);

    public abstract void preview(double[] startCoords, double[] endCoords, GraphicsContext gc);

}
