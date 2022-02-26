package com.example.taller_hilos;

import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    ObservableList<String> listServices = FXCollections.observableArrayList("Autorizaciones", "Citas", "Atencion al cliente");
    List<Person> personAList = new ArrayList<>();
    List<Person> personCList = new ArrayList<>();
    List<Person> personACList = new ArrayList<>();
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
        startThreads();
    }

    private void startThreads() {
        //Se colocan los label que dependen del estado
        lblAEstado.setChild(lblATurn);
        lblCEstado.setChild(lblCTurn);
        lblACEstado.setChild(lblACTurn);

    }

    @FXML
    public void test() {
        Person persona = new Person(this.txtName.getText(), this.txtId.getText());
        persona.setModule(boxServices.getValue());
        switch (boxServices.getValue()) {
            case "Autorizaciones" -> {
                lblTurnos.setText("A"+lblAEstado.getSize());
                persona.asignTurn(lblTurnos.getText());
                lblAEstado.addItem(persona);
                lblAEstado.start();
            }
            case "Citas" -> {
                lblTurnos.setText("C"+lblCEstado.getSize());
                persona.asignTurn(lblTurnos.getText());
                lblCEstado.addItem(persona);
                lblCEstado.start();
            }
            case "Atencion al cliente" -> {
                lblTurnos.setText("AC"+lblACEstado.getSize());
                persona.asignTurn(lblTurnos.getText());
                lblACEstado.addItem(persona);
                lblACEstado.start();
            }
        }
        startThreads();
    }
}