module com.example.bananarang {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.bananarang to javafx.fxml;
    exports com.example.bananarang;
}