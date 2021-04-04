package org.Denyx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
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
    @FXML
    void initialize() {
        GraphicsContext drawGraphicsContext = drawZone.getGraphicsContext2D();
        GraphicsContext previewGraphicsContext = previewZone.getGraphicsContext2D();

        previewZone.setVisible(false);
        drawZone.setFocusTraversable(true);

        /*
        * Button actions
        */
        openButton.setOnAction(actionEvent -> Actions.showAlertWindow("info", "Coming soon...", "Will be available in the next updates!"));
        saveButton.setOnAction(actionEvent -> Actions.showAlertWindow("info", "Coming soon...", "Will be available in the next updates!"));
        saveAsButton.setOnAction(actionEvent -> Actions.showAlertWindow("info", "Coming soon...", "Will be available in the next updates!"));
        undoButton.setOnAction(actionEvent -> Actions.showAlertWindow("info", "Coming soon...", "Will be available in the next updates!"));
        redoButton.setOnAction(actionEvent -> Actions.showAlertWindow("info", "Coming soon...", "Will be available in the next updates!"));
        aboutButton.setOnAction(actionEvent -> Actions.showAlertWindow("info", "About", "TapsDraw by Denyx"));
        clearButton.setOnAction(actionEvent -> Actions.clear(drawGraphicsContext));

        lineFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            lineBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = new LineFigure();
        });

        rectangleFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            rectangleBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = new RectangleFigure();
        });

        circleFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            circleBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = new CircleFigure();
        });

        polygonFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            polygonBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = null;
            Actions.showAlertWindow("info", "Coming soon...", "Will be available in the next updates!");
        });

        polylineFigure.setOnMouseClicked(mouseEvent -> {
            buttonsClearBackground();
            fillCheckbox.setSelected(false);
            polylineBackground.setStyle("-fx-background-color: #FFFFFF");
            currentFigure = null;
            Actions.showAlertWindow("info", "Coming soon...", "Will be available in the next updates!");
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
                previewStartCoords[0] = mouseEvent.getX();
                previewStartCoords[1] = mouseEvent.getY();
                figureCoords.add(new double[] {mouseEvent.getX(), mouseEvent.getY()});
            }
        });

        drawZone.setOnMouseDragged(mouseEvent -> {
            if (currentFigure != null) {
                currentFigure.setFigureColor(strokeColorPicker.getValue(), fillColorPicker.getValue());
                currentFigure.setFigureLineWidth(Integer.parseInt(chooseWidthField.getText()));
                previewZone.setVisible(true);
                double[] endCoords = new double[] {mouseEvent.getX(), mouseEvent.getY()};
                currentFigure.preview(previewStartCoords, endCoords, previewGraphicsContext);
            }
        });

        drawZone.setOnMouseReleased(mouseEvent -> {
            if (currentFigure != null) {
                figureCoords.add(new double[] {mouseEvent.getX(), mouseEvent.getY()});
                previewZone.setVisible(false);
                currentFigure.draw(figureCoords, drawGraphicsContext);
                previewStartCoords = new double[] {NaN, NaN};
                figureCoords.clear();
                drawZone.requestFocus();
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
}
