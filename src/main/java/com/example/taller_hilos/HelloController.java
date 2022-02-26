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
    private Label lblAEstado;

    @FXML
    private Label lblCEstado;

    @FXML
    private Label lblACEstado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}