module com.engmig {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires vecmath;
    requires java.desktop;
    requires javafx.swing;

    opens com.engmig to javafx.fxml;
    exports com.engmig;
}