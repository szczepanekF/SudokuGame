module View {
    requires Model;
    requires javafx.fxml;
    requires javafx.controls;
    requires ch.qos.logback.core;
    requires ch.qos.logback.classic;
    requires org.slf4j;
    opens pl.comp.view to javafx.fxml;
    exports pl.comp.view;

}