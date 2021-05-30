package org.Denyx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.Denyx.actions.Actions;
import org.Denyx.figures.*;

import static java.lang.Double.*;

public class PrimaryController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem openButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem saveAsButton;

    @FXML
    private MenuItem redoButton;

    @FXML
    private MenuItem undoButton;

    @FXML
    private MenuItem clearButton;

    @FXML
    private MenuItem aboutButton;

    @FXML
    private Canvas drawZone;

    @FXML
    private Canvas previewZone;

    @FXML
    private Pane lineBackground;

    @FXML
    private ImageView lineFigure;

    @FXML
    private Pane rectangleBackground;

    @FXML
    private ImageView rectangleFigure;

    @FXML
    private Pane circleBackground;

    @FXML
    private ImageView circleFigure;

    @FXML
    private Pane polygonBackground;

    @FXML
    private ImageView polygonFigure;

    @FXML
    private Pane polylineBackground;

    @FXML
    private ImageView polylineFigure;

    @FXML
    private Pane ellipsesBackground;

    @FXML
    private ImageView ellipsisButton;

    @FXML
    private ColorPicker strokeColorPicker;

    @FXML
    private TextField chooseWidthField;

    @FXML
    private CheckBox fillCheckbox;

    @FXML
    private ColorPicker fillColorPicker;

    private Figure currentFigure;
    private double[] previewStartCoords = {NaN, NaN};
    private List<double[]> figureCoords = new ArrayList<>();
    private List<double[][]> figuresCoordsHistory = new ArrayList<>();
    private List<Figure> figuresHistory = new ArrayList<>();
    private List<double[][]> deletedFigureCoords = new ArrayList<>();
    private List<Figure> deletedFigures = new ArrayList<>();
    private Map<Integer, Class> mapFigureTypes = new HashMap<>();

    @FXML
    void initialize() {
        GraphicsContext drawGraphicsContext = drawZone.getGraphicsContext2D();
        GraphicsContext previewGraphicsContext = previewZone.getGraphicsContext2D();

        previewZone.setVisible(false);
        drawZone.setFocusTraversable(true);

        mapFigureTypes = initFigureTypes();

        /*
        * Buttons actions
        */
        openButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
            File file = fileChooser.showOpenDialog(Stage.getWindows().get(0));
            if (file != null){
                try {
                    deletedFigures.clear();
                    deletedFigureCoords.clear();
                    Pair<List<Figure>, List<double[][]>> fileData = Actions.open(file, mapFigureTypes, drawGraphicsContext);
                    figuresHistory = fileData.getKey();
                    figuresCoordsHistory = fileData.getValue();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        saveButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file:");
            File file = fileChooser.showSaveDialog(Stage.getWindows().get(0));
            if (file != null){
                Actions.save(figuresHistory, figuresCoordsHistory, file);
            }
        });
        saveAsButton.setOnAction(actionEvent -> Actions.showAlertWindow("info", "Coming soon...", "Will be available in the next updates!"));
        undoButton.setOnAction(actionEvent -> Actions.undo(figuresHistory, deletedFigures, figuresCoordsHistory, deletedFigureCoords, drawGraphicsContext));
        redoButton.setOnAction(actionEvent -> Actions.redo(figuresHistory, deletedFigures, figuresCoordsHistory, deletedFigureCoords, drawGraphicsContext));
        aboutButton.setOnAction(actionEvent -> Actions.showAlertWindow("info", "About", "TapsDraw by Denyx"));
        clearButton.setOnAction(actionEvent -> {
            Actions.clear(drawGraphicsContext);
            figuresHistory.clear();
            figuresCoordsHistory.clear();
            deletedFigureCoords.clear();
            deletedFigures.clear();
            figureCoords.clear();
        });

        lineFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            lineBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = new LineFigure();
            figureCoords.clear();
        });

        rectangleFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            rectangleBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = new RectangleFigure();
            figureCoords.clear();
        });

        circleFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            circleBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = new CircleFigure();
            figureCoords.clear();
        });

        polygonFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            polygonBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = new PolygonFigure();
            Actions.showAlertWindow("info", "Help", "To stop drawing press ENTER");
        });

        polylineFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            polylineBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = new PolylineFigure();
            Actions.showAlertWindow("info", "Help", "To stop drawing press ENTER");
        });

        fillCheckbox.setOnAction(actionEvent -> {
            currentFigure.setNeedToFillFigure(fillCheckbox.isSelected());
        });

        /*
         * Draw zone actions
         */
        drawZone.setOnDragDetected(mouseEvent -> {
            if (currentFigure == null) {
                Actions.showAlertWindow("error", "Choose Figure", "Please, select figure to draw");
            } else {
                if (figureCoords.size() < 1) {
                    previewStartCoords[0] = mouseEvent.getX();
                    previewStartCoords[1] = mouseEvent.getY();
                    figureCoords.add(new double[] {mouseEvent.getX(), mouseEvent.getY()});
                }
            }
        });

        drawZone.setOnMouseDragged(mouseEvent -> {
            if (currentFigure != null) {
                currentFigure.setFigureColor(strokeColorPicker.getValue(), fillColorPicker.getValue());
                currentFigure.setFigureLineWidth(Integer.parseInt(chooseWidthField.getText()));
                previewZone.setVisible(true);
                double[] endCoords = new double[] {mouseEvent.getX(), mouseEvent.getY()};
                if (figureCoords.size() < 1) {
                    currentFigure.preview(previewStartCoords, endCoords, previewGraphicsContext);
                } else {
                    currentFigure.preview(figureCoords.get(figureCoords.size() - 1), endCoords, previewGraphicsContext);
                }
            }
        });

        drawZone.setOnMouseReleased(mouseEvent -> {
            if (currentFigure != null) {
                figureCoords.add(new double[] {mouseEvent.getX(), mouseEvent.getY()});
                previewZone.setVisible(false);
                if (figureCoords.size() > 1 || currentFigure.isFigurePolygonal()) {
                    currentFigure.draw(figureCoords, drawGraphicsContext);
                } else {
                    Actions.showAlertWindow("error", "Draw Error", "Please, drag your mouse!");
                    figureCoords.clear();
                    return;
                }
                previewStartCoords = new double[] {NaN, NaN};
                if (!currentFigure.isFigurePolygonal()){
                    figuresHistory.add(currentFigure);
                    double[][] coords = new double[figureCoords.size()][figureCoords.size()];
                    for (int i = 0; i <= figureCoords.size() - 1; i++) {
                        coords[i][0] = figureCoords.get(i)[0];
                        coords[i][1] = figureCoords.get(i)[1];
                    }
                    figuresCoordsHistory.add(coords);
                    deletedFigureCoords.clear();
                    deletedFigures.clear();
                    figureCoords.clear();
                }
                drawZone.requestFocus();
            }
        });

        drawZone.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                figuresHistory.add(currentFigure);
                double[][] coords = new double[figureCoords.size()][figureCoords.size()];
                for (int i = 0; i <= figureCoords.size() - 1; i++) {
                    coords[i][0] = figureCoords.get(i)[0];
                    coords[i][1] = figureCoords.get(i)[1];
                }
                figuresCoordsHistory.add(coords);
                deletedFigureCoords.clear();
                deletedFigures.clear();
                figureCoords.clear();
            }
        });

    }
    /*
     * Necessary functions
     */
    private void buttonsClearBackground() {
        lineBackground.setStyle("-fx-background-color: #bcbcbc");
        rectangleBackground.setStyle("-fx-background-color: #bcbcbc");
        circleBackground.setStyle("-fx-background-color: #bcbcbc");
        polygonBackground.setStyle("-fx-background-color: #bcbcbc");
        polylineBackground.setStyle("-fx-background-color: #bcbcbc");
    }

    private Map<Integer, Class> initFigureTypes() {
        Map<Integer, Class> map = new HashMap<>();
        map.put(1, LineFigure.class);
        map.put(2, CircleFigure.class);
        map.put(3, RectangleFigure.class);
        map.put(4, PolylineFigure.class);
        map.put(5, PolygonFigure.class);
        return map;
    }
}
