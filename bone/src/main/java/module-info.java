module com.example.bone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bone to javafx.fxml;
    exports com.example.bone;
    exports org.example.bone;
    opens org.example.bone to javafx.fxml;
}