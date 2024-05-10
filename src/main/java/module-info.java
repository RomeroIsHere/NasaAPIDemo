module com.example.nasaapidemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;

    opens com.example.nasaapidemo to javafx.fxml;
    exports com.example.nasaapidemo;
}