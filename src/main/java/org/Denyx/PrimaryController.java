package org.Denyx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import org.Denyx.actions.Actions;

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
    private MenuItem aboutButton;

    @FXML
    private ImageView lineFigure;

    @FXML
    private ImageView rectangleFigure;

    @FXML
    private ImageView circleFigure;

    @FXML
    private ImageView polygonFigure;

    @FXML
    private ImageView polylineFigure;

    @FXML
    private ImageView ellipsisButton;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField chooseWidthField;

    @FXML
    private Button setBoundsButton;

    Actions actions = new Actions();
    @FXML
    void initialize() {
        setBoundsButton.setOnAction(actionEvent -> actions.showAlertWindow());
    }
}
