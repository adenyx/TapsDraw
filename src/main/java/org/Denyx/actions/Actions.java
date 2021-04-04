package org.Denyx.actions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

public class Actions {
    public static void redo() {}

    public static void undo() {}

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
