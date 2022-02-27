package com.example.taller_hilos;

import Model.Person;
import Model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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


    @FXML
    private TableView<Persona> tablaA;
    @FXML
    private TableColumn AName;
    @FXML
    private TableColumn AId;
    @FXML
    private TableColumn ATurno;
    ObservableList<Persona> personasTablaA;


    @FXML
    private TableView<Persona> tablaC;
    @FXML
    private TableColumn CName;
    @FXML
    private TableColumn CId;
    @FXML
    private TableColumn CTurn;
    ObservableList<Persona> personasTablaC;



    @FXML
    private TableView<Persona> tablaAC;
    @FXML
    private TableColumn ACName;
    @FXML
    private TableColumn ACId;
    @FXML
    private TableColumn ACTurn;
    ObservableList<Persona> personasTablaAC;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         //Se agregan los items al select

        boxServices.getItems().addAll(listServices);
        startThreads();

    }


    private void inicializar_tabla (){
        AName.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre"));
        AId.setCellValueFactory(new PropertyValueFactory<Persona,String>("id"));
        ATurno.setCellValueFactory(new PropertyValueFactory<Persona,String>("turno"));

        personasTablaA = FXCollections.observableArrayList();
        tablaA.setItems(personasTablaA);


        CName.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre"));
        CId.setCellValueFactory(new PropertyValueFactory<Persona,String>("id"));
        CTurn.setCellValueFactory(new PropertyValueFactory<Persona,String>("turno"));

        personasTablaC = FXCollections.observableArrayList();
        tablaC.setItems(personasTablaC);




        ACName.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre"));
        ACId.setCellValueFactory(new PropertyValueFactory<Persona,String>("id"));
        ACTurn.setCellValueFactory(new PropertyValueFactory<Persona,String>("turno"));

        personasTablaAC = FXCollections.observableArrayList();
        tablaAC.setItems(personasTablaAC);


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



        this.inicializar_tabla();


        for (int i = 0; i < personAList.size(); i++) {


            Persona persona = new Persona();
            persona.nombre.set(personAList.get(i).getName());
            persona.id.set(personAList.get(i).getId());
            persona.turno.set(personAList.get(i).getTurn());

            personasTablaA.add(persona);


        }





        for (int i = 0; i < personCList.size() ; i++) {


            Persona persona = new Persona();
            persona.nombre.set(personCList.get(i).getName());
            persona.id.set(personCList.get(i).getId());
            persona.turno.set(personCList.get(i).getTurn());

            personasTablaC.add(persona);



        }
        for (int i = 0; i < personACList.size(); i++) {

            Persona persona = new Persona();
            persona.nombre.set(personACList.get(i).getName());
            persona.id.set(personACList.get(i).getId());
            persona.turno.set(personACList.get(i).getTurn());

            personasTablaAC.add(persona);

        }
    }

    public void cerrar (){
        estadisticas.setVisible(false);
        turnos.setVisible(true);
    }
}