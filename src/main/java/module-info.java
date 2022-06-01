module com.example.metododeltrapeciofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires exp4j;


    opens com.example.metododeltrapeciofx to javafx.fxml;
    exports com.example.metododeltrapeciofx;
}