package org.Denyx.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.util.Pair;
import org.example.Figure;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static void save(List<Figure> figures, List<double[][]> figuresCoords, File file) {
        Gson gson = new Gson();

        String figJson = "";
        String coordsJson = "";
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < figures.size(); i++) {
            figJson = gson.toJson(figures.get(i));
            coordsJson = gson.toJson(figuresCoords.get(i));
            try {
                writer.write(figures.get(i).getFigureType() + "\n");
                writer.write(figJson + "\n");
                writer.write(coordsJson + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAs() {}

    public static Pair<List<Figure>, List<double[][]>> open(File file,
                                             Map<Integer, Class> mapTypes,
                                             GraphicsContext graphicsContextDraw) throws IOException {
        List<Figure> figuresFromFile = new ArrayList<>();
        List<double[][]> coordsFromFile = new ArrayList<>();
        int figureType = 0;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        Figure figure;
        double[][] coords;
        int k = 0;
        String line = reader.readLine();
        while (line != null){
            if (k % 3 == 0){
                figureType = Character.getNumericValue(line.charAt(0));
            } else if (k % 3 == 1) {
                figure = (Figure) gson.fromJson(line, mapTypes.get(figureType));
                figuresFromFile.add(figure);
            } else {
                coords = gson.fromJson(line, double[][].class);
                coordsFromFile.add(coords);
            }
            line = reader.readLine();
            k++;
        }
        if (figuresFromFile.size() > 0) {
            clear(graphicsContextDraw);
            for (int i = 0; i < figuresFromFile.size(); i++) {
                figuresFromFile.get(i).draw(doubleMatrixToListDoubleArray(coordsFromFile.get(i)), graphicsContextDraw);
            }
        }
        return new Pair<>(figuresFromFile, coordsFromFile);
    }

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
