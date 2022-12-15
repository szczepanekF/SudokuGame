module View {
    requires ModelProject;
    requires javafx.fxml;

    requires javafx.controls;
    opens pl.comp.view to javafx.fxml;
    exports pl.comp.view;
}