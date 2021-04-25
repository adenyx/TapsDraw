package org.Denyx.actions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import org.Denyx.figures.Figure;

import java.util.List;

public class Actions {
    public static void redo() {}

    public static void undo(List<Figure> figuresHistory,
                            List<Figure> deletedFigures,
                            List<List<double[]>> figuresCoordsHistory,
                            List<List<double[]>> deletedFiguresCoords,
                            GraphicsContext graphicsContextDraw){
        if (figuresHistory.size() > 0) {
            clear(graphicsContextDraw);
            for (int i = 0; i < figuresHistory.size() - 1; i++) {
                System.out.println(figuresCoordsHistory);
                figuresHistory.get(i).draw(figuresCoordsHistory.get(i), graphicsContextDraw);
            }
            deletedFigures.add(figuresHistory.get(figuresHistory.size() - 1));
            figuresHistory.remove(figuresHistory.get(figuresHistory.size() - 1));
            deletedFiguresCoords.add(figuresCoordsHistory.get(figuresCoordsHistory.size() - 1));
            figuresCoordsHistory.remove(figuresCoordsHistory.get(figuresCoordsHistory.size() - 1));
        }
    }

    public static void clear(GraphicsContext gc) {
        gc.clearRect(0, 0, 600, 286);
    }

    public static void save() {}

    public static void saveAs() {}

    public static void open() {}

    public static void showAlertWindow(String type, String title, String content) {
        Alert alert;
        switch (type) {
            case "error" :
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            default:
                alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
