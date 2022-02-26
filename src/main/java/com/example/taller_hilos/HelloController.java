package com.example.taller_hilos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    ObservableList<String> listServices = FXCollections.observableArrayList("Autorizaciones", "Citas", "Atencion al cliente");
    @FXML
    private ComboBox<String> boxServices;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtId;

    @FXML
    private Label lblTurnos;

    @FXML
    private Label lblATurn;

    @FXML
    private Label lblCTurn;

    @FXML
    private Label lblACTurn;

    @FXML
    private com.example.taller_hilos.Label lblAEstado;

    @FXML
    private com.example.taller_hilos.Label lblCEstado;

    @FXML
    private com.example.taller_hilos.Label lblACEstado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Se agregan los items al select
        boxServices.getItems().addAll(listServices);

        //Se colocan los label que dependen del estado
        lblAEstado.setChild(lblATurn);
        lblCEstado.setChild(lblCTurn);
        lblACEstado.setChild(lblACTurn);

        //Se coloca el valor que debe acompañar al index
        lblAEstado.setType("A");
        lblCEstado.setType("C");
        lblACEstado.setType("AC");

        //Se inicializa el hilo
        lblAEstado.start();
        lblCEstado.start();
        lblACEstado.start();
    }
}