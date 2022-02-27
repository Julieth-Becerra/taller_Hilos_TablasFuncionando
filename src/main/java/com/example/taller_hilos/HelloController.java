package com.example.taller_hilos;

import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    private Object FXMLLoader;

    @FXML
    private AnchorPane estadisticas;

    @FXML
    private AnchorPane turnos;


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

        if (txtName.getText().equals("") || txtId.getText().equals("") ||  boxServices.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Hay campos vacios, por favor complete la informacion");
            alert.showAndWait();
        }else {
            Person persona = new Person(this.txtName.getText(), this.txtId.getText(), this.lblTurnos.getText());
            persona.setModule(boxServices.getValue());
            System.out.println(boxServices.getValue());
            switch (boxServices.getValue()) {
                case "Autorizaciones" -> {
                    lblTurnos.setText("A" + lblAEstado.getSize());
                    persona.asignTurn(lblTurnos.getText());
                    lblAEstado.addItem(persona);
                    personAList.add(new Person(txtName.getText(), txtId.getText(), lblTurnos.getText()));
                    lblAEstado.start();
                    break;
                }
                case "Citas" -> {
                    lblTurnos.setText("C" + lblCEstado.getSize());
                    persona.asignTurn(lblTurnos.getText());
                    lblCEstado.addItem(persona);
                    personCList.add(new Person(txtName.getText(), txtId.getText(), lblTurnos.getText()));
                    lblCEstado.start();
                    break;
                }
                case "Atencion al cliente" -> {
                    lblTurnos.setText("AC" + lblACEstado.getSize());
                    persona.asignTurn(lblTurnos.getText());
                    lblACEstado.addItem(persona);
                    personACList.add(new Person(txtName.getText(), txtId.getText(), lblTurnos.getText()));
                    lblACEstado.start();
                    break;
                }
            }
            startThreads();
        }
    }

    public void estadisticas (){
        estadisticas.setVisible(true);
        turnos.setVisible(false);
        for (int i = 0; i < personAList.size(); i++) {

            System.out.println(personAList.get(i));
        }
        for (int i = 0; i < personCList.size() ; i++) {

            System.out.println(personCList.get(i));
        }
        for (int i = 0; i < personACList.size(); i++) {
            System.out.println(personACList.get(i));
        }
    }

    public void cerrar (){
        estadisticas.setVisible(false);
        turnos.setVisible(true);
    }
}