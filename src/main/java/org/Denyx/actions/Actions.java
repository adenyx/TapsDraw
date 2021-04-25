package org.Denyx.actions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import org.Denyx.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Actions {
    public static void redo(List<Figure> figuresHistory,
                            List<Figure> deletedFigures,
                            List<double[][]> figuresCoordsHistory,
                            List<double[][]> deletedFiguresCoords,
                            GraphicsContext graphicsContextDraw){
        if (deletedFigures.size() > 0) {
            deletedFigures.get(deletedFigures.size() - 1)
                    .draw(doubleMatrixToListDoubleArray(deletedFiguresCoords.get(deletedFigures.size() - 1)),
                    graphicsContextDraw);

            figuresHistory.add(deletedFigures.get(deletedFigures.size() - 1));
            deletedFigures.remove(deletedFigures.size() - 1);
            figuresCoordsHistory.add(deletedFiguresCoords.get(deletedFiguresCoords.size() - 1));
            deletedFiguresCoords.remove(deletedFiguresCoords.get(deletedFiguresCoords.size() - 1));
        } else {
            showAlertWindow("error", "Action Error", "There are no figures to redo");
        }
    }

    public static void undo(List<Figure> figuresHistory,
                            List<Figure> deletedFigures,
                            List<double[][]> figuresCoordsHistory,
                            List<double[][]> deletedFiguresCoords,
                            GraphicsContext graphicsContextDraw){
        if (figuresHistory.size() > 0) {
            clear(graphicsContextDraw);
            for (int i = 0; i < figuresHistory.size() - 1; i++) {
                figuresHistory.get(i).draw(doubleMatrixToListDoubleArray(figuresCoordsHistory.get(i)), graphicsContextDraw);
            }
            deletedFigures.add(figuresHistory.get(figuresHistory.size() - 1));
            figuresHistory.remove(figuresHistory.get(figuresHistory.size() - 1));
            deletedFiguresCoords.add(figuresCoordsHistory.get(figuresCoordsHistory.size() - 1));
            figuresCoordsHistory.remove(figuresCoordsHistory.get(figuresCoordsHistory.size() - 1));
        } else {
            showAlertWindow("error", "Action Error", "There are no figures to undo");
        }
    }

    private static List<double[]> doubleMatrixToListDoubleArray(double[][] matrix) {
        List<double[]> doubleArray = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            doubleArray.add(new double[] {matrix[i][0], matrix[i][1]});
        }
        return doubleArray;
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
