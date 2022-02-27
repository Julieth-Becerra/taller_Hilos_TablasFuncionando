module com.example.taller_hilos {


    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.taller_hilos to javafx.fxml;
    exports com.example.taller_hilos;

    opens Model ;
    exports Model;
}