package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class Figure {

    private String lineColor;
    private String fillColor;
    private int lineWidth;
    private boolean isNeedToFillFigure;

    public String getStrokeFigureColor() {
        return this.lineColor;
    }

    public String getFillFigureColor() {
        return this.fillColor;
    }

    public void setFigureColor(Color strokeColor, Color fillColor) {
        this.lineColor = toHEX(strokeColor);
        this.fillColor = toHEX(fillColor);
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

    public abstract Boolean isFigurePolygonal();

    public abstract int getFigureType();

    public abstract void draw(List<double[]> figureCoords, GraphicsContext gc);

    public abstract void preview(double[] startCoords, double[] endCoords, GraphicsContext gc);

    private String toHEX(Color color){
        return String.format("#%02X%02X%02X", (int)(color.getRed() * 255.0D), (int)(color.getGreen() * 255.0D), (int)(color.getBlue() * 255.0D));
    }

}
