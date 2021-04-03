package org.Denyx.actions;

import javafx.scene.control.Alert;

public class Actions {
    public static void redo() {}

    public static void undo() {}

    public static void save() {}

    public static void saveAs() {}

    public static void open() {}

    public static void showAlertWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming soon...");
        alert.setHeaderText(null);
        alert.setContentText("Will be available in the next updates!");

        alert.showAndWait();
    }
}
