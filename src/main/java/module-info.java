module org.Denyx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens org.Denyx to javafx.fxml;
    exports org.Denyx;

    opens org.Denyx.figures;
    exports org.Denyx.figures;
}