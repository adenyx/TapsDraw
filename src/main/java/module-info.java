module org.Denyx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.Denyx to javafx.fxml;
    exports org.Denyx;
}