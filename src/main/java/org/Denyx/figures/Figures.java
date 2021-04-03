package org.Denyx.figures;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

// TODO split all figures on separate classes
public class Figures {
    public void addLine(int startX, int startY, int endX, int endY, Paint color, Group group) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(color);
        group.getChildren().add(line);
    }

    public void addRectangle(int startX, int startY, int width, int height, Paint strokeColor, Paint fillColor, Group group) {
        Rectangle rectangle = new Rectangle(startX, startY, width, height);
        rectangle.setStroke(strokeColor);
        rectangle.setFill(fillColor);
        group.getChildren().add(rectangle);
    }

    public void addCircle(int centerX, int centerY, int radius, Paint color, Group group) {
        Circle circle = new Circle();
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
        circle.setRadius(radius);
        circle.setFill(color);
        group.getChildren().add(circle);
    }

    public void addPolygon(Paint color, Group group) { // TODO add editable parameters
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(200.0, 50.0,
                300.0, 50.0,
                350.0, 100.0,
                300.0, 250.0,
                200.0, 250.0,
                150.0, 100.0
        );
        polygon.setFill(color);
        group.getChildren().add(polygon);
    }

    public void addPolyline(Paint color, Group group) {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(
                100.0, 200.0,
                150.0, 250.0,
                100.0, 280.0,
                50.0, 280.0
        );
        polyline.setStroke(color);
        group.getChildren().add(polyline);
    }
}
