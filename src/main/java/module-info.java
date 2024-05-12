module com.example.nasaapidemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires java.desktop;

    requires layout;
    requires kernel;
    requires org.slf4j;
    requires org.apache.logging.log4j;
    requires io;
    requires java.sql;


    opens com.example.nasaapidemo to javafx.fxml;
    exports com.example.nasaapidemo;
}